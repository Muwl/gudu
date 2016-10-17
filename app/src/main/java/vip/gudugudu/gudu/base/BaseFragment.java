package vip.gudugudu.gudu.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import vip.gudugudu.gudu.base.util.TUtil;

/**
 * Created by Administrator on 2016/10/16.
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel>  extends Fragment {
    public T mPresenter;
    public E mModel;
    public Context mContext;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(this.getLayoutId(),null);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView(rootView);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.onDestroy();
        ButterKnife.unbind(this);
    }


    public abstract int getLayoutId();


    public abstract void initView(View view);


}
