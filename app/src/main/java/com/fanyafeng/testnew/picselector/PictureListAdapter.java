package com.fanyafeng.testnew.picselector;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.fanyafeng.testnew.R;

import java.util.LinkedList;
import java.util.List;

public class PictureListAdapter extends CommonAdapter<String>
{

	/**
	 */
	public static List<String> mSelectedImage = new LinkedList<String>();

	/**
	 */
	private String mDirPath;

	private OnImageSelected mOnImageSelected;

	private int mMaxSelectedCount = 9;

	public PictureListAdapter(Context context, List<String> mDatas, int itemLayoutId,
			String dirPath, OnImageSelected onImageSelected, int maxSelectedCount)
	{
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		this.mOnImageSelected = onImageSelected;
		this.mMaxSelectedCount = maxSelectedCount;
	}

	@Override
	public void convert(final com.fanyafeng.testnew.picselector.ViewHolder helper, final String item)
	{
		helper.setImageResource(R.id.id_item_image, R.mipmap.picture_selector_pictures_no);
				helper.setImageResource(R.id.id_item_select,
						R.mipmap.picture_selector_picture_unselected);
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		mImageView.setOnClickListener(new OnClickListener()
		{
			//ѡ����ͼƬ�䰵����֮��֮
			@Override
			public void onClick(View v)
			{
				if (mSelectedImage.contains(mDirPath + "/" + item))
				{
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.mipmap.picture_selector_picture_unselected);
					mImageView.setColorFilter(null);
				} else
				{
					if(mSelectedImage.size() >= mMaxSelectedCount) {
						return;
					}
					mSelectedImage.add(mDirPath + "/" + item);
					mSelect.setImageResource(R.mipmap.picture_selector_pictures_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
				}

				mOnImageSelected.selected();

			}
		});

		/**
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item))
		{
			mSelect.setImageResource(R.mipmap.picture_selector_pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
	
	public interface OnImageSelected
	{
		public abstract void selected();
	}
}
