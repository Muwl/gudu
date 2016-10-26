package vip.gudugudu.gudu.base.util;

import android.text.TextUtils;
import android.util.Log;


import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import vip.gudugudu.gudu.api.Api;
import vip.gudugudu.gudu.base.BaseEntity;
import vip.gudugudu.gudu.base.util.helper.RxSchedulers;
import vip.gudugudu.gudu.data.Pointer;
import vip.gudugudu.gudu.data.entity.ReturnState;

import static android.R.attr.name;

/**
 * Created by baixiaokang on 16/5/5.
 */
public class ApiUtil {

    // 联网返回成功
    public static final int RETURN_OK = 200;
    //验证失败
    public static final int RETURN_ERR = 201;
    // 联网Token失败
    public static final int RETURN_TOKENERR = 202;

    public static final String RETURN_NULL = "123456789-null";
    public static final String RETURN_ERROR = "123456789-error";
    public static final String RETURN_TROKENERROR = "123456789-tokenerror";

    public static final String[] RETURN_STRINGS = new String[]{RETURN_NULL};// 解析后返回数据的状态
    public static final List<String> RETURN_LSITS = Arrays
            .asList(RETURN_STRINGS);


    public static final String LOGIN = "login";// 登录方法
    public static final String GETALLCLASSIFY = "GetAllClassify";// 获取首页表头的分类
    public static final String GETALBUMSLIST = "GetAlbumsList";// 获取首页分类下面的列表


    public static  Observable<String> getStringDataNoToken(String path,String upData) {
       return Api.getInstance().service
                .getNoTokenData(path, upData)
                .flatMap(new Func1<ReturnState, Observable<String>>() {
                    @Override
                    public Observable<String> call(ReturnState returnState) {
                        Log.e("----------------api","----------------");
                        if (returnState==null){
                            return  Observable.just(RETURN_ERROR).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_ERR){
                            return Observable.just(RETURN_ERROR).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_TOKENERR){
                            return Observable.just(RETURN_TROKENERROR).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_OK && ToosUtils.isStringEmpty(returnState.resData)){
                            return Observable.just(RETURN_NULL).compose(RxSchedulers.io_main());
                        }else{
                            return Observable.just(returnState.resData).compose(RxSchedulers.io_main());
                        }
                    }

                }).compose(RxSchedulers.io_main());

    }


    public static  Observable<String> getStringDataNoToken1(String path,String upData) {
        return Api.getInstance().service
                .getNoTokenData1(path, upData)
                .compose(RxSchedulers.io_main());

    }
}
