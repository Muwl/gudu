package vip.gudugudu.gudu.view.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseViewHolder;
import vip.gudugudu.gudu.base.util.ImageUtil;
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.data.repository.AlbumsRepository;
import vip.gudugudu.gudu.data.repository.CollectAlbumsRepository;
import vip.gudugudu.gudu.ui.albumdetail.AlbumDetailActivity;
import vip.gudugudu.gudu.view.dialog.CustomeDialog;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class CollectItemVH extends BaseViewHolder<CollectAlbumsRepository> {


    @Bind(R.id.fhome_item_title)
    TextView fhomeItemTitle;
    @Bind(R.id.fhome_item_time)
    TextView fhomeItemTime;
    @Bind(R.id.fhome_item_image)
    ImageView fhomeItemImage;

    Handler handler;


    public CollectItemVH(View v) {
        super(v);
    }


    @Override
    public int getType() {
        return R.layout.fhome_item;
    }

    public void setHandler(Handler handler){
        this.handler=handler;
    }

    @Override
    public void onBindViewHolder(View view, final CollectAlbumsRepository mSubject) {
        ImageUtil.loadImg(fhomeItemImage, mSubject.data.CoverSrc);
        fhomeItemTitle.setText(mSubject.data.Title);
        fhomeItemTime.setText(mSubject.data.AddTime);
//        tv_info.setText(mSubject.data.type);
//        tv_time.setText(mSubject.data.createdAt);
        view.setOnClickListener((v) ->
                ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, AlbumDetailActivity.class).putExtra("albumId", mSubject.data.AlbumId)
                        , null)
        );


//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                LogManager.LogShow("----------","changanl---",LogManager.ERROR);
//                if (handler!=null){
//                    CustomeDialog customeDialog=new CustomeDialog(mContext,handler,"确定删除此收藏？",mSubject.data);
//                }
//                return true;
//            }
//        });
    }
}
