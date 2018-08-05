package rahul.myapplication.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.List;

import rahul.myapplication.ui.map.MapFragment;
import rahul.myapplication.ui.news.NewsFragment;

/**
 * Created by rahul on 5/8/18.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = null;

    public TabAdapter(FragmentManager fm) {
        super(fm);

    }
    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MapFragment();
        } else {
            return new NewsFragment();
        }
    }



    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "Map";
            default:return "News";
        }
    }

}
