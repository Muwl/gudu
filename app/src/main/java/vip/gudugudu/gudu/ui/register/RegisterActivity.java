package vip.gudugudu.gudu.ui.register;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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
import vip.gudugudu.gudu.data.entity.RegisterEntity;

import static android.R.attr.country;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static cn.smssdk.SMSSDK.getSupportedCountries;
import static cn.smssdk.SMSSDK.getVerificationCode;
import static cn.smssdk.SMSSDK.submitVerificationCode;

/**
 * Created by Administrator on 2016/10/26.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements RegisterContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.register_name)
    EditText registerName;
    @Bind(R.id.register_getcode)
    TextView registerGetcode;
    @Bind(R.id.register_code)
    EditText registerCode;
    @Bind(R.id.register_pwd)
    EditText registerPwd;
    @Bind(R.id.register_cb)
    CheckBox registerCb;
    @Bind(R.id.register_register)
    TextView registerRegister;

    private String sphone;
    private TimeCount time;

    EventHandler eh=new EventHandler(){

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                Log.e("---------",new Gson().toJson(data));
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    mPresenter.register(ToosUtils.getTextContent(registerName),ToosUtils.getTextContent(registerPwd));
                    //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    dissDialog();
                    time.start();
                    //获取验证码成功
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表

                }
            }else{
                dissDialog();
                ((Throwable)data).printStackTrace();
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        titleTitle.setText("注册");
        time = new TimeCount(60000, 1000);
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    public void getRegisterView(RegisterEntity registerEntity) {
        dissDialog();
        setResult(RESULT_OK);
        finish();

    }

    @Override
    public void getRegisterError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            registerGetcode.setText("获取验证码");
            registerGetcode.setClickable(true);
            registerGetcode.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            registerGetcode.setClickable(false);
            registerGetcode.setEnabled(false);
            registerGetcode.setText(millisUntilFinished / 1000 + "S");
        }
    }






    @OnClick({R.id.title_back, R.id.register_getcode, R.id.register_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.register_getcode:
                if (ToosUtils.isTextEmpty(registerName)) {
                    ToastUtil.show( "手机号不能为空！");
                    return ;
                }
                if (!ToosUtils.MatchPhone(ToosUtils.getTextContent(registerName))) {
                    ToastUtil.show("输入的手机号格式错误！");
                    return ;
                }
                showDialog();
                getVerificationCode("86",ToosUtils.getTextContent(registerName));
                break;
            case R.id.register_register:
                if (checkInput()){
                    showDialog();
                    submitVerificationCode("86", ToosUtils.getTextContent(registerName), ToosUtils.getTextContent(registerCode));
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
        if (ToosUtils.isTextEmpty(registerName)) {
            ToastUtil.show("手机号不能为空！");
            return false;
        }
        if (!ToosUtils.MatchPhone(ToosUtils.getTextContent(registerName))) {
            ToastUtil.show( "输入的手机号格式错误！");
            return false;
        }
        if (ToosUtils.isTextEmpty(registerPwd)) {
            ToastUtil.show( "密码不能为空！");
            return false;
        } if (ToosUtils.isTextEmpty(registerCode)) {
            ToastUtil.show( "验证码不能为空！");
            return false;
        }
        if (!ToosUtils.checkPwd(ToosUtils.getTextContent(registerPwd))) {
            ToastUtil.show( "密码不能少于6位！");
            return false;
        }
        return true;

    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(eh);
        super.onDestroy();
    }
}
