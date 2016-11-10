package vip.gudugudu.gudu.ui.other;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vip.gudugudu.gudu.C;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.FileUtil;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.helper.LogManager;
import vip.gudugudu.gudu.view.widget.CirclePageIndicator;
import vip.gudugudu.gudu.view.widget.HackyViewPager;
import vip.gudugudu.gudu.view.widget.photo.PhotoView;

import static android.R.attr.name;

/**
 * @author Mu
 * @date 2015-6-1 上午11:00:53
 * @Description 图片查看页面
 */
public class PhotoShowActivity extends BaseActivity {

	private HackyViewPager pager;

	private CirclePageIndicator mIndicator;

	private List<String> photoEntities;

	private int position;

	private TextView title;

	private ImageView back;

	private TextView save;

	public static final String FilePath = Environment.getExternalStorageDirectory() + "/gudu/";

	// private ImageLoaderUtil2 loaderUtil2;

	private static final String STATE_POSITION = "STATE_POSITION";

	@Override
	public int getLayoutId() {
		return R.layout.ac_image_pager;
	}



	public void initView() {
		Intent intent = getIntent();
		position = intent.getIntExtra("position", 0);
		photoEntities = (List<String>) intent
				.getSerializableExtra("photo");
		pager = (HackyViewPager) findViewById(R.id.pager);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
//		LogManager.LogShow("-------", new Gson().toJson(photoEntities), LogManager.ERROR);

		back = (ImageView) findViewById(R.id.image_page_back);
		title = (TextView) findViewById(R.id.image_page_title);
		save = (TextView) findViewById(R.id.image_page_save);
		pager.setAdapter(new ImagePagerAdapter(photoEntities, this));
		pager.setCurrentItem(position);
		mIndicator.setViewPager(pager);


		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				LogManager.LogShow("---------","1111111111111111",LogManager.ERROR);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				title.setText((arg0 + 1) + "/" + photoEntities.size());
				LogManager.LogShow("---------", "2222222222222", LogManager.ERROR);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				LogManager.LogShow("---------","3333333333333",LogManager.ERROR);
			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				saveImageView(photoEntities.get(pager.getCurrentItem()));
				showDialog();
			}
		});

	}


	private void saveImageView(String url){
		HttpUtils utils=new HttpUtils();
		utils.configTimeout(20000);
		String name = url.substring(url.lastIndexOf("/") + 1);
		try {
            FileUtil.CreateFileOrDel(FilePath +name, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.e("-------------",url);

		utils.download(url, FilePath +name, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				dissDialog();
				ToastUtil.show("保存成功！");
			}

			@Override
			public void onFailure(HttpException e, String s) {
				ToastUtil.show("保存失败！");
				e.printStackTrace();
				dissDialog();
			}
		});
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends PagerAdapter {

		private List<String> paths;
		private LayoutInflater inflater;
		private Context mContext;
		private BitmapUtils utils;

		ImagePagerAdapter(List<String> paths, Context context) {
			this.paths = paths;
			this.mContext = context;
			inflater = getLayoutInflater();
			utils = new BitmapUtils(context);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return paths.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = inflater.inflate(R.layout.item_pager_image,
					view, false);

			final PhotoView imageView = (PhotoView) imageLayout
					.findViewById(R.id.image);
			final ProgressBar spinner = (ProgressBar) imageLayout
					.findViewById(R.id.loading);
			// title.setText((photoEntities.size()-position) + "/" +
			// photoEntities.size());
			utils.display(imageView, paths.get(position),
					new BitmapLoadCallBack<View>() {

						@Override
						public void onLoadStarted(View container, String uri,
												  BitmapDisplayConfig config) {
							super.onLoadStarted(container, uri, config);
							spinner.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadCompleted(View arg0, String arg1,
													Bitmap arg2, BitmapDisplayConfig arg3,
													BitmapLoadFrom arg4) {
							spinner.setVisibility(View.GONE);
							imageView.setImageBitmap(arg2);
						}

						@Override
						public void onLoadFailed(View arg0, String arg1,
												 Drawable arg2) {
							ToastUtil.show("加载失败");
							spinner.setVisibility(View.GONE);

						}
					});

			((ViewPager) view).addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
}
