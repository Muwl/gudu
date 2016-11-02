package vip.gudugudu.gudu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseFragment;
import vip.gudugudu.gudu.base.util.ImageUtil;
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.ui.feedback.FeedBackActivity;
import vip.gudugudu.gudu.ui.login.LoginActivity;
import vip.gudugudu.gudu.ui.other.SettingActivity;
import vip.gudugudu.gudu.view.widget.CircleImageView;

public class PersonFragment extends BaseFragment<PersonPresenter, PersonModel> implements PersonContract.View {

    @Bind(R.id.person_icon)
    CircleImageView personIcon;
    @Bind(R.id.person_name)
    TextView personName;
    @Bind(R.id.person_setting)
    TextView personSetting;
    @Bind(R.id.person_feedback)
    TextView personFeedback;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (ToosUtils.isStringEmpty(SpUtil.getUserToken())){
            personIcon.setImageResource(R.mipmap.img_default_avatar);
            personName.setText("请登录");
        }else{
            ImageUtil.loadImg(personIcon,SpUtil.getIconUrl());
            if (!ToosUtils.isStringEmpty(SpUtil.getNickname())){
                personName.setText(SpUtil.getNickname());
            }else{
                personName.setText(SpUtil.getUmobile());
            }

        }
        LogManager.LogShow("----","sssssssssssss",LogManager.ERROR);
    }

    @OnClick({R.id.person_name, R.id.person_setting, R.id.person_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_name:
                if (ToosUtils.isStringEmpty(SpUtil.getUserToken())){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.person_setting:
                Intent intent2=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.person_feedback:
                Intent intent3=new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
