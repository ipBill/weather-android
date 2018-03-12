package weather.com.theweatherapp.menu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MainMenuViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentMenuList = new ArrayList<>();

    public MainMenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentMenuList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentMenuList.size();
    }

    public void addFragment(Fragment fragment) {
        fragmentMenuList.add(fragment);
    }
}
