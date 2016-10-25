package vip.gudugudu.gudu.data;

/**
 * Created by Administrator on 2016/10/25.
 */
public interface ResponseListener<T> {
    void onSucess(T data);
    void OnTokenError(String s);
    void onError(String s);


}
