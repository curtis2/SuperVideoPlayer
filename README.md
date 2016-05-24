# SuperVideoPlayer



集成android流媒体处理的主流框架，vitamio, ijkplayer等等。使用不同的主流框架实现播放本地视频，网络视频，直播等功能。
>  写这个开源项目的初衷：
       刚接触流媒体处理的时候，一个小的问题都能被难住半天。然后跑到qq群里面去问，google去搜（ps：官方群不鸟你）。一直觉得这样浪费了很多很多宝贵的时间。写这个开源项目是主要想把自己的踩过的一些坑都整理出来，能帮助到一些新接触流媒体处理的开发者。更多的是抛砖引玉，小弟也是流媒体处理的新兵一只。希望各位流媒体处理的大神，小神大家能一起开发这个项目，我们一起实现一个实用流媒体的开源库（走过的坑不能白走啊！！）。

  **注意事项**：
  1. 用studio 导入项目可能较慢，耐心等一下**。
  2.  播放本地视频只需要修改VideoViewActivity的mVideoView.setVideoPath(mVideoPath);将mVideoPath修改为你本地的视频的   路径：例如：“/storage/sdcard0/xxx.mp4"即可。
  3.  关于Videoview的一些设置，大家可以参考vitamio的API。[vitamio Api链接](https://www.vitamio.org/docs/API/)
  
---
**目前集成了vitamio，综合vitamio的特性目前实现的功能有**:

 - 播放本地视频，网络视频，直播
 - 左右侧滑动调节声音和亮度
 - 缓存视频进度条
 - 切换不同显示大小（全屏，拉伸,剪切）
 -  支持截屏
 - 支持快进，快退
 - <font color="blue"> **支持弹幕功能**</font>


vitamio支持的视频格式：
-  DivX/Xvid
- FLV
- TS/TP
- MKV
- MOV
- M4V
- AVI
- MP4
- 3GP

直播流格式：
 - MMS
 - RTSP (RTP, SDP), RTMP
 - HTTP progressive streaming
 - HLS - HTTP live streaming (M3U8)
 
>  注： 不同视频格式的测试地址有mp4,avi,flv，直播流的测试地址有：RTMP，M3U8。如果大家有其他视频格式的链接地址，帮忙发我一下。添加到issue中就行

下一步：
  - 添加视频设置界面，可以设置播放的一些配置（画面质量，硬解码等）
  - 边播放，边缓存到本地
  
示例:

---

![Alt text](https://github.com/curtis2/SuperVideoPlayer/blob/0aaf3ce2b6fc817d50ffdbe79a75ef6701c0b96b/source/start.gif)

![Alt text](https://github.com/curtis2/SuperVideoPlayer/blob/master/source/start3.gif)

![Alt text](https://raw.githubusercontent.com/curtis2/SuperVideoPlayer/master/source/Screenshot_2016-05-19-11-39-39.jpeg)
![Alt text](https://raw.githubusercontent.com/curtis2/SuperVideoPlayer/master/source/Screenshot_2016-05-19-11-39-49.jpeg)
![Alt text](https://raw.githubusercontent.com/curtis2/SuperVideoPlayer/master/source/Screenshot_2016-05-19-11-40-21.jpeg)

***

## 项目介绍:
---
1.引入库项目
   - **greenDAO**       greenDAO是一个Android对象关系映射工具（ORM）库
   - **CircularFloatingActionMenu**    是用于安卓系统中的可自定义的动态圆形浮动菜单按钮。
   - **SwipeMenuListView**     是一种可以在某一个view条目中响应用户左右侧滑滑出操作菜单的UI类库。
   - **DanmakuFlameMaster**     android弹幕支持库
   - **vitamio**     视频播放库

 > 引入的库除了 vitamio以外，其他的都只是涉及到界面ui,数据存储相关的。和流媒体的处理关系不太。 实现流媒体功能的时候完全可以不用考虑这些。 不要因为这个不参与这个项目哦。

2.核心类介绍

 &emsp;&emsp;**LaunchActivity**：
 -   &emsp;应用启动界面，有加载数据，添加数据。切换不同界面等功能。

 ***
 &emsp;&emsp;**VideoViewActivity**：

-  &emsp;   视频播放界面,在改类中使用vitamio的VideoActivity播放视频。

***
&emsp;&emsp;**CustomMediaController** ：

 - &emsp;  继承vitamio的MediaController类，是一个FrameLayout. CustomMediaController 类主要是用于显示视频控制布局（就是播放，暂停，截屏，左右滑动切换声音的界面）。

>注：截屏，锁屏功能都是使用VideoView进行开发。所以都是在VideoViewActivity和CustomMediaController 中进行。
CustomMediaController 类中处理相关ui操作，VideoViewActivity中处理VideoView的相关设置。



##    参与步骤:
  ---
 1. 将该项目fork到自己的github;
 2. 完成待实现功能的代码编写和测试。
 3. 提交pull requests.(提交之前记得先拉取一下，避免冲突哦！！)


##  问题反馈:
  ---
&emsp;如果在使用过程中发现有问题，请描述清楚问题。我会尽力去解决，如果你有好的解决方案，也欢迎提交requests.
&emsp; 例如： 视频播放不了
  - 请写明视频格式，和链接，然后在github上提交issue。  

## 扩展
  ---
   大家如果在使用中想要提出公司业务相关的功能，比如视频前加广告，可以直接提交到issue上。（ps：这个我现在也不知道怎么做）。我会把一些好的需求加到项目的功能计划中，相信大家一起参与就能实现。

## 版权说明
  ---
  项目目前是基于vitamio进行二次开发的，该开源库对个人开发者免费，对公司收费。如果是公司使用，请自行和[vitamio公司](https://www.vitamio.org)协商付费。
