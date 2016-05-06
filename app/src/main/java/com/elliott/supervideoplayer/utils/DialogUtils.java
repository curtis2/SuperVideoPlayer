package com.elliott.supervideoplayer.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 用于创建自定义dialog的工具类
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public class DialogUtils {
    private SparseArray<View> mViews;
    private Context mContext;
    private View mContentView;
    AlertDialog alertDialog;

    public DialogUtils( Context mContext, int mLayoutId) {
        this.mContext = mContext;
        mContentView= LayoutInflater.from(mContext).inflate(mLayoutId, null);
        mViews = new SparseArray<View>();
    }

    public DialogUtils show(){
        if(alertDialog==null){
            alertDialog=new AlertDialog.Builder(mContext).create();
            alertDialog.setView(mContentView);
        }
        alertDialog.show();
        return this;
    }

    public void close(){
        if(alertDialog!=null&&alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }
    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {

        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public DialogUtils setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public DialogUtils setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }
    /**
     * 关于事件的
     */
    public DialogUtils setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public DialogUtils setOnTouchListener(int viewId,
                                         View.OnTouchListener listener)
    {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public DialogUtils setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener)
    {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
