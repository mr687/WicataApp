package id.co.wicata.app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.co.wicata.app.fragment.HistoryFragment;

public class MainTabAdapter extends FragmentPagerAdapter {

    public MainTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HistoryFragment();
                break;
            case 1:
                fragment = new HistoryFragment();
                break;
            case 2:
                fragment = new HistoryFragment();
                break;
            default:
                fragment = new HistoryFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position){
            case 0:
                title = "Regions";
                break;
            case 1:
                title = "Tours";
                break;
            case 2:
                title = "Events";
                break;
            default:
                break;
        }
        return title;
    }
}
