package vip.gudugudu.gudu.base;

import android.util.SparseArray;
import android.view.View;

import vip.gudugudu.gudu.R;

/**
 * 作者：Administrator on 2016/3/10 18:04
 * 描述：ViewHolder基类
 */
public class ViewHolder {
    // I added a generic return type to reduce the casting noise in client code
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag(R.id.tag);
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(R.id.tag,viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}