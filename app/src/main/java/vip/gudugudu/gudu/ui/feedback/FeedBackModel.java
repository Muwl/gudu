package vip.gudugudu.gudu.ui.feedback;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.MD5Util;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.RegisterEntity;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class FeedBackModel implements FeedBackContract.Model {

    private ResponseListener<String> feedBackLister;

    public void setFeedBackLister(ResponseListener<String> feedBackLister) {
        this.feedBackLister = feedBackLister;
    }

    @Override
    public void feedback(String content, String contacts) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("content",content);
            jsonObject.put("contacts", contacts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiUtil.getStringDataToken(ApiUtil.FEEDBACK,jsonObject.toString()).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    feedBackLister.onError(callEntity.result);
                }else if(ApiUtil.RETURN_TROKENERROR.equals(callEntity.state)){
                    feedBackLister.OnTokenError(callEntity.result);
                }else{
                    feedBackLister.onSucess("");

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                feedBackLister.onError("请求失败");
            }
        });
    }
}
