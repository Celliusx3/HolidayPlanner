package com.app.cellstudio.holidayplanner.presentation.view.adapter;


import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ListItemPublicHolidayItemBinding;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coyan on 16/09/2018.
 */

public class PublicHolidayAdapter extends RecyclerView.Adapter<PublicHolidayAdapter.ViewHolder> {

    private List<HolidayModel> holidayModels;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_iph_date)
        ImageView ivDate;

        @BindView(R.id.tv_iph_month)
        TextView tvMonth;

        private ListItemPublicHolidayItemBinding binding;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public ViewHolder(ListItemPublicHolidayItemBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
    }

    public PublicHolidayAdapter(List<HolidayModel> holidayModels) {
        this.holidayModels = holidayModels;
    }

    @Override
    public PublicHolidayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemPublicHolidayItemBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_public_holiday_item, parent, false);
        return new PublicHolidayAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final PublicHolidayAdapter.ViewHolder holder, int position) {
        holder.binding.setModel(holidayModels.get(position));

        setColor(holder, holidayModels.get(position));
    }

    @Override
    public int getItemCount() {
        return holidayModels.size();
    }

    private void setColor(final PublicHolidayAdapter.ViewHolder holder, HolidayModel holidayModel) {
        Resources resources = holder.itemView.getResources();
        switch (holidayModel.getMonth()) {
            case 1:
                holder.tvMonth.setText(R.string.Jan);
                holder.ivDate.setColorFilter(resources.getColor(R.color.red_500));
                return;
            case 2:
                holder.tvMonth.setText(R.string.Feb);
                holder.ivDate.setColorFilter(resources.getColor(R.color.pink_500));
                return;
            case 3:
                holder.tvMonth.setText(R.string.Mar);
                holder.ivDate.setColorFilter(resources.getColor(R.color.purple_500));
                return;
            case 4:
                holder.tvMonth.setText(R.string.Apr);
                holder.ivDate.setColorFilter(resources.getColor(R.color.deep_purple_500));
                return;
            case 5:
                holder.tvMonth.setText(R.string.May);
                holder.ivDate.setColorFilter(resources.getColor(R.color.indigo_500));
                return;
            case 6:
                holder.tvMonth.setText(R.string.Jun);
                holder.ivDate.setColorFilter(resources.getColor(R.color.blue_500));
                return;
            case 7:
                holder.tvMonth.setText(R.string.Jul);
                holder.ivDate.setColorFilter(resources.getColor(R.color.light_blue_500));
                return;
            case 8:
                holder.tvMonth.setText(R.string.Aug);
                holder.ivDate.setColorFilter(resources.getColor(R.color.cyan_500));
                return;
            case 9:
                holder.tvMonth.setText(R.string.Sep);
                holder.ivDate.setColorFilter(resources.getColor(R.color.teal_500));
                return;
            case 10:
                holder.tvMonth.setText(R.string.Oct);
                holder.ivDate.setColorFilter(resources.getColor(R.color.green_500));
                return;
            case 11:
                holder.tvMonth.setText(R.string.Nov);
                holder.ivDate.setColorFilter(resources.getColor(R.color.light_green_500));
                return;
            case 12:
                holder.tvMonth.setText(R.string.Dec);
                holder.ivDate.setColorFilter(resources.getColor(R.color.lime_500));
                return;
            default:
                return;
        }
    }
}
