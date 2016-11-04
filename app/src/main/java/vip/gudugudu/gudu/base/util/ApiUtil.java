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
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.base.util.helper.RxSchedulers;
import vip.gudugudu.gudu.data.Pointer;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;
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
    public static final String RETURN_SUC = "123456789-ok";
    public static final String RETURN_ERROR = "123456789-error";
    public static final String RETURN_TROKENERROR = "123456789-tokenerror";

    public static final String[] RETURN_STRINGS = new String[]{RETURN_NULL};// 解析后返回数据的状态
    public static final List<String> RETURN_LSITS = Arrays
            .asList(RETURN_STRINGS);


    public static final String GETALLCLASSIFY = "GetAllClassify";// 获取首页表头的分类
    public static final String GETALBUMSLIST = "GetAlbumsList";// 获取首页分类下面的列表
    public static final String GETALBUMSDETIAL = "GetAlbumsDetial";// 获取套图详情
    public static final String REGISTER = "Register";// 获取套图详情
    public static final String LOGIN = "Login";// 登录方法
    public static final String FEEDBACK = "FeedBack";// 反馈
    public static final String ADDCOLLECT = "addCollect";// 对套图进行收藏
    public static final String GETCOLLECT = "GetCollect";// 获取用户收藏
    public static final String DELCOLLECT = "delCollect";// 删除用户收藏


    public static  Observable<ReturnCallEntity> getStringDataNoToken(String path, String upData) {
       return Api.getInstance().service
                .getNoTokenData(path, upData)
                .flatMap(new Func1<ReturnState, Observable<ReturnCallEntity>>() {
                    @Override
                    public Observable<ReturnCallEntity> call(ReturnState returnState) {

                        if (returnState==null){
                            return  Observable.just(new ReturnCallEntity(RETURN_ERROR,"请求失败")).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_ERR){
                            return Observable.just(new ReturnCallEntity(RETURN_ERROR,returnState.resMsg)).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_TOKENERR){
                            return Observable.just(new ReturnCallEntity(RETURN_TROKENERROR,returnState.resMsg)).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_OK && ToosUtils.isStringEmpty(returnState.resData)){
                            return Observable.just(new ReturnCallEntity(RETURN_NULL,"")).compose(RxSchedulers.io_main());
                        }else{
                            Log.e("-----返回",returnState.resData);
                            return Observable.just(new ReturnCallEntity(RETURN_SUC,returnState.resData)).compose(RxSchedulers.io_main());
                        }
                    }

                }).compose(RxSchedulers.io_main());

    }

    public static  Observable<ReturnCallEntity> getStringDataToken(String path, String upData) {
        if (!ToosUtils.isStringEmpty(upData)){
            LogManager.LogShow("上传参数---",upData,LogManager.ERROR);
            LogManager.LogShow("token---",SpUtil.getUserToken(),LogManager.ERROR);
        }

       return Api.getInstance().service
                .getTokenData(path,SpUtil.getUserToken(), upData)
                .flatMap(new Func1<ReturnState, Observable<ReturnCallEntity>>() {
                    @Override
                    public Observable<ReturnCallEntity> call(ReturnState returnState) {

                        if (returnState==null){
                            return  Observable.just(new ReturnCallEntity(RETURN_ERROR,"请求失败")).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_ERR){
                            return Observable.just(new ReturnCallEntity(RETURN_ERROR,returnState.resMsg)).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_TOKENERR){
                            return Observable.just(new ReturnCallEntity(RETURN_TROKENERROR,returnState.resMsg)).compose(RxSchedulers.io_main());
                        }
                        if (returnState.resCode==RETURN_OK && ToosUtils.isStringEmpty(returnState.resData)){
                            return Observable.just(new ReturnCallEntity(RETURN_NULL,"")).compose(RxSchedulers.io_main());
                        }else{
                            Log.e("-----返回",returnState.resData);
                            return Observable.just(new ReturnCallEntity(RETURN_SUC,returnState.resData)).compose(RxSchedulers.io_main());
                        }
                    }

                }).compose(RxSchedulers.io_main());

    }



}
