package com.elliott.supervideoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import io.vov.vitamio.widget.MediaController;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public class CustomMediaController extends MediaController {
    private Context mContext;

    public CustomMediaController(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    protected View makeControllerView() {
       return ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
               inflate(getResources().getIdentifier("mediacontroller", "layout", mContext.getPackageName()), this);
    }

}
