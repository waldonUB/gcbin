## Community
一个用SSM框架+Vue.js和MySQL做的javaweb网站。里面还有很多节点功能没有实现，有好玩的想法的，可以加我QQ：852727515，添加分支，一起学习。觉得不错的可以给个watch或star，我会定时更新，谢谢鼓励。（[点击跳转到服务器](http://47.106.190.36:8080/PetsCT/)预览效果）

###简介：
1 JDK1.8 <br>
2 运行工具 IntelliJ Idea <br>
3 数据库 MySQL <br>
4 Tomcat 7.0.79 <br>

装完上述工具后，直接下载项目用IntelliJ Idea打开，执行create.sql里面的语句建表，配置好Tomcat就可以直接运行了

###使用：
运行tomcat，到登录直接界面。注册完后默认会以管理员的身份登录。<br>
到了管理员界面后，可以自己注册导航栏节点。节点的路径(url)可以用相对路径</br>
来添加其他页面，也可以放其他网址的超链接。如果作为虚拟节点的话就填一个 # 就可以了


###效果：
图表和地图：根据用户上线的活跃度和地区分布，用echart.js生成的图表和地图<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/index.gif)
导航栏节点注册：用Ztree来构建树形菜单，从数据库把这部分数据生成按父子节点来划分层级的数组，再用Vue的组件通信机制将数据动态渲染，就可以搞出一个无限层级的导航栏了(因为bootstrap的顶部导航栏只支持两层，后面层级的样式可以按自己的想法来写)<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/tree.gif)
话题交流：目前只是一个简单的发表话题和点赞评论，待改进<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/community.gif)
Druid连接池监控：只是简单演示一下阿里巴巴的东西<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/druid.gif)


