package vip.gudugudu.gudu;

import android.app.Dialog;

import vip.gudugudu.gudu.api.Api;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class C {
    //base
    public static final int PAGE_COUNT = 10;

    // intent
    public static final String HEAD_DATA = "data";
    public static final String VH_CLASS = "vh";
    // RxBusEventName

    public static final String EVENT_DEL_ITEM = "delete_item";
    public static final String EVENT_UPDATE_ITEM = "update_item";

    // 锁，是否关闭Log日志输出
    public static final boolean LOGOFF = false;
    // 是否关闭VERBOSE输出
    public static final boolean LOGOFF_VERBOSE = false;
    // 是否关闭debug输出
    public static final boolean LOGOFF_DEBUG = false;


    public static String updateUrl;
    public static Dialog updateDialog;
    public static final String AppName = "咕嘟咕嘟";
    public static String UpdatePath = "/gudu/Update/";
    public static boolean updateData = true;
    public static boolean updating = false;

    public static final String UPDATE = Api.BASE_URL+"GetVersion";




}
