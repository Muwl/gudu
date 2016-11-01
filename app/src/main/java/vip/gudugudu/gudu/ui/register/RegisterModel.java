package vip.gudugudu.gudu.ui.register;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.MD5Util;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.data.entity.RegisterEntity;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class RegisterModel implements RegisterContract.Model {

    private ResponseListener<RegisterEntity> registerLister;

    public void setGetDetailLister(ResponseListener<RegisterEntity> registerLister) {
        this.registerLister = registerLister;
    }

    @Override
    public void register(String mobile, String pwd) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("mobile",mobile);
            jsonObject.put("pwd", MD5Util.MD5(pwd));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiUtil.getStringDataNoToken(ApiUtil.REGISTER,jsonObject.toString()).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    registerLister.onError(callEntity.result);
                }else{
                    try{
                        RegisterEntity registerEntity=new Gson().fromJson(callEntity.result,RegisterEntity.class);
                        registerLister.onSucess(registerEntity);
                    }catch (Exception e){
                        registerLister.onError("请求失败");
                    }

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                registerLister.onError("请求失败");
            }
        });

    }
}
