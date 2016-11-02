package vip.gudugudu.gudu.api;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import vip.gudugudu.gudu.data.entity.ReturnState;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("{path}")
    Observable<ReturnState> getNoTokenData(@Path("path") String path, @Field("reqData") String reqData);

    @FormUrlEncoded
    @POST("{path}")
    Observable<ReturnState> getTokenData(@Path("path") String path, @Header("token") String token, @Field("reqData") String reqData);
//    Observable<ReturnState> getNoTokenData(@Path("path") String path, @Query("reqData") String reqData);



}
