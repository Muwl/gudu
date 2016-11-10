package vip.gudugudu.gudu.ui.forget;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.ui.resertpwd.ResertActivity;

import static cn.smssdk.SMSSDK.getVerificationCode;
import static cn.smssdk.SMSSDK.submitVerificationCode;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ForgetActiyity extends BaseActivity<ForgetPresenter, ForgetModel> implements ForgetContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.forgetpwd_phone)
    EditText forgetpwdPhone;
    @Bind(R.id.forgetpwd_getcode)
    TextView forgetpwdGetcode;
    @Bind(R.id.forgetpwd_code)
    EditText forgetpwdCode;
    @Bind(R.id.forgetpwd_next)
    TextView forgetpwdNext;

    private TimeCount time;
    private String sphone;

    EventHandler eh=new EventHandler(){

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                Log.e("---------",new Gson().toJson(data));
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    dissDialog();
                    Intent intent=new Intent(ForgetActiyity.this, ResertActivity.class);
                    intent.putExtra("phone",ToosUtils.getTextContent(forgetpwdPhone));
                    startActivityForResult(intent,1001);
                    //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    dissDialog();
                    time.start();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show("发送成功");
                        }
                    });
                    //获取验证码成功
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表

                }
            }else{
                if (event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show("验证码错误，请重新输入");
                        }
                    });
                }else if(event==SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show("发送失败");
                        }
                    });
                }

                dissDialog();
                ((Throwable)data).printStackTrace();
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    public void initView() {
        titleTitle.setText("找回密码");
        time = new TimeCount(60000, 1000);
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK && requestCode==1001){
            finish();
        }
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            forgetpwdGetcode.setText("获取验证码");
            forgetpwdGetcode.setClickable(true);
            forgetpwdGetcode.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            forgetpwdGetcode.setClickable(false);
            forgetpwdGetcode.setEnabled(false);
            forgetpwdGetcode.setText(millisUntilFinished / 1000 + "S");
        }
    }



    @OnClick({R.id.title_back, R.id.forgetpwd_getcode, R.id.forgetpwd_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.forgetpwd_getcode:
                if (ToosUtils.isTextEmpty(forgetpwdPhone)) {
                    ToastUtil.show( "手机号不能为空！");
                    return ;
                }
                if (!ToosUtils.MatchPhone(ToosUtils.getTextContent(forgetpwdPhone))) {
                    ToastUtil.show("输入的手机号格式错误！");
                    return ;
                }
                showDialog();

                getVerificationCode("86",ToosUtils.getTextContent(forgetpwdPhone));
                break;
            case R.id.forgetpwd_next:
                if (checkInput()){
                    submitVerificationCode("86", ToosUtils.getTextContent(forgetpwdPhone), ToosUtils.getTextContent(forgetpwdCode));
                }


                break;
        }
    }

    private boolean checkInput() {
        if (ToosUtils.isTextEmpty(forgetpwdPhone)) {
            ToastUtil.show("手机号不能为空！");
            return false;
        }
        if (!ToosUtils.MatchPhone(ToosUtils.getTextContent(forgetpwdPhone))) {
            ToastUtil.show( "输入的手机号格式错误！");
            return false;
        }
        if (ToosUtils.isTextEmpty(forgetpwdCode)) {
            ToastUtil.show( "验证码不能为空！");
            return false;
        }
        return true;

    }

}
