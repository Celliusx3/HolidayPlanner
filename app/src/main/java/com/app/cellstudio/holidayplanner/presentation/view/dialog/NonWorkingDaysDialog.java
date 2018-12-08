package com.app.cellstudio.holidayplanner.presentation.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.data.pref.BasePref;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.navigation.Navigator;

import org.joda.time.DateTimeConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by coyan on 16/10/2018.
 */

public class NonWorkingDaysDialog extends BaseDialog {

    @Inject
    BasePref pref;

    @Inject
    Navigator navigator;

    @BindView(R.id.cb_monday)
    CheckBox cbMonday;

    @BindView(R.id.cb_tuesday)
    CheckBox cbTuesday;

    @BindView(R.id.cb_wednesday)
    CheckBox cbWednesday;

    @BindView(R.id.cb_thursday)
    CheckBox cbThursday;

    @BindView(R.id.cb_friday)
    CheckBox cbFriday;

    @BindView(R.id.cb_saturday)
    CheckBox cbSaturday;

    @BindView(R.id.cb_sunday)
    CheckBox cbSunday;

    private static final String EXTRA_DATA = "EXTRA_DATA";
    private static final String EXTRA_ISCANCELABLE = "EXTRA_ISCANCELABLE";

    private Listener listener;

    public static NonWorkingDaysDialog newInstance(List<Integer> nonWorkingDays, boolean isCancelable) {

        Bundle args = new Bundle();
        if (nonWorkingDays != null)
            args.putIntegerArrayList(EXTRA_DATA, new ArrayList<>(nonWorkingDays));
        args.putBoolean(EXTRA_ISCANCELABLE, isCancelable);

        NonWorkingDaysDialog fragment = new NonWorkingDaysDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_non_working_days;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onInject() {
        super.onInject();

        BaseApplication.getInstance()
                .getApplicationComponent()
                .inject(this);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected void onBindData(View view) {
        super.onBindData(view);
        if (getArguments() != null) {
            initDefaultNonWorkingDays(getArguments().getIntegerArrayList(EXTRA_DATA));
            setCancelable(getArguments().getBoolean(EXTRA_ISCANCELABLE));
        } else {
            initDefaultNonWorkingDays(null);
            setCancelable(false);
        }
    }

    @OnClick(R.id.btn_nwd_next)
    void onBtnNextClicked() {
        if (listener != null) {
            listener.onNextClickListener(getNonWorkingDaysInWeek());
        }
        dismiss();
    }

    List<Integer> getNonWorkingDaysInWeek(){
        List<Integer> nonWorkingDaysInWeek = new ArrayList<>();

        // Monday
        if (cbMonday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.MONDAY);
        // Tuesday
        if (cbTuesday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.TUESDAY);
        // Wednesday
        if (cbWednesday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.WEDNESDAY);
        // Thursday
        if (cbThursday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.THURSDAY);
        // Friday
        if (cbFriday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.FRIDAY);
        // Saturday
        if (cbSaturday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.SATURDAY);
        // Sunday
        if (cbSunday.isChecked())
            nonWorkingDaysInWeek.add(DateTimeConstants.SUNDAY);
        return nonWorkingDaysInWeek;
    }

    void initDefaultNonWorkingDays(List<Integer> nonWorkingDays){
        if (nonWorkingDays != null && !nonWorkingDays.isEmpty()){
            for (Integer nonWorkingDay: nonWorkingDays) {
                switch(nonWorkingDay){
                    case 1:
                        cbMonday.setChecked(true);
                        break;
                    case 2:
                        cbTuesday.setChecked(true);
                        break;
                    case 3:
                        cbWednesday.setChecked(true);
                        break;
                    case 4:
                        cbThursday.setChecked(true);
                        break;
                    case 5:
                        cbFriday.setChecked(true);
                        break;
                    case 6:
                        cbSaturday.setChecked(true);
                        break;
                    case 7:
                        cbSunday.setChecked(true);
                        break;
                    default:
                        break;
                }
            }
            
        } else if (nonWorkingDays == null) {
            cbSaturday.setChecked(true);
            cbSunday.setChecked(true);
        }
    }

    public interface Listener {
        void onNextClickListener(List<Integer> daysInWeek);
    }
}

