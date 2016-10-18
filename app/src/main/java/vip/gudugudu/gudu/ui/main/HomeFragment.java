package vip.gudugudu.gudu.ui.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseFragment;
import vip.gudugudu.gudu.base.BaseListFragment;
import vip.gudugudu.gudu.base.util.helper.FragmentAdapter;

import static android.R.id.tabs;

public class HomeFragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View {

    @Bind(R.id.fhome_tablayout)
    TabLayout fhomeTablayout;
    @Bind(R.id.fhome_viewpager)
    ViewPager fhomeViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void showTabList(List<TableShowEntity> mtabs) {
        List<Fragment> fragments = new ArrayList<>();
//        Observable.from(mtabs).subscribe(tab -> fragments.add(BaseListFragment.newInstance(ArticleItemVH.class, tab.type)));
//        fhomeViewpager.setAdapter(new FragmentAdapter(getFragmentManager(), fragments, Arrays.asList(mTabs)));
//        tabs.setupWithViewPager(viewpager);
//        tabs.setTabsFromPagerAdapter(viewpager.getAdapter());
    }
}
