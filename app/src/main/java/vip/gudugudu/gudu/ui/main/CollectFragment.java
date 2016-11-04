package vip.gudugudu.gudu.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vip.gudugudu.gudu.C;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.BaseFragment;
import vip.gudugudu.gudu.base.util.TUtil;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.view.dialog.CustomeDialog;
import vip.gudugudu.gudu.view.layout.TRecyclerView;
import vip.gudugudu.gudu.view.viewholder.CollectItemVH;

public class CollectFragment extends BaseFragment<CollectPresenter, CollectModel> implements CollectContract.View {

    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.fcollect_rootview)
    LinearLayout fcollectRootview;

    private TRecyclerView mXRecyclerView;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CustomeDialog.RET_OK:
                    ((BaseActivity)getActivity()).showDialog();
                    mPresenter.delCollect((AlbumsEntity) msg.obj);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initView(View view) {
        titleBack.setVisibility(View.GONE);
        titleTitle.setText("收藏");
        mXRecyclerView = new TRecyclerView(getActivity())
                .setView(CollectItemVH.class);
        fcollectRootview.addView(mXRecyclerView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ((CollectItemVH)mXRecyclerView.mIVH).setHandler(handler);
        if (mXRecyclerView!=null){
            mXRecyclerView.fetch();
        }

    }

    @Override
    public void delCollectView(AlbumsEntity albumsEntity) {
        ToastUtil.show("删除成功");
        ((BaseActivity)getActivity()).dissDialog();
        mXRecyclerView.getAdapter().removeItem(albumsEntity);
    }

    @Override
    public void delCollectError(String s) {
        ((BaseActivity)getActivity()).dissDialog();
        ToastUtil.show(s);
    }

    @Override
    public void delCollectTokenError(String s) {
        ((BaseActivity)getActivity()).dissDialog();
        ToastUtil.show(s);
        ToosUtils.goReLogin(getActivity());
    }
}
