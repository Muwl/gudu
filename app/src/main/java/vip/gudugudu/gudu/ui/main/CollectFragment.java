package vip.gudugudu.gudu.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.data.repository.CollectAlbumsRepository;
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
    int position=-1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CustomeDialog.RET_OK:
                    position= (int) msg.obj;
                    ((BaseActivity)getActivity()).showDialog();
                    mPresenter.delCollect(((CollectAlbumsRepository) mXRecyclerView.getAdapter().getDatas().get(position)).data);
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
//        ((CollectItemVH)mXRecyclerView.mIVH).setHandler(handler);
        mXRecyclerView.setItemOnLongClickLister(new TRecyclerView.itemOnLongClickLister() {
            @Override
            public void itemOnLongClick(int position) {
                CustomeDialog customeDialog=new CustomeDialog(mContext,handler,"确定删除此收藏？",position);

            }
        });
        if (mXRecyclerView!=null){
            mXRecyclerView.reFetch();
        }

    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden){
//            if (mXRecyclerView!=null){
//                mXRecyclerView.
//                mXRecyclerView.fetch();
//            }
//        }
//        LogManager.LogShow("---------------","==="+hidden,LogManager.ERROR);
//    }

    @Override
    public void delCollectView(AlbumsEntity albumsEntity) {
        ToastUtil.show("删除成功");
        ((BaseActivity)getActivity()).dissDialog();
        mXRecyclerView.getAdapter().removeItem(position);
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
