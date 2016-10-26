package vip.gudugudu.gudu.view.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import vip.gudugudu.gudu.C;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseViewHolder;
import vip.gudugudu.gudu.base.util.ImageUtil;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.data.repository.AlbumsRepository;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class ArticleItemVH extends BaseViewHolder<AlbumsRepository> {


    @Bind(R.id.fhome_item_title)
    TextView fhomeItemTitle;
    @Bind(R.id.fhome_item_time)
    TextView fhomeItemTime;
    @Bind(R.id.fhome_item_image)
    ImageView fhomeItemImage;


    public ArticleItemVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return R.layout.fhome_item;
    }

    @Override
    public void onBindViewHolder(View view, final AlbumsRepository mSubject) {
        ImageUtil.loadImg(fhomeItemImage, mSubject.data.CoverSrc);
        fhomeItemTitle.setText(mSubject.data.Title);
        fhomeItemTime.setText(mSubject.data.AddTime);
//        tv_info.setText(mSubject.data.type);
//        tv_time.setText(mSubject.data.createdAt);
//        view.setOnClickListener((v) ->
//                ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, ArticleActivity.class).putExtra(C.HEAD_DATA, mSubject.data)
//                        , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, image, ArticleActivity.TRANSLATE_VIEW).toBundle())
//        );
    }
}
