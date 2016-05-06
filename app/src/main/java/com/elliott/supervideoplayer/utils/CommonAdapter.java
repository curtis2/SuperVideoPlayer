package com.elliott.supervideoplayer.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected int layoutId;
    public CommonAdapter(Context context, List<T> mDatas, int layoutId)
    {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.layoutId = layoutId;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,layoutId, position);
        convert(holder, (T)getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item);



}
