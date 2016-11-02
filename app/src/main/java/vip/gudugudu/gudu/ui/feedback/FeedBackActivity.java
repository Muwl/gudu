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
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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
                if (checkInput()){
                    showDialog();
                    mPresenter.feedback(ToosUtils.getTextContent(feedbackContent),ToosUtils.getTextContent(feedbackPhone));
                }
                break;
        }
    }

    /**
     * 注册输入
     *
     * @return
     */
    private boolean checkInput() {
        if (ToosUtils.isTextEmpty(feedbackContent)) {
            ToastUtil.show("回访内容不能为空！");
            return false;
        }
        if (ToosUtils.isTextEmpty(feedbackPhone)) {
            ToastUtil.show( "联系方式不能为空！");
            return false;
        }
        return true;

    }

    @Override
    public void getFeedBackView() {
        dissDialog();
        ToastUtil.show("反馈成功");
        finish();
    }

    @Override
    public void getFeedBackError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }

    @Override
    public void getFeedBackTokenError(String s) {
        dissDialog();
        ToastUtil.show(s);
        ToosUtils.goReLogin(this);
    }
}
