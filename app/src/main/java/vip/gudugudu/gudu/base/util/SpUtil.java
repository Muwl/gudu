package vip.gudugudu.gudu.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.data.entity.RegisterEntity;


/**
 * Created by Administrator on 2016/4/5.
 */
public class SpUtil {
    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveUser(RegisterEntity registerEntity){
        SharedPreferences.Editor editor=prefs.edit();
        if (registerEntity!=null){
            editor.putInt("uid",registerEntity.uid);
            editor.putString("uname",registerEntity.uname);
            editor.putString("umobile",registerEntity.umobile);
            editor.putString("nickname",registerEntity.nickname);
            editor.putString("IconUrl",registerEntity.IconUrl);
            editor.putString("UserToken",registerEntity.UserToken);
        }else{
            editor.putInt("uid",0);
            editor.putString("uname","");
            editor.putString("umobile","");
            editor.putString("nickname","");
            editor.putString("IconUrl","");
            editor.putString("UserToken","");
        }
        editor.commit();
    }

    public static int getUid(){
        return prefs.getInt("uid",0);
    }
    public static String getUname(){
        return prefs.getString("uname","");
    }
    public static String getUmobile(){
        return prefs.getString("umobile","");
    }
    public static String getNickname(){
        return prefs.getString("nickname","");
    }
    public static String getIconUrl(){
        return prefs.getString("IconUrl","");
    }
    public static String getUserToken(){
        return prefs.getString("UserToken","");
    }

//    public static boolean isNight() {
//        return prefs.getBoolean("isNight", false);
//    }
//
//    public static void setNight(Context context, boolean isNight) {
//        prefs.edit().putBoolean("isNight", isNight).commit();
//        if (context instanceof BaseActivity)
//            ((BaseActivity) context).reload();
//    }


}
