package vip.gudugudu.gudu.ui.feedback;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class FeedBackActivity extends BaseActivity<FeedBackPresenter, FeedBackModel> implements FeedBackContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.feedback_content)
    EditText feedbackContent;
    @Bind(R.id.feedback_phone)
    EditText feedbackPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView() {
        titleTitle.setText("意见反馈");
        titleSend.setVisibility(View.VISIBLE);
        titleSend.setText("发送");
    }



    @OnClick({R.id.title_back, R.id.title_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                break;
        }
    }
}
