## android studio项目project目录结构介绍


![Alt text](./QQ截图20160506173503.png)

上图是一个在android studio中切换到project结构下的gradle项目的目录结构。
下面挨个介绍一下这几个目录的作用;
.gradle文件夹：
.idea文件夹 ：
     as生成的工程配置文件，

app/build/ app模块build编译输出的目录
app/build.gradle app模块的gradle编译文件
app/app.iml app模块的配置文件
app/proguard-rules.pro app模块proguard文件
build.gradle 项目的gradle编译文件
settings.gradle 定义项目包含哪些模块
gradlew 编译脚本，可以在命令行执行打包
local.properties 配置SDK/NDK
MyApplication.iml 项目的配置文件
External Libraries 项目依赖的Lib, 编译时自动下载的

