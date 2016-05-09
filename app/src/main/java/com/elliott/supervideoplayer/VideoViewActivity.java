/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elliott.supervideoplayer;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elliott.supervideoplayer.utils.LogUtils;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

public class VideoViewActivity extends Activity {
    public static final String VIDEO_PATH="videoName";
    private VideoView mVideoView;

    private LinearLayout mLoadingLayout;
    private ImageView mLoadingImg;
    private TextView mLoadingTv;
    private ObjectAnimator mOjectAnimator;
    /**
     * 当前进度
     */
    private Long currentPosition = (long) 0;
    private String mVideoPath = "";

    /**
     * setting
     */
    private boolean needResume;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        if (!LibsChecker.checkVitamioLibs(this)) return;
        setContentView(R.layout.video_play_layout);
        getDataFromIntent();
        initviews();
        initVideoSettings();
    }

    private void getDataFromIntent() {
        Intent Intent = getIntent();
        if (Intent != null && Intent.getExtras().containsKey(VIDEO_PATH)) {
            mVideoPath = Intent.getExtras().getString(VIDEO_PATH);
        }
    }

    private void initviews() {
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mLoadingLayout=(LinearLayout) findViewById(R.id.loading_LinearLayout);
        mLoadingImg=(ImageView) findViewById(R.id.loading_image);
        mLoadingTv=(TextView) findViewById(R.id.loading_tv);
    }

    private void initVideoSettings() {
        mVideoView.requestFocus();
        mVideoView.setBufferSize(1024 * 1024);
        mVideoView.setVideoPath(mVideoPath);
        CustomMediaController mediaController = new CustomMediaController(this);
        mVideoView.setMediaController(mediaController);
    }

    public void onResume() {
        super.onResume();
        preparePlayVideo();
    }

    private void preparePlayVideo() {
         startLoadingAnimator();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // TODO Auto-generated method stub
                stopLoadingAnimator();

                if (currentPosition > 0) {
                    mVideoView.seekTo(currentPosition);
                } else {
                    mediaPlayer.setPlaybackSpeed(1.0f);
                }
                startPlay();
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
                switch (arg1) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //开始缓存，暂停播放
                        LogUtils.i(LogUtils.LOG_TAG, "开始缓存");
                        startLoadingAnimator();
                        if (mVideoView.isPlaying()) {
                            stopPlay();
                            needResume = true;
                        }
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //缓存完成，继续播放
                        stopLoadingAnimator();
                        if (needResume) startPlay();
                        LogUtils.i(LogUtils.LOG_TAG, "缓存完成");
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        //显示 下载速度
                        LogUtils.i("download rate:" + arg2);
                        break;
                }
                return true;
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
//                LogUtils.i(LogUtils.LOG_TAG, "what=" + what);
                return false;
            }
        });
        mVideoView.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
            }
        });
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                LogUtils.i(LogUtils.LOG_TAG, "percent" + percent);
             /*   if(percent==100){
                    stopLoadingAnimator();
                }*/
            }
        });
    }

    @NonNull
    private void startLoadingAnimator() {
        if(mOjectAnimator==null){
            mOjectAnimator = ObjectAnimator.ofFloat(mLoadingImg, "rotation", 0f, 360f);
        }
        mLoadingLayout.setVisibility(View.VISIBLE);

        mOjectAnimator.setDuration(1000);
        mOjectAnimator.setRepeatCount(-1);
        mOjectAnimator.start();
    }
    
    private void stopLoadingAnimator() {
        mLoadingLayout.setVisibility(View.GONE);
        mOjectAnimator.cancel();
    }

    private void startPlay() {
        mVideoView.start();
    }

    private void stopPlay() {
        mVideoView.pause();
    }

    public void onPause() {
        currentPosition = mVideoView.getCurrentPosition();
        mVideoView.pause();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(mVideoView!=null){
            mVideoView.stopPlayback();
            mVideoView = null;
        }
    }

}
