package vip.gudugudu.gudu.ui.main;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;
import vip.gudugudu.gudu.data.entity.TableShowEntity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2016/10/16.
 */

public class HomeModel implements HomeContract.Model{

    private ResponseListener<List<TableShowEntity>> tableListLister;

    public void setTableListLister(ResponseListener<List<TableShowEntity>> tableListLister) {
        this.tableListLister = tableListLister;
    }

    @Override
    public void getTabs() {
        ApiUtil.getStringDataNoToken(ApiUtil.GETALLCLASSIFY,"").subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    tableListLister.onError(callEntity.result);
                }else{
                    try{
                        List<TableShowEntity> tabs=new Gson().fromJson(callEntity.result,new TypeToken<List<TableShowEntity>>() {
                        }.getType());
                        tableListLister.onSucess(tabs);
                    }catch (Exception e){
                        tableListLister.onError("请求失败");
                    }

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                tableListLister.onError("请求失败");
            }
        });

    }


}
