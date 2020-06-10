package com.example.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import com.example.fragment.LauncherBaseFragment;

/**
 * Viewpager适配器
 *
 * @author apple
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    private List<LauncherBaseFragment> list;

    public BaseFragmentAdapter(FragmentManager fm, List<LauncherBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
