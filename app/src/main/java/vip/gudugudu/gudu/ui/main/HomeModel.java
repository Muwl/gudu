package vip.gudugudu.gudu.ui.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
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
        ApiUtil.getStringDataNoToken("GetAllClassify","").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                if (ToosUtils.isStringEmpty(s) || ApiUtil.RETURN_ERROR.equals(s)){
                    tableListLister.onError("请求失败");
                }else{
                    try{
                        List<TableShowEntity> tabs=new Gson().fromJson(s,new TypeToken<List<TableShowEntity>>() {
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
