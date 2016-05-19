package com.elliott.supervideoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import io.vov.vitamio.widget.MediaController;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public class CustomMediaLiveController extends MediaController {
    private Context mContext;
    VideoViewActivity activity;
    /**
     * 弹幕相关
     */
    private IDanmakuView mDanmakuView;
    private Switch mtanMuSwitch;
    private DanmakuContext mDanmakuContext;
    private BaseDanmakuParser mParser;

    public CustomMediaLiveController(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected View makeControllerView() {
        return ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                inflate(getResources().getIdentifier("mediacontroller_live", "layout", mContext.getPackageName()), this);
    }

    @Override
    protected void initOtherView() {
        mtanMuSwitch= (Switch) mRoot.findViewById(R.id.switch_tanmu);
        mtanMuSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mtanMuSwitch.setBackgroundColor(getResources().getColor(R.color.video_red_color));
                    //开启弹幕
                    mDanmakuView.prepare(mParser, mDanmakuContext);
                    mDanmakuView.show();
                }else{
                    mtanMuSwitch.setBackgroundColor(getResources().getColor(R.color.video_gray_color));
                    //关闭弹幕
                    mDanmakuView.hide();
                }
            }
        });
    }
    public void setTanMuView(IDanmakuView tanMuView,DanmakuContext mDanmakuContext,BaseDanmakuParser mParser ) {
        this.mDanmakuView = tanMuView;
        this.mDanmakuContext=mDanmakuContext;
        this.mParser=mParser;
    }


}
