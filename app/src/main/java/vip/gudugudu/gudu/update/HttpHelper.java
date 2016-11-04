package vip.gudugudu.gudu.update;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 穆文磊
 *         <p>
 *         2014年7月29日 22:25:56
 *         <p>
 *         post get 请求
 */
public class HttpHelper {
    // 设置请求超时10秒钟
    private static final int REQUEST_TIMEOUT = 15 * 1000;
    // 设置等待数据超时时间10秒钟
    private static final int SO_TIMEOUT = 15 * 1000;

    public static String sendHttpRequest(String pUrl) {

        String result = null;
        result = doGet(pUrl);
        return result;
    }

    /**
     * Get请求
     */
    public static String doGet(String url) {
        String result = null;

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        // GET
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                Log.i("GET", "Bad Request!");
            }

            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
        }

        return result;
    }

}
