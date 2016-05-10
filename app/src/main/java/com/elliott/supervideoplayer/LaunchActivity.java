package com.elliott.supervideoplayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.elliott.supervideoplayer.adapter.VideoAdapter;
import com.elliott.supervideoplayer.adapter.ViewPagerAdapter;
import com.elliott.supervideoplayer.db.VideoBeanDaoHelper;
import com.elliott.supervideoplayer.model.VideoBean;
import com.elliott.supervideoplayer.utils.DensityUtils;
import com.elliott.supervideoplayer.utils.DialogUtils;
import com.elliott.supervideoplayer.utils.T;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class LaunchActivity extends Activity {
    private String mVideoPath = "http://125.39.142.86/data2/video09/2016/03/01/3871799-102-1615.mp4";
    private LinearLayout mLinearLayout;
    private LinearLayout lineaylayout1;
    private LinearLayout lineaylayout2;
    private LinearLayout lineaylayout3;
    private ImageView ImageView1;
    private ImageView ImageView2;
    private ImageView ImageView3;

    public LaunchActivity() {
        super();
    }

    private ArrayList<View> mViewList;
    private ViewPagerAdapter pageAdapter;

    private SparseArray<ArrayList<VideoBean>> mDataMaps;
    private SparseArray<VideoAdapter> mAdapters;

    FloatingActionMenu  floatingActionMenu;

    VideoBeanDaoHelper helper;
    private int currentType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initMenus();
        initDatas();
        showCurrentListView();
   }

    /**
     * 插入测试数据，可删除
     */
    private void initTestDatas() {
        VideoBean bean=new VideoBean();
        bean.setVideoLink(mVideoPath);
        bean.setVideoName("mp4");
        bean.setType(currentType);
        helper.insertBean(bean);

    }

    private void showCurrentListView() {
        mLinearLayout.removeAllViews();
        mLinearLayout.addView(mViewList.get(currentType));
    }

    private void initViews() {
        mLinearLayout= (LinearLayout) findViewById(R.id.linearlayout_content);
        lineaylayout1= (LinearLayout) findViewById(R.id.button_layout_1);
        lineaylayout2= (LinearLayout) findViewById(R.id.button_layout_2);
        lineaylayout3= (LinearLayout) findViewById(R.id.button_layout_3);
        lineaylayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handTitleIndexImg(0);
                showCurrentListView();
            }
        });
        lineaylayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handTitleIndexImg(1);
                showCurrentListView();
            }
        });
        lineaylayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handTitleIndexImg(2);
                showCurrentListView();
            }
        });

        ImageView1= (ImageView) findViewById(R.id.button_line_img1);
        ImageView2= (ImageView) findViewById(R.id.button_line_img2);
        ImageView3= (ImageView) findViewById(R.id.button_line_img3);
        handTitleIndexImg(0);
    }

    private void handTitleIndexImg(int position) {
        currentType=position;
        ArrayList<View> list=new ArrayList<>();
        list.add(ImageView1);
        list.add(ImageView2);
        list.add(ImageView3);
        int size =list.size();
        for (int i=0;i<size;i++){
            list.get(i).setVisibility(View.INVISIBLE);
        }
        list.get(position).setVisibility(View.VISIBLE);
    }

    private void initMenus() {
        ImageView menuimg= (ImageView) findViewById(R.id.menu_img);
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.action_edit_light));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.action_hidden_default_dark));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.action_delete_selected_light));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.abc_ic_clear_search_api_holo_light));

        SubActionButton rlSub1 = rLSubBuilder.setContentView(rlIcon1).build();
        SubActionButton rlSub2 = rLSubBuilder.setContentView(rlIcon2).build();
        SubActionButton rlSub3 = rLSubBuilder.setContentView(rlIcon3).build();
        SubActionButton rlSub4 = rLSubBuilder.setContentView(rlIcon4).build();
        rlIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加
                showAddVideoItemDialog();
            }
        });
        rlIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看直播
            }
        });
        rlIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
            }
        });
        rlIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出
                floatingActionMenu.close(true);
            }
        });
       floatingActionMenu= new FloatingActionMenu.Builder(this)
                .setStartAngle(180)
                .setEndAngle(270)
                .addSubActionView(rlSub1)
                .addSubActionView(rlSub2)
                .addSubActionView(rlSub3)
                .addSubActionView(rlSub4)
                .attachTo(menuimg)
                .build();
    }

    private void showAddVideoItemDialog() {
       final  DialogUtils dialogUtils=new DialogUtils(this,R.layout.normal_dialog);
        dialogUtils.setOnClickListener(R.id.btn_ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //插入listview
                EditText linkEd= (EditText)dialogUtils.getView(R.id.dialog_link_ed);
                EditText nameEd= (EditText)dialogUtils.getView(R.id.dialog_name_ed);
                if(!TextUtils.isEmpty(linkEd.getText().toString())&&!TextUtils.isEmpty(nameEd.getText().toString())){
                    VideoBean bean=new VideoBean();
                    bean.setVideoLink(linkEd.getText().toString());
                    bean.setVideoName(nameEd.getText().toString());
                    bean.setType(currentType);
                    helper.insertBean(bean);
                    showCurrentTypeData(bean);
                }else{
                    T.showLong(LaunchActivity.this,"请求填写正确的流视频");
                }
                dialogUtils.close();
            }
        }).setOnClickListener(R.id.btn_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUtils.close();
            }
        }).show();
    }

    private void  showCurrentTypeData(VideoBean bean){
        mDataMaps.get(currentType).add(bean);
        mAdapters.get(currentType).notifyDataSetChanged();
    }
    private void  deleteCurrentTypeData(int position){
        VideoBean bean =mDataMaps.get(currentType).get(position);
        helper.deleteBean(bean);
        mDataMaps.get(currentType).remove(bean);
        mAdapters.get(currentType).notifyDataSetChanged();
    }

    private void initDatas() {
        helper=new VideoBeanDaoHelper(this);
        initTestDatas();
        mViewList=new ArrayList<View>();
        mDataMaps=new SparseArray<>();
        mAdapters=new SparseArray<>();
        for (int i=0;i<3;i++) {
            SwipeMenuListView listview = new SwipeMenuListView(this);
            ArrayList<VideoBean> dataList = helper.getDatasByType(i);
            VideoAdapter videoAdapter = new VideoAdapter(this, dataList, R.layout.video_item_layout);
            listview.setAdapter(videoAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    VideoBean bean = mDataMaps.get(currentType).get(position);
                    goToVideoPlayerPage(bean);
                }
            });
            SwipeMenuCreator creator = new SwipeMenuCreator() {
                @Override
                public void create(SwipeMenu menu) {
                    // create "delete" item
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getApplicationContext());
                    // set item background
                    deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                            0x3F, 0x25)));
                    // set item width
                    deleteItem.setWidth(DensityUtils.dp2px(LaunchActivity.this, 90));
                    // set a icon
                    deleteItem.setIcon(R.drawable.ic_delete);
                    // add to menu
                    menu.addMenuItem(deleteItem);
                }
            };
            listview.setMenuCreator(creator);
            listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    switch (index) {
                        case 0:
                            deleteCurrentTypeData(position);
                            break;
                    }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            });
            mDataMaps.put(i,dataList);
            mAdapters.put(i,videoAdapter);
            mViewList.add(listview);
        }
    }
    private void goToVideoPlayerPage(VideoBean bean) {
        Intent appIntent = new Intent(this,VideoViewActivity.class);
        appIntent.putExtra(VideoViewActivity.VIDEO_PATH,bean.getVideoLink());
        startActivity(appIntent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(floatingActionMenu!=null&&floatingActionMenu.isOpen()){
            floatingActionMenu.close(true);
        }
        return super.onTouchEvent(event);
    }
}
