package com.elliott.supervideoplayer.adapter;

import android.content.Context;

import com.elliott.supervideoplayer.R;
import com.elliott.supervideoplayer.model.VideoBean;
import com.elliott.supervideoplayer.utils.CommonAdapter;
import com.elliott.supervideoplayer.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public class VideoAdapter extends CommonAdapter<VideoBean> {

    public VideoAdapter(Context context, List mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    public void setDatas(ArrayList<VideoBean> list){
        this.mDatas=list;
    }
    @Override
    public void convert(ViewHolder holder, VideoBean item) {
              holder.setText(R.id.item_name_tv,item.getVideoName())
                .setText(R.id.item_link_tv,item.getVideoLink());
    }

}
