package vip.gudugudu.gudu.ui.albumdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.ViewHolder;
import vip.gudugudu.gudu.base.util.ImageUtil;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.data.entity.PicturesEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class DarwDetailRecAdapter extends BaseAdapter {

    private Context context;
    private List<AlbumsEntity> entities;

    public DarwDetailRecAdapter(Context context, List<AlbumsEntity> entities) {
        this.context = context;
        this.entities = entities;
    }


    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int i) {
        return entities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drawdetail_recitem, parent, false);
        }
        TextView textView= ViewHolder.get(convertView,R.id.recitem_text);
        textView.setText(entities.get(position).Title);
        return convertView;
    }
}
