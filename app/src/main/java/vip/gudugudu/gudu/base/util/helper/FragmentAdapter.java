package vip.gudugudu.gudu.base.util.helper;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vip.gudugudu.gudu.data.entity.TableShowEntity;

/**
 * Created by baixiaokang on 16/5/8.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<TableShowEntity> mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<TableShowEntity> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).ClassName;
    }
}