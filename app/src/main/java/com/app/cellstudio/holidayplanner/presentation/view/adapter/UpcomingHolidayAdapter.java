package com.app.cellstudio.holidayplanner.presentation.view.adapter;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ListItemUpcomingHolidayBinding;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coyan on 01/10/2018.
 */

public class UpcomingHolidayAdapter extends RecyclerView.Adapter<UpcomingHolidayAdapter.ViewHolder> {

    private List<HolidayModel> holidayModels;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_cuh_main)
        RelativeLayout rlMain;

        @BindView(R.id.tv_cuh_holiday_title)
        TextView tvHolidayTitle;

        private ListItemUpcomingHolidayBinding binding;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public ViewHolder(ListItemUpcomingHolidayBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
    }

    public UpcomingHolidayAdapter(List<HolidayModel> holidayModels) {
        this.holidayModels = holidayModels;
    }

    @Override
    public UpcomingHolidayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemUpcomingHolidayBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_upcoming_holiday, parent, false);
        return new UpcomingHolidayAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final UpcomingHolidayAdapter.ViewHolder holder, int position) {
        int viewPosition = position % holidayModels.size();
        holder.binding.setModel(holidayModels.get(viewPosition));
        setColor(holder, viewPosition % 2 > 0? Theme.DARK : Theme.LIGHT);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return holidayModels != null ? holidayModels.size() : 0;
    }

    private enum Theme {
        DARK,
        LIGHT
    }

    private void setColor(final UpcomingHolidayAdapter.ViewHolder holder, Theme theme) {
        Resources resources = holder.itemView.getResources();
        switch (theme) {
            case DARK:
                holder.rlMain.setBackgroundColor(resources.getColor(R.color.indigo_800));
                holder.tvHolidayTitle.setBackgroundColor(resources.getColor(R.color.indigo_600));
                return;
            case LIGHT:
                holder.rlMain.setBackgroundColor(resources.getColor(R.color.indigo_600));
                holder.tvHolidayTitle.setBackgroundColor(resources.getColor(R.color.indigo_800));
                return;
            default:
                return;
        }
    }
}