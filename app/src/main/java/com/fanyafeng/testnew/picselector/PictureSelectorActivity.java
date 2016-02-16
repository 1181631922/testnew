package com.fanyafeng.testnew.picselector;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class PictureSelectorActivity extends Activity implements ListImageDirPopupWindow.OnImageDirSelected, PictureListAdapter.OnImageSelected
{
	public final static String SELECTED_IMAGES = "selected_images";
	public final static String SELECTED_MAX_COUNT = "selected_max_count";

	private ProgressDialog mProgressDialog;

	/**
	 */
	private int mPicsSize;
	/**
	 */
	private File mImgDir;
	/**
	 */
	private List<String> mImgs;

	private Button mChosenButton;

	private GridView mGirdView;
	private PictureListAdapter mAdapter;
	/**
	 */
	private HashSet<String> mDirPaths = new HashSet<String>();

	/**
	 */
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

	private RelativeLayout mBottomLy;

	private TextView mChooseDir;
	private TextView mImageCount;
	int totalCount = 0;

	private int mScreenHeight;

	private ListImageDirPopupWindow mListImageDirPopupWindow;

	private int mMaxSelectedCount = 0;

	private Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			mProgressDialog.dismiss();
			data2View();
			initListDirPopupWindw();
		}
	};

	/**
	 * ΪView������
	 */
	private void data2View()
	{
		if (mImgDir == null)
		{
			Toast.makeText(getApplicationContext(), "",
					Toast.LENGTH_SHORT).show();
			return;
		}

//		mImgs = Arrays.asList(mImgDir.list());
//        mImgs = getSortedFileListByModifiedDate(mImgDir);
        /**
		 */
		mAdapter = new PictureListAdapter(getApplicationContext(), mImgs,
				R.layout.picture_selector_grid_item, mImgDir.getAbsolutePath(), this, mMaxSelectedCount);
		mGirdView.setAdapter(mAdapter);
		mImageCount.setText(totalCount + "��");
	};

    /**
     * @param dir
     */
    private List<String> getSortedFileListByModifiedDate(File dir) {
        File[] files = dir.listFiles();
        ArrayList<File> fileList = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            fileList.add(files[i]);
        }
        Collections.sort(fileList, new FileComparator());

        List<String> filePathList = new ArrayList<>();
        for(int i = 0; i < fileList.size(); i++) {
            filePathList.add(fileList.get(i).getName());
        }

        return  filePathList;
    }

    public class FileComparator implements Comparator<File> {
        public int compare(File file1, File file2) {
            if(file1.lastModified() > file2.lastModified())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }


	/**
	 */
	private void initListDirPopupWindw()
	{
		mListImageDirPopupWindow = new ListImageDirPopupWindow(
				LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
				mImageFloders, LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.picture_selector_list_dir, null));

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener()
		{

			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
			}
		});
		mListImageDirPopupWindow.setOnImageDirSelected(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_picture_selector);

//		List<String> picList = getIntent().getStringArrayListExtra(SELECTED_IMAGES);
//		if(picList != null) {
//			PictureListAdapter.mSelectedImage = picList;
//		} else {
//			PictureListAdapter.mSelectedImage.clear();
//		}

        PictureListAdapter.mSelectedImage.clear();

		mMaxSelectedCount = getIntent().getIntExtra(SELECTED_MAX_COUNT, 9);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;

		initView();
		getImages();
		initEvent();

	}

	/**
	 */
	private void getImages()
	{
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
		{
			Toast.makeText(this, "�����ⲿ�洢", Toast.LENGTH_SHORT).show();
			return;
		}
		mProgressDialog = ProgressDialog.show(this, null, "...");

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = PictureSelectorActivity.this
						.getContentResolver();

				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

//				Log.e("TAG", mCursor.getCount() + "");
				while (mCursor.moveToNext())
				{
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

//					Log.e("TAG", path);
					if (firstImage == null)
						firstImage = path;
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					ImageFloder imageFloder = null;
					if (mDirPaths.contains(dirPath))
					{
						continue;
					} else
					{
						mDirPaths.add(dirPath);
						imageFloder = new ImageFloder();
						imageFloder.setDir(dirPath);
						imageFloder.setFirstImagePath(path);
					}

					int picSize = parentFile.list(new FilenameFilter()
					{
						@Override
						public boolean accept(File dir, String filename)
						{
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;
					totalCount += picSize;

					imageFloder.setCount(picSize);
					mImageFloders.add(imageFloder);

					if (picSize > mPicsSize)
					{
						mPicsSize = picSize;
						mImgDir = parentFile;
					}
				}
				mCursor.close();

				mDirPaths = null;

                mImgs = getSortedFileListByModifiedDate(mImgDir);

				mHandler.sendEmptyMessage(0x110);

			}
		}).start();

	}

	/**
	 * ��ʼ��View
	 */
	private void initView()
	{
		mGirdView = (GridView) findViewById(R.id.id_gridView);
		mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
		mImageCount = (TextView) findViewById(R.id.id_total_count);

		mChosenButton = (Button) findViewById(R.id.id_do_chosen);

		mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);

	}

	private void initEvent()
	{
		/**
		 */
		mBottomLy.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mListImageDirPopupWindow
						.setAnimationStyle(R.style.anim_popup_dir);
				mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);

				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = .3f;
				getWindow().setAttributes(lp);
			}
		});

		mChosenButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setResult(RESULT_OK);
				finish();
			}
		});
	}

	@Override
	public void selected(ImageFloder floder)
	{

		mImgDir = new File(floder.getDir());
		mImgs = Arrays.asList(mImgDir.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String filename)
			{
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		/**
		 */
		mAdapter = new PictureListAdapter(getApplicationContext(), mImgs,
				R.layout.picture_selector_grid_item, mImgDir.getAbsolutePath(), this, mMaxSelectedCount);
		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();
		mImageCount.setText(floder.getCount() + "��");
		mChooseDir.setText(floder.getName());
		mListImageDirPopupWindow.dismiss();

	}

	@Override
	public void selected() {
		// TODO Auto-generated method stub
		int count = PictureListAdapter.mSelectedImage.size();
		if(count == 0) {
			mChosenButton.setEnabled(false);
		} else {
			mChosenButton.setEnabled(true);
			mChosenButton.setText("" + count + "");
		}
	}

}
