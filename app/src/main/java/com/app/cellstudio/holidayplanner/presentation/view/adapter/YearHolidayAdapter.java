package com.app.cellstudio.holidayplanner.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ListItemPublicHolidayBinding;
import com.app.cellstudio.holidayplanner.interactor.model.YearHolidayModel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.ButterKnife;

/**
 * Created by coyan on 17/09/2018.
 */

public class YearHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CopyOnWriteArrayList<YearHolidayModel> models;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public YearHolidayAdapter(@NonNull List<YearHolidayModel> models) {
        this.models = new CopyOnWriteArrayList<>();

        if (models != null)
            this.models.addAll(models);
        this.recycledViewPool = new RecyclerView.RecycledViewPool();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemPublicHolidayBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_public_holiday, parent, false);
        YearHolidayAdapter.ViewHolder viewHolder = new YearHolidayAdapter.ViewHolder(binding);

        RecyclerView rv = viewHolder.rvRailItems;
        Context context = rv.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setRecycledViewPool(recycledViewPool);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {

        YearHolidayAdapter.ViewHolder holder = (YearHolidayAdapter.ViewHolder) viewHolder;
        YearHolidayModel yearHolidayModel = models.get(position);
        holder.binding.setModel(yearHolidayModel);

        RecyclerView rvRails = holder.rvRailItems;

        if (holder.adapter == null) {
            holder.adapter = new PublicHolidayAdapter(yearHolidayModel.getHolidayList());
            rvRails.setAdapter(holder.adapter);
            rvRails.setNestedScrollingEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        int size = models.size();
        return size == 0 ? 1 : size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemPublicHolidayBinding binding;

        public RecyclerView rvRailItems;

        public PublicHolidayAdapter adapter;

        public ViewHolder(View view) {
            super(view);
            rvRailItems = ButterKnife.findById(view, R.id.rv_liph_item);
        }

        public ViewHolder(ListItemPublicHolidayBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
    }
}