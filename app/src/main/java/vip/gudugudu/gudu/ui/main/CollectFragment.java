package vip.gudugudu.gudu.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseFragment;

public class CollectFragment extends BaseFragment<CollectPresenter, CollectModel> implements CollectContract.View {

    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initView(View view) {
        titleBack.setVisibility(View.GONE);
        titleTitle.setText("收藏");
    }

}
