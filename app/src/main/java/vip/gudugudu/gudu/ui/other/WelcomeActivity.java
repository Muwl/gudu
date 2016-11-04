package vip.gudugudu.gudu.ui.other;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;

import vip.gudugudu.gudu.C;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.entity.ReturnState;
import vip.gudugudu.gudu.ui.main.MainActivity;
import vip.gudugudu.gudu.update.Task;
import vip.gudugudu.gudu.update.UpdataService;
import vip.gudugudu.gudu.update.UpdateEntity;
import vip.gudugudu.gudu.view.dialog.UpdateDialog;

/**
 * @author Mu
 * @date 2015-8-1上午9:00:03
 * @description
 */
public class WelcomeActivity extends Activity {

    private static final int sleepTime = 2000;

    private long currentMin1;

    private long currentMin2;

    private UpdataService.BackgroundDownloadBinder binder;
    ServiceConnection connection;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 111:
                    go();
                    break;
                case UpdateDialog.CUSDIALOG_OK:
                    String fileEx = C.updateUrl.substring(C.updateUrl.lastIndexOf("/") + 1);
                    File updateDir = new File(
                            Environment
                                    .getExternalStorageDirectory(),
                            C.UpdatePath);
                                                /* 创建临时文件 */
                    File myTempFile = new File(
                            updateDir.getPath(),
                            fileEx);

                    if (!myTempFile.exists()) {
                        Toast.makeText(
                                WelcomeActivity.this,
                                "正在下载升级包...",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                    C.updating = true;
                    C.updateData = true;
                    Task task = new Task();
                    task.setUriPath(C.updateUrl);
                    task.setName(C.AppName);
                    task.setId(1);
                    binder.startTask(task);
                    break;
                case UpdateDialog.CUSDIALOG_CANCEL:
                    currentMin2 = System.currentTimeMillis();
                    if (currentMin2 - currentMin1 < 2000) {
                        handler.sendEmptyMessageDelayed(111, 2000
                                - currentMin2 + currentMin1);
                    }else{
                        handler.sendEmptyMessage(111);
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shape);
        initView();
        currentMin1 = System.currentTimeMillis();
        connection = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName name) {
            }

            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (UpdataService.BackgroundDownloadBinder) service;
            }
        };

        Intent myIntent = new Intent(this, UpdataService.class);
        bindService(myIntent, connection, Context.BIND_AUTO_CREATE);

        if (C.updateData) {
            if (!C.updating) {
                setUpdateApp();
            }

        }

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                if (true) {
//                    try {
//                        Thread.sleep(2000);
//                        go();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }).start();
    }


    private void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void go() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 访问服务器是否需要升级App
     */
    private void setUpdateApp() {
        HttpUtils utils = new HttpUtils();
        utils.configTimeout(20000);
        RequestParams requestParams=new RequestParams();
        requestParams.addBodyParameter("reqData","");
        utils.send(
                HttpRequest.HttpMethod.POST, C.UPDATE,requestParams, new RequestCallBack<String>() {

                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        arg0.printStackTrace();
                        currentMin2 = System.currentTimeMillis();
                        if (currentMin2 - currentMin1 < 2000) {
                            handler.sendEmptyMessageDelayed(111, 2000
                                    - currentMin2 + currentMin1);
                        }else{
                            handler.sendEmptyMessage(111);
                        }
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        try {
                            Log.e("--------------", arg0.result + "------");
                            Gson gson = new Gson();
                            ReturnState returnState=gson.fromJson(arg0.result, ReturnState.class);
                            if (returnState.resCode==200){
                                UpdateEntity updateEntity=gson.fromJson(returnState.resData, UpdateEntity.class);
                                if (!getVerCode(WelcomeActivity.this).endsWith(updateEntity.ver)) {
                                    C.updateUrl = updateEntity.downUrl;
                                    ToosUtils.deleteFile(new File(Environment
                                            .getExternalStorageDirectory(),
                                            C.UpdatePath));
                                    UpdateDialog updateDialog = new UpdateDialog(WelcomeActivity.this, handler, "为了不影响您的使用，请更新至最新版本!", -1, -1, null);
                                }else{
                                    currentMin2 = System.currentTimeMillis();
                                    if (currentMin2 - currentMin1 < 2000) {
                                        handler.sendEmptyMessageDelayed(111, 2000
                                                - currentMin2 + currentMin1);
                                    }else{
                                        handler.sendEmptyMessage(111);
                                    }
                                }
                            }else{
                                currentMin2 = System.currentTimeMillis();
                                if (currentMin2 - currentMin1 < 2000) {
                                    handler.sendEmptyMessageDelayed(111, 2000
                                            - currentMin2 + currentMin1);
                                }else{
                                    handler.sendEmptyMessage(111);
                                }
                            }
//                            UpdateState updateState =
//                            if (appCode < Integer.valueOf(updateState.versionCode)) {
//                                Constant.updateUrl = updateState.cloudUrl;
//                                ToosUtils.deleteFile(new File(Environment
//                                        .getExternalStorageDirectory(),
//                                        Constant.UpdatePath));
//                                UpdateDialog updateDialog = new UpdateDialog(WelcomeActivity.this, handler, updateState.content, -1, -1, null);
//                            }else{
//                                currentMin2 = System.currentTimeMillis();
//                                if (currentMin2 - currentMin1 < 2000) {
//                                    handler.sendEmptyMessageDelayed(111, 2000
//                                            - currentMin2 + currentMin1);
//                                }else{
//                                    handler.sendEmptyMessage(111);
//                                }
//                            }
                        } catch (Exception e) {
                            currentMin2 = System.currentTimeMillis();
                            if (currentMin2 - currentMin1 < 2000) {
                                handler.sendEmptyMessageDelayed(111, 2000
                                        - currentMin2 + currentMin1);
                            }else{
                                handler.sendEmptyMessage(111);
                            }
                            e.printStackTrace();
                        }

                    }

                });
    }


    /**
     * 获得当前版本号
     *
     * @param context
     * @return
     */
    private String getVerCode(Context context) {
        String verCode = "";
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "vip.gudugudu.gudu", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;

    }
}
