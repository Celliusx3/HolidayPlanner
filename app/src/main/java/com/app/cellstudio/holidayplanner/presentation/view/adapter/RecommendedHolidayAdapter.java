package com.app.cellstudio.holidayplanner.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ListItemRecommendedHolidayBinding;
import com.app.cellstudio.holidayplanner.interactor.model.BestHolidayWithAnnualLeaveModel;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by coyan on 10/10/2018.
 */

public class RecommendedHolidayAdapter extends RecyclerView.Adapter<RecommendedHolidayAdapter.ViewHolder> {

    private List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModels;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemRecommendedHolidayBinding binding;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public ViewHolder(ListItemRecommendedHolidayBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
    }

    public RecommendedHolidayAdapter(List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModels) {
        this.bestHolidayWithAnnualLeaveModels = bestHolidayWithAnnualLeaveModels;
    }

    @Override
    public RecommendedHolidayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemRecommendedHolidayBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_recommended_holiday, parent, false);
        return new RecommendedHolidayAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RecommendedHolidayAdapter.ViewHolder holder, int position) {
        holder.binding.setModel(bestHolidayWithAnnualLeaveModels.get(position));
    }

    @Override
    public int getItemCount() {
        return bestHolidayWithAnnualLeaveModels.size();
    }
}

