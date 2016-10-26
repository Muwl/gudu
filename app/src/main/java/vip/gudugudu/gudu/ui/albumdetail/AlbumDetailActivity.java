package vip.gudugudu.gudu.ui.albumdetail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.view.widget.MyListView;

/**
 * Created by Administrator on 2016/10/26.
 */

public class AlbumDetailActivity extends BaseActivity<AlbumDetailPresenter, AlbumDetailModel> implements AlbumDetailContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.drawdetail_share)
    ImageView drawdetailShare;
    @Bind(R.id.drawdetail_collect)
    ImageView drawdetailCollect;
    @Bind(R.id.drawdetail_discuss)
    EditText drawdetailDiscuss;
    @Bind(R.id.drawdetail_bom)
    RelativeLayout drawdetailBom;
    @Bind(R.id.drawdetail_bomlin)
    ImageView drawdetailBomlin;
    @Bind(R.id.drawdetail_tip)
    TextView drawdetailTip;
    @Bind(R.id.drawdetail_drawlist)
    MyListView drawdetailDrawlist;
    @Bind(R.id.drawdetail_attennum)
    TextView drawdetailAttennum;
    @Bind(R.id.drawdetail_reclist)
    MyListView drawdetailReclist;
    @Bind(R.id.drawdetail_discusslist)
    MyListView drawdetailDiscusslist;

    private int albumId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drawdetail;
    }

    @Override
    public void initView() {
        titleTitle.setText("详情");
        albumId=getIntent().getIntExtra("albumId",0);
        showDialog();
        mPresenter.getDetail(String.valueOf(albumId));

    }


    @OnClick({R.id.title_back, R.id.title_send, R.id.drawdetail_share, R.id.drawdetail_collect, R.id.drawdetail_attennum})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                break;
            case R.id.drawdetail_share:
                break;
            case R.id.drawdetail_collect:
                break;
            case R.id.drawdetail_attennum:
                break;
        }
    }

    @Override
    public void getDetailView(AlbumDetailEntity detailEntity) {
        Log.e("===========",new Gson().toJson(detailEntity));
        drawdetailTip.setText(detailEntity.AlbumTitle);
        DarwDetailImageAdapter imageAdapter=new DarwDetailImageAdapter(this,detailEntity.Pictures);
        drawdetailDrawlist.setAdapter(imageAdapter);
        DarwDetailRecAdapter recAdapter=new DarwDetailRecAdapter(this,detailEntity.RecommentsAlbums);
        drawdetailReclist.setAdapter(recAdapter);
    }

    @Override
    public void getTableError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }
}
