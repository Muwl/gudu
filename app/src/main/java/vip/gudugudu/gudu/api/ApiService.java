package vip.gudugudu.gudu.api;


import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import vip.gudugudu.gudu.data.entity.ReturnState;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {

    @POST("{path}")
    Observable<ReturnState> getNoTokenData(@Path("path") String path, @Query("reqData") String reqData);

    @POST("{path}")
    Observable<String> getNoTokenData1(@Path("path") String path, @Query("reqData") String reqData);


}
