package vip.gudugudu.gudu.view.viewholder;

import android.media.Image;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseViewHolder;

public class ArticleHeaderVH extends BaseViewHolder<Image> {

    public ArticleHeaderVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void onBindViewHolder(View view, Image obj) {

    }
//    @Bind(R.id.tv_article)
//    TextView tv_article;
//
//    public ArticleHeaderVH(View v) {
//        super(v);
//    }
//
//    @Override
//    public int getType() {
//        return R.layout.list_item_article;
//    }
//
//    @Override
//    public void onBindViewHolder(View view, Image obj) {
//        String article = obj.article.replace("<br>", "\n").replaceAll(" ", "").replaceAll("//", "");
//        if (!TextUtils.isEmpty(article)) {
//            article = article.substring(article.indexOf("&gt;") + 4, article.length());
//            tv_article.setText(article);
//        }
//    }
}
