# SuperVideoPlayer


集成android流媒体处理的主流框架，vitamio, ijkplayer等等。使用不同的主流框架实现播放本地视频，网络视频，直播等功能。

>  写这个开源项目的初衷：
       刚接触流媒体处理的时候，一个小的问题都能被难住半天。然后跑到qq群里面去问，google去搜（ps：官方群不鸟你）。一直觉得这样浪费了很多很多宝贵的时间。写这个开源项目是主要想把自己的踩过的一些坑都整理出来，能帮助到一些新接触流媒体处理的开发者。更多的是抛砖引玉，小弟也是流媒体处理的新兵一只。希望各位流媒体处理的大神，小神大家能一起开发这个项目，我们一起实现一个实用流媒体的开源库（走过的坑不能白走啊！！）。

目前集成了vitamio，综合vitamio的特性目前实现的功能有:

 - 播放本地视频，网络视频，直播流
 - 左右侧滑动调节声音和亮度
 - 缓存视频进度条

下一步使用vitamio实现下面的功能:
  - 支持截屏，锁屏
  - 切换不同显示大小（全屏，拉伸）
  - 设置视频播放的一下配置（画面质量，硬解码等）
  - 缓存到本地
  - 支持快进，快退


示例:
![Alt text](https://github.com/curtis2/SuperVideoPlayer/blob/0aaf3ce2b6fc817d50ffdbe79a75ef6701c0b96b/source/start.gif)

***
![Alt text](https://github.com/curtis2/SuperVideoPlayer/blob/master/source/start2.gif)


***

## 项目介绍:
---
1.引入库项目
   - **greenDAO**       greenDAO是一个Android对象关系映射工具（ORM）库
   - **CircularFloatingActionMenu**    是用于安卓系统中的可自定义的动态圆形浮动菜单按钮。
   - **SwipeMenuListView**     是一种可以在某一个view条目中响应用户左右侧滑滑出操作菜单的UI类库。
   - **vitamio**     视频播放库

 > 引入的库除了 vitamio以外，其他的都只是涉及到界面ui,数据存储相关的。和流媒体的处理关系不太。 实现流媒体功能的时候完全可以不用考虑这些。 不要因为这个不参与这个项目哦。

2.核心类介绍

 &emsp;&emsp; LaunchActivity：
 &emsp;&emsp;&emsp;&emsp;      应用启动界面，有加载数据，添加数据。切换不同界面等功能。

 &emsp;&emsp; CustomMediaController ：
    &emsp;&emsp;&emsp;&emsp;    继承vitamio的MediaController类，是一个FrameLayout. CustomMediaController 类主要是用于显示视频控制布局（就是播放，暂停，截屏，左右滑动切换声音的界面）。

&emsp;&emsp;  VideoViewActivity：
  &emsp;&emsp;&emsp;&emsp;    视频播放界面,在改类中使用vitamio的VideoActivity播放视频。


>注：之后的截屏，锁屏功能都是使用VideoView进行开发。所以都是在VideoViewActivity和CustomMediaController 中进行。
CustomMediaController 类中处理相关ui操作，VideoViewActivity中处理VideoView的相关设置。



##    参与步骤:
  ---
 1. 将该项目fork到自己的github;
 2. 完成待实现功能的代码编写和测试。
 3. 提交pull request.(提交之前记得先拉取一下，避免冲突哦！！)


