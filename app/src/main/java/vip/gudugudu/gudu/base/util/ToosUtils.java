package vip.gudugudu.gudu.base.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ToosUtils {

    public static boolean isStringEmpty(String msg) {
        return null == msg || "".equals(msg);
    }

    public static boolean isObjectEmpty(Object object) {
        if (object == null) {
            return true;
        }
        return null == String.valueOf(object) || "".equals(String.valueOf(object));
    }

    public static String getTextContent(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static boolean isTextEmpty(TextView textView) {
        return isStringEmpty(getTextContent(textView));
    }

    public static void goReLogin(Context context) {
        // ToastUtils.displayShortToast(context, "验证失败，请重新登录");
        // MyApplication.getInstance().logout(null);
//        ShareDataTool.saveToken(context, "", "", "", "", "", "");
//        if (MyApplication.getInstance().getCustomer() != null) {
//            MyApplication.getInstance().setCustomer(null);
//        }
//        Intent intent = new Intent(context, LoginActivity.class);
//        context.startActivity(intent);
//        ((Activity) context).finish();

    }


    public static boolean checkPwd(String str) {
        return str.length() >= 6;
    }

    public static boolean MatchPhone(String name) {
        Pattern p = Pattern
                .compile("^((1[3,7][0-9])|(15[^4,\\D])|(18[0-3,5-9])|(14[5,7]))\\d{8}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }


    public static boolean isURL(String str) {
        // 转换为小�?
        str = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();

    }

    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return false;
            }
        }
        return true;
    }


}