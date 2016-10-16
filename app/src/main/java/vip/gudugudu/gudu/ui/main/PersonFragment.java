package vip.gudugudu.gudu.ui.main;

import android.view.View;

import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseFragment;

public class PersonFragment extends BaseFragment<PersonPresenter,PersonModel> implements PersonContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initView(View view) {

    }
}
