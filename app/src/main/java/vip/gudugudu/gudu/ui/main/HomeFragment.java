package vip.gudugudu.gudu.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import rx.Observable;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.BaseFragment;
import vip.gudugudu.gudu.base.BaseListFragment;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.helper.FragmentAdapter;
import vip.gudugudu.gudu.data.entity.TableShowEntity;
import vip.gudugudu.gudu.view.viewholder.ArticleItemVH;

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
        ((BaseActivity)getActivity()).showDialog();

    }

    @Override
    public void showTabList(List<TableShowEntity> mtabs) {
        Log.e("-----",new Gson().toJson(mtabs));
        ((BaseActivity)getActivity()).dissDialog();
        List<Fragment> fragments = new ArrayList<>();
        Observable.from(mtabs).subscribe(tab -> fragments.add(BaseListFragment.newInstance(ArticleItemVH.class, tab.PinYin)));
        fhomeViewpager.setAdapter(new FragmentAdapter(((BaseActivity)getActivity()).getSupportFragmentManager(), fragments, mtabs));
        fhomeTablayout.setupWithViewPager(fhomeViewpager);
        fhomeTablayout.setTabsFromPagerAdapter(fhomeViewpager.getAdapter());
    }

    @Override
    public void getTableError(String s) {
        ((BaseActivity)getActivity()).dissDialog();
        ToastUtil.show(s);
    }
}
