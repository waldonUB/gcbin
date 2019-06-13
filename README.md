## gcbin
#### 该项目为2017年实习做的demo项目，主要框架为：SSM+Vue.js。[点击跳转到服务器](http://47.106.190.36:8080/gcbin/)（因近期淘宝解析IP的服务不稳定，可能会出现无法定位地址的情况，如果不能注册，请使用管理员账号：admin  密码：2） <br/>
#### 查看基于SVG和VUE的前端流程设计器的简单Demo请移步[SVGDemo](https://github.com/waldonUB/SVGDemo)<br/>
#### 查看springboot+vue全家桶的后端部分项目请移步[gcMybatisPlus](https://github.com/waldonUB/gcMybatisPlus) <br/>

###简介：
1 JDK1.8 <br>
2 运行工具 IntelliJ Idea <br>
3 数据库 MySQL 5.7 <br>
4 Tomcat 7.0.79(tomcat7以上都可以使用) <br>
装完上述工具后，直接下载项目用IntelliJ Idea打开，配置好Tomcat就可以直接运行了<br/>
顺便简单科普一下idea下载项目之后运行的操作<br/>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/operate.gif)
###本次更新：
1.自动获取当前登录地区<br/>
2.增加微信登录接口<br/>
3.根据不同用户在权限树配置的权限，显示不同的导航栏菜单<br/>
###效果：
图表和地图<br/>
采用echarts图表绘制,根据后台数据自动生成图形界面<br/>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/index.gif)
导航栏节点注册<br/>
整个系统的导航栏采用最原生的方式,根据用户表,角色表和权限表的对应关系,生成对应的权限节点的数据,再在前台递归取数,可以生成无限层级的节点. <br/>
这里是html内的写法,vue组件写法的方式移步[gc-vue](https://github.com/waldonUB/gc-vue) <br/>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/tree.gif)
话题交流<br/>
![image](https://github.com/qq852727515/imageSave/blob/master/petsCT_img/community.gif)



