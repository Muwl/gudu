package vip.gudugudu.gudu.ui.main;

import java.util.List;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.TableShowEntity;

/**
 * Created by Administrator on 2016/10/16.
 */

public class HomePresenter extends HomeContract.Presenter implements ResponseListener<List<TableShowEntity>>{

    @Override
    public void onStart() {
        ((HomeModel)mModel).setTableListLister(this);
        getTabList();
    }

    @Override
    public void getTabList() {
        mModel.getTabs();
    }


    @Override
    public void onSucess(List<TableShowEntity> data) {
        mView.showTabList(data);
    }

    @Override
    public void OnTokenError(String s) {

    }

    @Override
    public void onError(String s) {
        mView.getTableError(s);
    }


}
