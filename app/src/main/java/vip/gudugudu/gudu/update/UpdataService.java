package vip.gudugudu.gudu.update;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.webkit.URLUtil;
import android.widget.RemoteViews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import vip.gudugudu.gudu.C;
import vip.gudugudu.gudu.R;


/**
 * @author 穆文磊
 *         <p>
 *         2014年7月29日 22:27:50
 *         <p>
 *         app升级 service
 */

@SuppressLint("DefaultLocale")
public class UpdataService extends Service {
    /**
     * 通知管理
     */
    private NotificationManager mNotificationManager;
    /**
     * 通信对象
     */
    private Binder binder = new BackgroundDownloadBinder();
    /**
     * 总进度
     */
    private final int amountProgress = 100;

    private String strURL = "";
    private String fileEx = "";
    // 文件操作工具
    private FileUtils fileUtil;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressWarnings("deprecation")
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            Task task = (Task) msg.obj;
            switch (msg.what) {
                case FusionCode.download:
                    RemoteViews contentView = task.getNotification().contentView;
                    contentView.setTextViewText(R.id.rate, task.getProgress() + "%");
                    contentView.setProgressBar(R.id.progress, amountProgress, task.getProgress(), false);
                    mNotificationManager.notify(task.getId(), task.getNotification());
                    break;
                case FusionCode.downloaded:
                    // 下载完毕后变换通知形式

                    Notification.Builder builder = new Notification.Builder(getApplicationContext())
                            .setAutoCancel(true)
                            .setContentTitle("下载完成")
                            .setContentText(task.getName() + "已下载完成")
                            .setSmallIcon(R.mipmap.ic_launcher);
//				notification=builder.getNotification();


//				task.getNotification().flags = Notification.FLAG_AUTO_CANCEL;
//				task.getNotification().icon = R.mipmap.ic_launcher;
//				task.getNotification().contentView = null;

//
//				task.getNotification().setLatestEventInfo(getApplicationContext(), "下载完成", task.getName() + "已下载完成",
//						null);
                    mNotificationManager.notify(task.getId(), builder.getNotification());
                    break;
                case FusionCode.cancelDownload:
                    mNotificationManager.cancel(task.getId());
                    break;
                default:
                    break;
            }
        }

    };

    public class BackgroundDownloadBinder extends Binder {
        public void startTask(final Task task) {
            fileUtil = new FileUtils();

            strURL = task.getUriPath();

			/* 取得欲安装程序之文件名称 */
            fileEx = strURL.substring(strURL.lastIndexOf("/") + 1);
//            fileEx = strURL.replace(Constant.ROOT_PATH + "download/", "");

            File updateDir = new File(Environment.getExternalStorageDirectory(), C.UpdatePath);
            if (!updateDir.exists()) {
                updateDir.mkdirs();
            }
            /* 创建临时文件 */
            File myTempFile = new File(updateDir.getPath(), fileEx);

            if (myTempFile.exists()) {
                C.updating = false;
                /* 打开文件进行安装 */
                fileUtil.openFile(myTempFile, getApplicationContext());
            } else {
                setUpNotification(task);
                getFile(strURL, task);
            }

        }
    }

    /**
     * 创建通知
     */
    @SuppressWarnings("deprecation")
    public void setUpNotification(Task task) {
        int icon = R.mipmap.ic_launcher;
        CharSequence tickerText = task.getName();
        long when = System.currentTimeMillis();
        Notification mNotification = new Notification(icon, tickerText, when);
        task.setNotification(mNotification);

        // 放置在运行栏设置
        // mNotification.flags = Notification.FLAG_ONGOING_EVENT;
        RemoteViews contentView = new RemoteViews(getApplicationContext().getPackageName(),
                R.layout.download_notification_layout);
        contentView.setTextViewText(R.id.fileName, task.getName());
        // 制定个性化试图
        mNotification.contentView = contentView;
        // 制定内容意图
        mNotificationManager.notify(task.getId(), mNotification);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 该方法必须返回binder
        return binder;
    }

    /* 处理下载URL文件自定义函数 */
    private void getFile(final String strPath, final Task task) {
        try {
            if (strPath.equals("")) {
                getDataSource(strPath, task);
            }
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        getDataSource(strPath, task);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(r).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 取得远程文件 */
    @SuppressWarnings("resource")
    private void getDataSource(String strPath, Task task) throws Exception {

        int downloadCount = 0;

        if (!URLUtil.isNetworkUrl(strPath)) {
            // updateInfo.setText("错误的URL");
        } else {
            try {
				/* 取得URL */
                URL myURL = new URL(strPath);
				/* 创建连接 */
                URLConnection conn = myURL.openConnection();
                conn.connect();
				/* InputStream 下载文件 */
                InputStream is = conn.getInputStream();
                if (is == null) {
                    throw new RuntimeException("stream is null");
                }

                int totalSize = conn.getContentLength();

                File updateDir = new File(Environment.getExternalStorageDirectory(), C.UpdatePath);
				/* 创建临时文件 */
                File myTempFile = new File(updateDir.getPath(), fileEx);

                if (!updateDir.exists()) {
                    updateDir.mkdirs();
                }

                if (!myTempFile.exists()) {
                    myTempFile.createNewFile();
                }

                myTempFile.getAbsolutePath();
				/* 将文件写入暂存盘 */
                FileOutputStream fos = new FileOutputStream(myTempFile);
                byte buf[] = new byte[128];
                long nowSize = 0;
                do {
                    int numread = is.read(buf);

                    if (numread <= 0) {
                        break;
                    }
                    fos.write(buf, 0, numread);

                    nowSize += numread;
                    // 为了防止频繁的通知导致应用吃紧，百分比增加1才通知一次
//                    Log.e("--------------nowSize",nowSize+"");

                    if ((downloadCount == 0) || (int) (nowSize * 100 / totalSize) - 1 > downloadCount) {
                        downloadCount += 1;
                        task.setProgress((int) (((long) ((long) nowSize * 100)) / totalSize));
                        task.setStatus(FusionCode.download);
                        handler.sendMessage(handler.obtainMessage(task.getStatus(), task));
                    }
                } while (true);
                // 下载完成
                C.updating = false;
                task.setStatus(FusionCode.cancelDownload);
                handler.sendMessage(handler.obtainMessage(task.getStatus(), task));

				/* 打开文件进行安装 */
                fileUtil.openFile(myTempFile, getApplicationContext());
                try {
                    is.close();
                } catch (Exception ex) {

                }
            } catch (Exception e) {

				/* 取得欲安装程序之文件名称 */
//                String fileEx = Constant.updateUrl.replace(Constant.ROOT_PATH + "download/", "");
                String fileEx = C.updateUrl.substring(C.updateUrl.lastIndexOf("/") + 1);
                File updateDir = new File(Environment.getExternalStorageDirectory(), C.UpdatePath);
				/* 创建临时文件 */
                File myTempFile = new File(updateDir.getPath(), fileEx);
                myTempFile.delete();

                e.printStackTrace();
            }
        }
    }
}
