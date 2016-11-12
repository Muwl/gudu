package vip.gudugudu.gudu.ui.resertpwd;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.MD5Util;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ResertModel implements ResertContract.Model {

    private ResponseListener<String> resertPwdLister;

    public void setResertPwdLister(ResponseListener<String> resertPwdLister) {
        this.resertPwdLister = resertPwdLister;
    }

    @Override
    public void resertPwd(String mobile, String pwd) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("mobile",mobile);
            jsonObject.put("newpass", MD5Util.MD5(pwd));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiUtil.getStringDataNoToken(ApiUtil.CHANGEPASS,jsonObject.toString()).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    resertPwdLister.onError(callEntity.result);
                }else{
                    try{
                        resertPwdLister.onSucess("修改成功,请重新登录");
                    }catch (Exception e){
                        resertPwdLister.onError("修改失败");
                    }

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                resertPwdLister.onError("请求失败");
            }
        });

    }

}
