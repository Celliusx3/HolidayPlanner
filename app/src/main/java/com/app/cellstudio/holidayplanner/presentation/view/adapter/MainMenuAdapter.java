package com.app.cellstudio.holidayplanner.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ListItemDrawerBinding;
import com.app.cellstudio.holidayplanner.interactor.model.PageModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 13/09/2018.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;

    private List<PageModel> pageModels;
    private PublishSubject<PageModel> drawerModel = PublishSubject.create();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemDrawerBinding binding;

        public ViewHolder(View view) {
            super(view);
        }

        public ViewHolder(ListItemDrawerBinding binding){
            this(binding.getRoot());
            this.binding = binding;
        }
    }

    public MainMenuAdapter(List<PageModel> pageModels) {
        this.pageModels = pageModels;
    }

    public Observable<PageModel> getSelectedPageModel() {
        return drawerModel;
    }

    public void setData(List<PageModel> pageModels) {
        this.pageModels = pageModels;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ListItemDrawerBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_drawer, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holderItem = (ViewHolder)holder;

        holderItem.binding.setModel(pageModels.get(position));
        holderItem.binding.setListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos >= 0) {
                drawerModel.onNext(pageModels.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pageModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }
}
