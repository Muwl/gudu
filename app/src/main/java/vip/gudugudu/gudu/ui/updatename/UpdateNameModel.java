package vip.gudugudu.gudu.ui.updatename;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

/**
 * Created by Administrator on 2016/11/12.
 */

public class UpdateNameModel implements UpdateNameContract.Model {

    private ResponseListener<String> updateNameLister;

    public void setUpdateNameLister(ResponseListener<String> updateNameLister) {
        this.updateNameLister = updateNameLister;
    }

    @Override
    public void updateName(String name) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("nickname",name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiUtil.getStringDataToken(ApiUtil.CHANGEUSER,jsonObject.toString()).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    updateNameLister.onError(callEntity.result);
                }else if(ApiUtil.RETURN_TROKENERROR.equals(callEntity.state)){
                    updateNameLister.OnTokenError(callEntity.result);
                }else{
                    updateNameLister.onSucess(name);

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                updateNameLister.onError("请求失败");
            }
        });
    }
}
