package vip.gudugudu.gudu.base.util.helper;

import android.util.Log;

import vip.gudugudu.gudu.C;


/**
 * 日志记录模块
 *
 */
public class LogManager {


//	// 锁，是否关闭Log日志输出
//	public static boolean LogOFF = HycsConst.LogOFF;
//
//
//	// 是否关闭VERBOSE输出
//	public static boolean LogOFF_VERBOSE = HycsConst.LogOFF_VERBOSE;
//
//
//	// 是否关闭debug输出
//	public static boolean LogOFF_DEBUG = HycsConst.LogOFF_DEBUG;


	/**** 5中Log日志类型 *******/
	/** 调试日志类型 */
	public static final int DEBUG = 111;


	/** 错误日志类型 */
	public static final int ERROR = 112;
	/** 信息日志类型 */
	public static final int INFO = 113;
	/** 详细信息日志类型 */
	public static final int VERBOSE = 114;
	/** 警告调试日志类型 */
	public static final int WARN = 115;


	/** 显示，打印日�? */
	public static void LogShow(String Tag, String Message, int Style) {
		if (!C.LOGOFF) {
			switch (Style) {
			case DEBUG:
				if (!C.LOGOFF_DEBUG) {
					Log.d(Tag, Message);
				}
				break;
			case ERROR:
				Log.e(Tag, Message);
				break;
			case INFO:
				Log.i(Tag, Message);
				break;
			case VERBOSE:
				if (!C.LOGOFF_VERBOSE) {
					Log.v(Tag, Message);
				}
				break;
			case WARN:
				Log.w(Tag, Message);
				break;
			}
		}
	}
}
