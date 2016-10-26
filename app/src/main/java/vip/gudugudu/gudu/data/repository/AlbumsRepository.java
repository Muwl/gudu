package vip.gudugudu.gudu.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.base.util.helper.RxSchedulers;
import vip.gudugudu.gudu.data.Data;
import vip.gudugudu.gudu.data.Repository;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.data.entity.TableShowEntity;

/**
 * 作者：Administrator on 2016/10/26 15:10
 * 描述：
 */

public class AlbumsRepository extends Repository<AlbumsEntity> {
    @Override
    public Observable<Data<AlbumsEntity>> getPageAt(int page) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("pinyin",param.get("type"));
            jsonObject.put("pageIndex",String.valueOf(page));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("----------------",jsonObject.toString());
//        ApiUtil.getStringDataNoToken(ApiUtil.GETALBUMSLIST,jsonObject.toString()).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e("----------------cccc",s);
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                throwable.printStackTrace();
//                Log.e("----------------失败","--------失败"+throwable.getMessage());
//            }
//        });
//        return Observable.just(new Data<>());

        return ApiUtil.getStringDataNoToken(ApiUtil.GETALBUMSLIST,jsonObject.toString())
                .flatMap(new Func1<String, Observable<Data<AlbumsEntity>>>() {
                    @Override
                    public Observable<Data<AlbumsEntity>> call(String s) {
                        Log.e("----------------cccc",s);
                        List<AlbumsEntity> tabs=new Gson().fromJson(s,new TypeToken<List<AlbumsEntity>>() {
                        }.getType());
                        Data<AlbumsEntity> data=new Data<AlbumsEntity>();
                        data.results= (ArrayList<AlbumsEntity>) tabs;
                        return  Observable.just(data).compose(RxSchedulers.io_main());
                    }
                });
    }
}
