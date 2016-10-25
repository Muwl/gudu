package vip.gudugudu.gudu.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.BaseFragment;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.data.entity.TableShowEntity;

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
        ((BaseActivity)getActivity()).showDialog();

    }

    @Override
    public void showTabList(List<TableShowEntity> mtabs) {
        Log.e("-----",new Gson().toJson(mtabs));
        ((BaseActivity)getActivity()).dissDialog();
        List<Fragment> fragments = new ArrayList<>();

        ToastUtil.show("请求成功");
//        Observable.from(mtabs).subscribe(tab -> fragments.add(BaseListFragment.newInstance(ArticleItemVH.class, tab.type)));
//        fhomeViewpager.setAdapter(new FragmentAdapter(getFragmentManager(), fragments, Arrays.asList(mTabs)));
//        tabs.setupWithViewPager(viewpager);
//        tabs.setTabsFromPagerAdapter(viewpager.getAdapter());
    }

    @Override
    public void getTableError(String s) {
        ((BaseActivity)getActivity()).dissDialog();
        ToastUtil.show(s);
    }
}
