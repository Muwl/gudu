package vip.gudugudu.gudu.update;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.webkit.URLUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import vip.gudugudu.gudu.C;


/**
 * @author 穆文磊
 *         <p>
 *         2014年7月29日 22:24:24
 *         <p>
 *         文件操作
 */

@SuppressLint("DefaultLocale")
public class FileUtils {
    private String SDPATH = null;

    public String getSDPATH() {
        return SDPATH;
    }

    public FileUtils() {
        // 获得当前外部存储设备SD卡的目录
        SDPATH = Environment.getExternalStorageDirectory() + "/";
    }

    /**
     * 创建sdcard文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public File createFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 创建sdcard目录
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public File createDir(String fileName) throws IOException {
        File dir = new File(SDPATH + fileName);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断sdcard文件是否存在
     *
     * @param fileName
     * @return
     */
    public boolean isExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 删除sd中的文件
     *
     * @param fileName
     */
    public void deleteFile(String fileName) {
        File file = new File(SDPATH + fileName);
        file.delete();
    }

    /**
     * 将文件写入sdcard
     *
     * @param path
     * @param fileName
     * @param inputstream
     * @return
     */
    public File writeToSDPATHFromInput(String path, String fileName,
                                       InputStream inputstream) {
        File file = null;
        OutputStream outputstream = null;
        try {
            createDir(path);
            file = createFile(path + fileName);
            outputstream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            // 将输入流中的内容先输入到buffer中缓存，然后用输出流写到文件中, /*真机测试，这段可能有问题
            /*
             * while ((inputstream.read(buffer)) != -1) {
			 * outputstream.write(buffer); }
			 */
            // 用这段
            int length;
            while ((length = (inputstream.read(buffer))) > 0) {
                outputstream.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputstream.close();
                outputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("捕获空指针异常----FileUtils.java  123行");
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 取得远程文件
     *
     * @param strPath 下载路径
     *                文件拓展名
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public File getDataSource(String strPath) throws Exception {
		/* 取得欲安装程序之文件名称 */
        String fileNa = strPath.substring(strPath.lastIndexOf("/") + 1,
                strPath.length());
		/* 创建临时文件 */
        File myTempFile = null;

        if (!URLUtil.isNetworkUrl(strPath)) {
            // updateInfo.setText("错误的URL");
        } else {
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

            File updateDir = new File(
                    Environment.getExternalStorageDirectory(),
                    C.UpdatePath);
			/* 创建临时文件 */
            myTempFile = new File(updateDir.getPath(), fileNa);

            if (!updateDir.exists()) {
                updateDir.mkdirs();
            }

            if (!myTempFile.exists()) {
                myTempFile.createNewFile();
            }

            myTempFile.getAbsolutePath();
			/* 将文件写入暂存盘 */
            @SuppressWarnings("resource")
            FileOutputStream fos = new FileOutputStream(myTempFile);
            byte buf[] = new byte[1024 * 4];
            long nowSize = 0;

			/* 网友提供 begin */
            int length;
            while ((length = (is.read(buf))) > 0) {
                fos.write(buf, 0, length);
            }
			/* 网友提供 end */

            //
            // do {
            // int numread = is.read(buf);
            //
            // if (numread <= 0) {
            // break;
            // }
            // fos.write(buf, 0, numread);
            //
            // } while (true);
            try {
                is.close();
            } catch (Exception ex) {
                myTempFile = null;
            }
        }

        return myTempFile;
    }

    /**
     * 获得文件拓展名
     *
     * @param file 文件
     * @return
     */
    @SuppressLint("DefaultLocale")
    public String getMimeType(File file) {
        String type = "";
        String fname = file.getName();
        // 获得扩展名
        String end = fname
                .substring(fname.lastIndexOf('.') + 1, fname.length())
                .toLowerCase();
        // 按扩展名的类型决定MimeType
        if ("m4a".equals(end) || "mp3".equals(end) || "mid".equals(end)
                || "xmf".equals(end) || "ogg".equals(end) || "wav".equals(end)) {
            type = "audio";
        } else if ("3gp".equals(end) || "mp4".equals(end)) {
            type = "video";
        } else if ("jpg".equals(end) || "gif".equals(end) || "png".equals(end)
                || "jpeg".equals(end) || "bmp".equals(end)) {
            type = "image";
        } else if ("apk".equals(end)) {
            type = "application/vnd.android.package-archive";
        } else {
            type = "*";
        }
        if ("apk".equals(end)) {

        } else {
            type += "/*";
        }
        return type;
    }

    /**
     * 打开下载的apk
     *
     * @param file
     */
    public void openFile(File file, Context mContext) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = getMimeType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        mContext.startActivity(intent);
    }

}