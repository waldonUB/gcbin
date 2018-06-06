## Community
一个用SSM框架+Vue.js和MySQL做的javaweb网站

###简介：
1 JDK1.8 <br>
2 运行工具 IntelliJ Idea <br>
3 数据库 MySQL <br>
4 Tomcat 7.0.79 <br>

装完上述工具后，直接下载项目用IntelliJ Idea打开，配置好Tomcat就可以直接运行了

###使用：
运行tomcat，到登录直接界面。注册完后默认会以普通会员的身份登录。<br>
如果想要管理员登录，直接在数据库把注册完的账号的user_type更改为1。<br>
到了管理员界面后，可以自己注册导航栏节点。节点的路径(url)可以用相对路径</br>
来添加其他页面，也可以放其他网址的超链接。如果作为虚拟节点的话就填一个 # 就可以了

###效果：
图表和地图<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/datascreen.gif)
导航栏节点注册<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/resource.gif)
头像上传<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/individuation.gif)
话题交流<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/classify.gif)
Druid连接池监控<br>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/druid.gif)


