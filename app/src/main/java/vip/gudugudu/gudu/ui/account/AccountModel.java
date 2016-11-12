package vip.gudugudu.gudu.ui.account;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;

import rx.functions.Action1;
import vip.gudugudu.gudu.api.Api;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static u.aly.av.H;

/**
 * Created by Administrator on 2016/11/11.
 */

public class AccountModel implements AccountContract.Model {

    private ResponseListener<String> updateIconLister;

    public void setUpdateIconLister(ResponseListener<String> updateIconLister) {
        this.updateIconLister = updateIconLister;
    }

    @Override
    public void updateIcon(File file) {

//        HttpUtils utils=new HttpUtils();
//        RequestParams rp = new RequestParams();
//        rp.addHeader("token", SpUtil.getUserToken());
//        rp.addBodyParameter("file",file);
//        utils.configTimeout(20000);
//        utils.send(HttpRequest.HttpMethod.POST, Api.BASE_URL
//                        + ApiUtil.UPLOADHEAD, rp,  new RequestCallBack<String>() {
//
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                        LogManager.LogShow("-------------",responseInfo.result,LogManager.ERROR);
//                    }
//
//                    @Override
//                    public void onFailure(HttpException e, String s) {
//                        e.printStackTrace();
//                        LogManager.LogShow("-------------",e.getExceptionCode()+"----"+e.getMessage()+"-------"+s,LogManager.ERROR);
//                        updateIconLister.onError("请求失败");
//                    }
//                });

        ApiUtil.upodateFile(ApiUtil.UPLOADHEAD,file).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    updateIconLister.onError(callEntity.result);
                }else if(ApiUtil.RETURN_TROKENERROR.equals(callEntity.state)){
                    updateIconLister.OnTokenError(callEntity.result);
                }else{
                    updateIconLister.onSucess(callEntity.result);

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                updateIconLister.onError("请求失败");
            }
        });
    }
}
