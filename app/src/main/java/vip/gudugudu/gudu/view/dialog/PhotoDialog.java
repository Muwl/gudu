package vip.gudugudu.gudu.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import vip.gudugudu.gudu.R;


public class PhotoDialog implements OnClickListener {
	public static final int  PHOTO_CAMERA=81;
	public static final int  PHOTO_PHOTO=82;
	private Dialog d = null;
	private View view = null;
	private Context context = null;
	int height;
	private Handler handler;
	private TextView camara;
	private TextView photo;
	private TextView cancel;

	public PhotoDialog(final Context context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
		d = new Dialog(context, R.style.dialog);
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
		view = View.inflate(context, R.layout.photo_dialog, null);
		d.setContentView(view);
		camara = (TextView) d.findViewById(R.id.photo_dialog_camera);
		photo = (TextView) d.findViewById(R.id.photo_dialog_photo);
		cancel = (TextView) d.findViewById(R.id.photo_dialog_cancel);
		camara.setOnClickListener(this);
		photo.setOnClickListener(this);
		cancel.setOnClickListener(this);
		init();
	}

	private void init() {
		Window dialogWindow = d.getWindow();
		LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		lp.width = LayoutParams.MATCH_PARENT;
		// dialogWindow.requestFeature(Window.FEATURE_NO_TITLE);
		lp.height = LayoutParams.WRAP_CONTENT;
		dialogWindow
				.setBackgroundDrawableResource(R.drawable.background_dialog);
		height = lp.height;
		d.show();
		dialogAnimation(d, view, getWindowHeight(), height, false);
	}

	private int getWindowHeight() {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		return dm.widthPixels;
	}

	private void dialogAnimation(final Dialog d, View v, int from, int to,
								 boolean needDismiss) {

		Animation anim = new TranslateAnimation(0, 0, from, to);
		anim.setFillAfter(true);
		anim.setDuration(500);
		if (needDismiss) {
			anim.setAnimationListener(new AnimationListener() {

				public void onAnimationStart(Animation animation) {
				}

				public void onAnimationRepeat(Animation animation) {
				}

				public void onAnimationEnd(Animation animation) {
					d.dismiss();
				}
			});

		}
		v.startAnimation(anim);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.photo_dialog_camera:
			handler.sendEmptyMessage(PHOTO_CAMERA);
			d.dismiss();
			break;
		case R.id.photo_dialog_photo:
			handler.sendEmptyMessage(PHOTO_PHOTO);
			d.dismiss();
			break;
		case R.id.photo_dialog_cancel:
			d.dismiss();
			break;

		default:
			break;
		}
	}

}
