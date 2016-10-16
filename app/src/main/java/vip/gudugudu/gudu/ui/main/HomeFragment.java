package vip.gudugudu.gudu.ui.main;

import android.view.View;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseFragment;

public class HomeFragment extends BaseFragment<HomePresenter,HomeModel> implements HomeContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

    }
}
