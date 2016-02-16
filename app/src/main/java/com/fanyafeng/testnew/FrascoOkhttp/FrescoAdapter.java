package com.fanyafeng.testnew.FrascoOkhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fanyafeng.testnew.MyApplication.FrescoConfig;
import com.fanyafeng.testnew.R;

import java.util.List;

/**
 * Created by fanyafeng on 2015/12/23,0023.
 */
public class FrescoAdapter extends BaseAdapter {
    private Context context;
    private List<FrescoBean> frescoBeanList;

    public FrescoAdapter(Context context, List<FrescoBean> frescoBeanList) {
        this.context = context;
        this.frescoBeanList = frescoBeanList;
    }


    @Override
    public int getCount() {
        return frescoBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return frescoBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fraso_listview, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
            viewHolder.draweeview_list_item = (SimpleDraweeView) view.findViewById(R.id.draweeview_list_item);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        默认有缓存
        FrescoBean frasoBean = (FrescoBean) getItem(position);
        ImageRequest imageRequest= FrescoConfig.getDraweeViewImageRequest(viewHolder.draweeview_list_item, frasoBean.getImageUrl());
        DraweeController draweeController = FrescoConfig.getSimpleDraweeController(imageRequest, viewHolder.draweeview_list_item);
        viewHolder.draweeview_list_item.setController(draweeController);
//        不加以上配置的话只显示第一祯图片，gif图片不会循环播放
//        viewHolder.draweeview_list_item.setImageURI(Uri.parse(frasoBean.getImageUrl()));

        return view;
    }

    static class ViewHolder {
        SimpleDraweeView draweeview_list_item;
    }
}
