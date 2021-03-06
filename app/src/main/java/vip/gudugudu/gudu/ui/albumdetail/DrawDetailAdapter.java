package vip.gudugudu.gudu.ui.albumdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.ViewHolder;
import vip.gudugudu.gudu.base.util.DensityUtil;
import vip.gudugudu.gudu.base.util.ImageUtil;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.data.entity.PicturesEntity;
import vip.gudugudu.gudu.ui.other.PhotoShowActivity;

import static android.R.attr.id;

/**
 * 作者：Administrator on 2016/10/27 15:07
 * 描述：
 */

public class DrawDetailAdapter extends BaseAdapter {
    private Context context;
    private AlbumDetailEntity entity;
    private int type0 = 1;
    private int type1 = 2;
    private int type2 = 3;
    private Handler handler;
    private BitmapUtils bitmapUtils;

    public DrawDetailAdapter(Context context, AlbumDetailEntity entity, Handler handler) {
        this.entity = entity;
        this.context = context;
        this.handler = handler;
        bitmapUtils=new BitmapUtils(context);
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.loding);
        bitmapUtils.configDefaultLoadingImage(R.mipmap.loding);
    }

    @Override
    public int getCount() {
        return entity.Pictures.size() + entity.RecommentsAlbums.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return type0;
        } else if (position <= entity.Pictures.size()) {
            return type1;
        } else {
            return type2;
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        int type = getItemViewType(position);
        if (type == type0) {
            convertView = null;
            if (convertView == null
                    || !convertView.getTag(R.id.tag).getClass()
                    .equals(ViewHolder1.class)) {
                convertView = View.inflate(context, R.layout.drawdetail_tipitem,
                        null);
                holder1 = new ViewHolder1();
                holder1.tip = (TextView) convertView.findViewById(R.id.drawdetail_tip);
                convertView.setTag(R.id.tag, holder1);
            } else {
                holder1 = (ViewHolder1) convertView.getTag(R.id.tag);
            }
        } else if (type == type1) {
            if (convertView == null
                    || !convertView.getTag(R.id.tag).getClass()
                    .equals(ViewHolder2.class)) {
                convertView = View.inflate(context, R.layout.drawdetail_imageitem,
                        null);
                holder2 = new ViewHolder2();
                holder2.imageView = (ImageView) convertView.findViewById(R.id.recitem_image);
                convertView.setTag(R.id.tag, holder2);
            } else {
                holder2 = (ViewHolder2) convertView.getTag(R.id.tag);
            }
        } else if (type == type2) {
            if (convertView == null
                    || !convertView.getTag(R.id.tag).getClass()
                    .equals(ViewHolder3.class)) {
                convertView = View.inflate(context, R.layout.drawdetail_recitem,
                        null);
                holder3 = new ViewHolder3();
                holder3.rectext = (TextView) convertView.findViewById(R.id.recitem_text);
                holder3.div = (ImageView) convertView.findViewById(R.id.recitem_div);
                holder3.tip = (TextView) convertView.findViewById(R.id.recitem_tip);
                holder3.tiplin =  convertView.findViewById(R.id.recitem_tiplin);
                holder3.collect = (ImageView) convertView.findViewById(R.id.recitem_collect);
                convertView.setTag(R.id.tag, holder3);
            } else {
                holder3 = (ViewHolder3) convertView.getTag(R.id.tag);
            }
        }
        if (type == type0) {
            holder1.tip.setText(entity.AlbumTitle);
        } else if (type == type1) {
            if(!entity.Pictures.get(position - 1).Src.equals(holder2.imageView.getTag())) {
                holder2.imageView.setTag(entity.Pictures.get(position - 1).Src);
                bitmapUtils.display(holder2.imageView, entity.Pictures.get(position - 1).Src);
            }

            holder2.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<String> strings=new ArrayList<String>();
                    for (PicturesEntity picturesEntity:entity.Pictures){
                        strings.add(picturesEntity.Src);
                    }
                    Intent intent=new Intent(context, PhotoShowActivity.class);
                    intent.putExtra("photo", (Serializable) strings);
                    intent.putExtra("position",position - 1);
                    context.startActivity(intent);
                }
            });

//            ImageUtil.loadImg(holder2.imageView, entity.Pictures.get(position - 1).Src);
        } else if (type == type2) {
            holder3.rectext.setText(entity.RecommentsAlbums.get(position - 1 - entity.Pictures.size()).Title);
            holder3.rectext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,AlbumDetailActivity.class);
                    intent.putExtra("albumId",entity.RecommentsAlbums.get(position - 1 - entity.Pictures.size()).AlbumId);
                    context.startActivity(intent);
                }
            });

            if(position==entity.Pictures.size()+1){
                holder3.tiplin.setVisibility(View.VISIBLE);
            }else{
                holder3.tiplin.setVisibility(View.GONE);
            }
            holder3.collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler.sendEmptyMessage(AlbumDetailActivity.COLLECT_FLAG);
                }
            });

            if (position==entity.Pictures.size() + entity.RecommentsAlbums.size()){
                holder3.div.setVisibility(View.GONE);
            }else{
                holder3.div.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    class ViewHolder1 {
        public TextView tip;
    }

    class ViewHolder2 {
        public ImageView imageView;
    }

    class ViewHolder3 {
        public TextView rectext;
        public View tiplin;
        public TextView tip;
        public ImageView collect;
        public ImageView div;
    }

}
