package vip.gudugudu.gudu.ui.main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {

    @Bind(R.id.main_container)
    FrameLayout mainContainer;
    @Bind(R.id.mainbom_home)
    TextView mainbomHome;
    @Bind(R.id.mainbom_colloect)
    TextView mainbomColloect;
    @Bind(R.id.mainbom_person)
    TextView mainbomPerson;

    private HomeFragment homeFragment;
    private CollectFragment collectFragment;
    private PersonFragment personFragment;

    private int currentId = R.id.mainbom_home;// 当前选中id,默认是主页

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainbomHome.setSelected(true);
        changeFragment(currentId);
    }



    @OnClick({R.id.mainbom_home, R.id.mainbom_colloect, R.id.mainbom_person})
    public void onClick(View view) {
        if (view.getId() != currentId) {
            changeSelect(view.getId());
            changeFragment(view.getId());
            currentId = view.getId();
        }
    }

    /**
     * 改变fragment的显示
     *
     * @param resId
     */
    private void changeFragment(int resId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideFragments(transaction);//隐藏所有fragment
        if (resId == R.id.mainbom_home) {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                transaction.add(R.id.main_container, homeFragment);
            } else {
                transaction.show(homeFragment);
            }
        } else if (resId == R.id.mainbom_colloect) {
            if (collectFragment == null) {
                collectFragment = new CollectFragment();
                transaction.add(R.id.main_container, collectFragment);
            } else {
                transaction.show(collectFragment);
            }
        } else if (resId == R.id.mainbom_person) {
            if (personFragment == null) {
                personFragment = new PersonFragment();
                transaction.add(R.id.main_container, personFragment);
            } else {
                transaction.show(personFragment);
            }
        }
        transaction.commit();//一定要记得提交事务
    }


    /**
     * 显示之前隐藏所有fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null)//不为空才隐藏,如果不判断第一次会有空指针异常
            transaction.hide(homeFragment);
        if (collectFragment != null)
            transaction.hide(collectFragment);
        if (personFragment != null)
            transaction.hide(personFragment);
    }

    /**
     * 改变TextView选中颜色
     *
     * @param resId
     */
    private void changeSelect(int resId) {
        mainbomHome.setSelected(false);
        mainbomColloect.setSelected(false);
        mainbomPerson.setSelected(false);

        switch (resId) {
            case R.id.mainbom_home:
                mainbomHome.setSelected(true);
                break;
            case R.id.mainbom_colloect:
                mainbomColloect.setSelected(true);
                break;
            case R.id.mainbom_person:
                mainbomPerson.setSelected(true);
                break;
        }
    }


}
