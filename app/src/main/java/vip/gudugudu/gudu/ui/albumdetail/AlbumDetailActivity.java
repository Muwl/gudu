package vip.gudugudu.gudu.ui.albumdetail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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

    @Bind(R.id.drawdetail_list)
    ListView drawdetailList;

//    @Bind(R.id.drawdetail_tip)
//    TextView drawdetailTip;
//    @Bind(R.id.drawdetail_drawlist)
//    MyListView drawdetailDrawlist;
//    @Bind(R.id.drawdetail_attennum)
//    TextView drawdetailAttennum;
//    @Bind(R.id.drawdetail_reclist)
//    MyListView drawdetailReclist;
//    @Bind(R.id.drawdetail_discusslist)
//    MyListView drawdetailDiscusslist;

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


    @OnClick({R.id.title_back, R.id.title_send, R.id.drawdetail_share, R.id.drawdetail_collect})
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
        }
    }

    @Override
    public void getDetailView(AlbumDetailEntity detailEntity) {
        dissDialog();
        DrawDetailAdapter adapter=new DrawDetailAdapter(this,detailEntity);
        drawdetailList.setAdapter(adapter);
//        drawdetailTip.setText(detailEntity.AlbumTitle);
//        DarwDetailImageAdapter imageAdapter=new DarwDetailImageAdapter(this,detailEntity.Pictures);
//        drawdetailDrawlist.setAdapter(imageAdapter);
//        DarwDetailRecAdapter recAdapter=new DarwDetailRecAdapter(this,detailEntity.RecommentsAlbums);
//        drawdetailReclist.setAdapter(recAdapter);
//        setListViewHeightBasedOnChildren(drawdetailDrawlist);
    }

    @Override
    public void getTableError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for(int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
