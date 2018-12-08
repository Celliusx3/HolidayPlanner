package com.app.cellstudio.holidayplanner.presentation.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.cellstudio.holidayplanner.interactor.model.PageModel;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.HomeFragment;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.PublicHolidayFragment;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.RecommendedFragment;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coyan on 14/09/2018.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final List<PageModel> pageModels;
    private final List<String> pageModelIds = new ArrayList<>();

    private HomeFragment homeFragment;
    private SettingsFragment settingsFragment;
    private PublicHolidayFragment publicHolidayFragment;
    private RecommendedFragment recommendedFragment;

    private boolean tablet;

    public MainPagerAdapter(final FragmentManager fragmentManager, final List<PageModel> pgModels,
                            final boolean tablet) {
        super(fragmentManager);
        this.pageModels = pgModels;
        this.tablet = tablet;

        pageModelIds.clear();
        for (PageModel pageModel : pageModels) {
            pageModelIds.add(pageModel.getTitle());
        }
    }

    @Override
    public Fragment getItem(int position) {
        PageModel pageModel = pageModels.get(position);
        switch (pageModel.getTitle()){
            case "Settings":
                if (settingsFragment == null) {
                    settingsFragment = SettingsFragment.newInstance();
                }
                return settingsFragment;
            case "Holiday":
                if (publicHolidayFragment == null) {
                    publicHolidayFragment = PublicHolidayFragment.newInstance();
                }
                return publicHolidayFragment;
            case "Recommended":
                if (recommendedFragment == null) {
                    recommendedFragment = RecommendedFragment.newInstance();
                }
                return recommendedFragment;
            default:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                }
                return homeFragment;
        }
    }

    @Override
    public int getCount() {
        return pageModels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageModels.get(position).getTitle();
    }

    public int getPagePositionById(String id) {
        return pageModelIds.indexOf(id);
    }

}
