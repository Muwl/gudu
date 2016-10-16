package vip.gudugudu.gudu.ui.main;

import android.view.View;

import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseFragment;

public class CollectFragment extends BaseFragment<CollectPresenter,CollectModel> implements CollectContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initView(View view) {

    }
}
