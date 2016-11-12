package vip.gudugudu.gudu.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.GlideImageLoader;
import vip.gudugudu.gudu.base.util.ImageUtil;
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.ui.updatename.UpdateNameActivity;
import vip.gudugudu.gudu.view.dialog.PhotoDialog;
import vip.gudugudu.gudu.view.widget.CircleImageView;

/**
 * Created by Administrator on 2016/11/11.
 */

public class AccountActivity extends BaseActivity<AccountPresenter, AccountModel> implements AccountContract.View {

    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.account_icon)
    CircleImageView accountIcon;
    @Bind(R.id.account_iconlin)
    LinearLayout accountIconlin;
    @Bind(R.id.account_namelin)
    LinearLayout accountNamelin;
    @Bind(R.id.account_name)
    TextView accountName;
    private ImagePicker imagePicker;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PhotoDialog.PHOTO_CAMERA:
                    Intent intent2 = new Intent(AccountActivity.this, ImageGridActivity.class);
                    intent2.putExtra("flag",11);
                    startActivityForResult(intent2, 100);
                    break;
                case PhotoDialog.PHOTO_PHOTO:
                    Intent intent = new Intent(AccountActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, 100);
                    break;

            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public void initView() {
        titleTitle.setText("账户管理");
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(false);
        imagePicker.setStyle(CropImageView.Style.CIRCLE);
        Integer radius = Integer.valueOf(70);
        radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, getResources().getDisplayMetrics());
        imagePicker.setFocusWidth(radius * 2);
        imagePicker.setFocusHeight(radius * 2);
        imagePicker.setOutPutX(Integer.valueOf(300));
        imagePicker.setOutPutY(Integer.valueOf(300));
        imagePicker.setShowCamera(false);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageUtil.loadImg(accountIcon, SpUtil.getIconUrl());
        accountName.setText(SpUtil.getNickname());
    }

    @OnClick({R.id.title_back, R.id.account_iconlin, R.id.account_namelin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.account_iconlin:
                PhotoDialog photoDialog=new PhotoDialog(AccountActivity.this,handler);
                break;
            case R.id.account_namelin:
                Intent intent=new Intent(AccountActivity.this, UpdateNameActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images!=null && images.size()>0){
                    File file=new File(images.get(0).path);
                    if (file!=null){
                        showDialog();
                        mPresenter.updateIcon(file);
                    }
//                    LogManager.LogShow("----------------",images.get(0).path,LogManager.ERROR);
//                    ImageUtil.loadImg(accountIcon,images.get(0).path);
                }
            }
        }
    }

    @Override
    public void updateIconSuc() {
        dissDialog();
        ToastUtil.show("修改成功");
    }

    @Override
    public void updateIconError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }

    @Override
    public void updateIconTokenError(String s) {
        dissDialog();
        ToosUtils.goReLogin(this);
        ToastUtil.show(s);
    }
}
