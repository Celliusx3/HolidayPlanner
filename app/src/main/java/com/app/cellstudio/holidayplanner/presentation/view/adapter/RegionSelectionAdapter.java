package com.app.cellstudio.holidayplanner.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View;
import android.view.ViewGroup;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ListItemRegionBinding;
import com.app.cellstudio.holidayplanner.interactor.model.RegionModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 16/09/2018.
 */

public class RegionSelectionAdapter extends RecyclerView.Adapter<RegionSelectionAdapter.ViewHolder> {

    private List<RegionModel> regionModels;
    private RegionModel currentRegion;
    private PublishSubject<Pair<RegionModel,RegionModel>>selectedSwitchRegion = PublishSubject.create();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemRegionBinding binding;

        public ViewHolder(View view) {
            super(view);
        }

        public ViewHolder(ListItemRegionBinding binding) {
            this(binding.getRoot());
            this.binding = binding;

        }
    }

    public RegionSelectionAdapter(List<RegionModel> regionModels,
                                    RegionModel currentRegion) {
        this.regionModels = regionModels;
        this.currentRegion = currentRegion;
    }

    public Observable<Pair<RegionModel,RegionModel>> getSelectedSwitchRegion() {
        return selectedSwitchRegion;
    }

    @Override
    public RegionSelectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemRegionBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_region, parent, false);
        return new RegionSelectionAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RegionSelectionAdapter.ViewHolder holder, int position) {

        holder.binding.setModel(regionModels.get(position));

        holder.binding.setListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos >= 0) {
                selectedSwitchRegion.onNext(new Pair<>(regionModels.get(pos),currentRegion));
                currentRegion = regionModels.get(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return regionModels.size();
    }
}

