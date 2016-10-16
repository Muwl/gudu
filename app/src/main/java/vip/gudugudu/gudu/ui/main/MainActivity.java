package vip.gudugudu.gudu.ui.main;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements MainContract.View{


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }
}
