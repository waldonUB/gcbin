//var $n3=$("#n3");要在$(document).ready(function)里面才有效
//$(document).ready(function)的缩写，可以有多个。而onload只能有一个
$(function () {
    var $model_info=$("#model_info");
    $model_info.click(function () {
        userData.switch_model=false;
    })
    /*$n3.keyup(function (event) {//1.对数据库压力有点大，2.如果用chang方法要失去焦点才行
        adminVue.query_search(userData.search_name)
    })*/
});
/**
 * 自己写的分页，样式有点丑，暂时先不用了...
 * */
/*var pageData={
  currentpage:1,
  all_page:5,
  show_item:5
};
Vue.component("page",{
   template:"#page",
   data:function () {
        return pageData;
    },
    computed:{
      pages:function () {
          var pag=[];
          if(this.currentpage<this.show_item){
              var i=Math.min(this.show_item,this.all_page);
              while(i){
                  pag.unshift(i--);
              }
          }else{
              var middle=this.currentpage-Math.floor(this.show_item/2);
              i=this.show_item;
              if(middle>(this.all_page-this.show_item)){
                  middle=(this.all_page-this.show_item)+1
              }
              while(i--){
                  pag.push(middle++);
              }
          }
          return pag;
      }
    },
    methods:{
        goto:function (index) {
            if(index===this.currentpage) return;
            this.currentpage=index;
            $.ajax({
                url:'/gcbin/query_usertype',
                data:JSON.stringify({"user_type":resdata,"page":(index-1)*10}),
                dataType:'json',
                type:'POST',
                contentType: "application/json; charset=utf-8",
                success:function (result) {
                    userData.users=result.data;
                }
            });
        }
    }
});*/
/**Z-Tree的测试数据*/
var model_data =[
    { id:1, pId:0, name:"Java技术", t:"我很普通，随便点我吧", open:true},
    { id:11, pId:1, name:"Java SE - 1", t:"我很普通，随便点我吧"},
    { id:12, pId:1, name:"J2ME - 2", t:"我很普通，随便点我吧"},
    { id:13, pId:1, name:"Java Web 开发 - 3", t:"我很普通，随便点我吧"},
    { id:2, pId:0, name:"Web语言", t:"点我可以，但是不能点我的子节点，有本事点一个你试试看？", open:false,click:false},
    { id:21, pId:2, name:"ASP - 1", t:"你哪个单位的？敢随便点我？小心点儿..", click:false},
    { id:22, pId:2, name:"HTML - 2", t:"我有老爸罩着呢，点击我的小心点儿..", click:false},
    { id:23, pId:2, name:"PHP - 3", t:"好歹我也是个领导，别普通群众就来点击我..", click:false},
    { id:3, pId:0, name:"游戏开发", t:"别点我，我好害怕...我的子节点随便点吧...", open:true, click:false },
    { id:31, pId:3, name:"Unity3D - 1", t:"唉，随便点我吧"},
    { id:32, pId:3, name:"其他游戏 - 2", t:"唉，随便点我吧"},
    { id:33, pId:3, name:"游戏策划与运营 - 3", t:"唉，随便点我吧"}
];
var setting={
    data:{
        key:{
            title:"",
            name:"tree_name",
            url:"inexistence"//点不到的网站
        },
        simpleData:{
            enable:true,
            idKey: "pk_resource",
            pIdKey: "pk_parent",
            rootPId:""
        }
    },
    callback:{
        onClick:editData,
        /*beforeDblClick:fbEdit,
        beforeClick:_beforeClick*/
    },
    view:{
        //dblClickExpand:fbUnfold
    }
};
var userData={
    users:[],
    resource:[],//所有资源信息
    current_model:{},//模块信息
    current_user:{},
    current_usertype:'',
    page_show:false,
    search_name:'',
    switch_model:true,//模块切换
    switch_button:true,//注册模块button切换
    init_button:true,//未选择树节点时，按钮的显示状态为不可点击
    input_disable:true,//平常状态下不可编辑
    always_disable:true,//一直不得修改
    add_edit:true,//新增或修改
    //分页属性
    pageInfo: {
        current: 0,
        pagecount: 200
    }
};
var adminVue=new Vue({
    el:"#admin_el",
    data:userData,
    watch:{
        search_name:function (data) {
            this.query_search(data);
        }
    },
    methods:{
        //初始化分页方法
        init: function () {
            $("#Pagination").pagination(this.pageInfo.pagecount, {
                current_page: this.pageInfo.current,
                callback: this.nextPage
            });
        },
        nextPage: function (current) {
            this.pageInfo.current = current;
            this.goto()
        },
        //分页跳转方法
        goto: function () {
            $.ajax({
                url:'/gcbin/query_usertype',
                data:JSON.stringify({"user_type":"all","page":userData.pageInfo.current*10}),
                dataType:'json',
                type:'POST',
                contentType: "application/json; charset=utf-8",
                success:function (result) {
                    userData.users=result.data;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    setTimeout(function () {
                        swal.close();
                    }, 500);
                }
            })
        },
        query_usertype:function (resdata) {
            userData.switch_model=true;
            userData.current_usertype=resdata;//用来指定颜色
            if(userData.current_usertype==='all'){
                userData.page_show=true;
            }else{
                userData.page_show=false;
            }
            var that=this;
            swal({
                //text:"加载中...",
                imageUrl:"../../resource/images/preloader.gif",
                showConfirmButton: false,
                allowOutsideClick: false,
                allowEscapeKey: false
            }).catch(swal.noop);
            $.ajax({
                url:'/gcbin/query_usertype',
                data:JSON.stringify({"user_type":resdata,"page":0}),
                dataType:'json',
                type:'POST',
                contentType: "application/json; charset=utf-8",
                success:function (result) {
                    setTimeout(function (){
                        userData.users=result.data;
                        userData.pageInfo.pagecount=result.status;
                        userData.pageInfo.current=0;
                        if(userData.current_usertype==='all'){

                        }
                        userData.page_show=true;
                        that.init();//初始化分页
                        swal.close()},300);
                }
            });
        },
        /**
         * 动态模糊查询方法
         * */
        query_search:function (resdata) {
            var user_name=resdata.trim();
            $.ajax({
                url:'/gcbin/query_search',
                data:JSON.stringify({"user_name":user_name}),
                dataType:'json',
                type:'POST',
                contentType:"application/json; charset=utf-8",
                success:function (result) {
                    userData.users=result.data;
                    userData.page_show=false;//隐藏分页栏
                    //userData.current_usertype=null;//置状态为空，隐藏分页栏
                }
            });
        },
        /**
         * 删除用户
         * */
        delete_user:function (resdata) {
            //不能删掉自己和同级别的人
            if(resdata.user_name===topDatas.userInfo.user_name){
                swal({
                    text:"用户"+resdata.user_name+"已在本机登录",
                    type:"error",
                    confirmButtonText:"确定"
                });
                return;
            }else if(resdata.user_type===1){
                swal({
                    text:"用户"+resdata.user_name+"为管理员权限",
                    type:"error",
                    confirmButtonText:"确定"
                });
                return;
            }
            swal({
                text:"确认删除"+resdata.user_name+"?",
                type:"question",
                showCancelButton:true,
                confirmButtonText:"确定",
                cancelButtonText:"取消",
                reverseButtons:true
            }).then(
                function () {
                    $.ajax({
                        url: '/gcbin/delete',
                        data: JSON.stringify({"user_name": resdata.user_name}),
                        dataType: 'json',
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        success: function (result) {
                            userData.users.splice(userData.users.indexOf(resdata), 1);
                            swal({
                                text:"用户："+resdata.user_name+"，已删除",
                                type:"success",
                                timer:500
                            }).catch(swal.noop);
                        }
                    });
                });
        },
        /**
         * 踢掉用户
         * */
        kicking:function (resdata) {
            //不能踢掉自己和同级别的人
            if(resdata.user_name===topDatas.userInfo.user_name){
                swal({
                    text:"用户"+resdata.user_name+"已在本机登录",
                    type:"error",
                    confirmButtonText:"确定"
                });
                return;
            }else if(resdata.user_type===1){
                swal({
                    text:"用户"+resdata.user_name+"为管理员权限",
                    type:"error",
                    confirmButtonText:"确定"
                });
                return;
            }
            $.ajax({
                url: '/gcbin/kicking',
                data:{user_name:resdata.user_name},
                dataType:"text",
                type:"POST",
                //contentType: "application/json; charset=utf-8",//不指定时为String
                success: function (result) {
                    userData.users.splice(userData.users.indexOf(resdata), 1);
                    swal({
                        text:resdata.user_name+",已下线",
                        type:"success",
                        timer:500
                    }).catch(swal.noop);
                }
            });
        },


        /*---------左边树开始---------*/

        /**
         * 查询树
         * */
        query_tree:function () {
            var that=this;
            $.ajax({
                url:'/gcbin/query_tree',
                data:JSON.stringify({"username":"test"}),
                type:"POST",
                contentType: "application/json;charset=utf-8",
                success:function (result) {
                    userData.resource=result.data;
                    that.init_tree();
                }
            })
        },
        /**
         * 保存树的数据
         * @param resdata 树形菜单的数据
         *
         * */
        save_tree:function (resdata) {
            var that=this;
            $.ajax({
                url:'/gcbin/save_tree',
                data:JSON.stringify(resdata),//相当于userData.current_model
                dataType:'json',
                type:'POST',
                contentType:'application/json;charset=utf-8',
                success:function (result) {
                    if(result.success===true){
                        topDatas.root_info=[];
                        if(userData.add_edit===true){//新增态
                            userData.resource.push(result.data[0]);
                            //Vue.set(userData,'resource',userData.resource);//push可以动态生成，不需要set
                            topDatas.treeInfo.push(result.data[0]);
                            for(var n=0;n<topDatas.treeInfo.length;n++){
                                if(topDatas.treeInfo[n].pk_parent===0){
                                    topDatas.root_info.push(topDatas.treeInfo[n]);
                                }
                            }
                            for(var j=0;j<topDatas.root_info.length;j++){
                                topDatas.root_info[j].children=[];
                                for(var k=0;k<topDatas.treeInfo.length;k++){
                                    if(topDatas.root_info[j].pk_resource===topDatas.treeInfo[k].pk_parent){
                                        topDatas.root_info[j].children.push(topDatas.treeInfo[k]);
                                    }
                                }
                            }
                        }else{//编辑态
                            for(var i=0;i<userData.resource.length;i++){
                                if(userData.resource[i].pk_resource===result.data[0].pk_resource){
                                    userData.resource.splice(i,1,result.data[0]);//List数组，为data[0],之前用的是Map
                                    topDatas.treeInfo.splice(i,1,result.data[0]);//导航栏增加
                                }
                            }
                            for(var n=0;n<topDatas.treeInfo.length;n++){
                                if(topDatas.treeInfo[n].pk_parent===0){
                                    topDatas.root_info.push(topDatas.treeInfo[n]);
                                }
                            }
                            for(var j=0;j<topDatas.root_info.length;j++){
                                topDatas.root_info[j].children=[];
                                for(var k=0;k<topDatas.treeInfo.length;k++){
                                    if(topDatas.root_info[j].pk_resource===topDatas.treeInfo[k].pk_parent){
                                        topDatas.root_info[j].children.push(topDatas.treeInfo[k]);
                                    }
                                }
                            }
                        }
                        userData.switch_button=true;
                        userData.init_button=true;
                        userData.input_disable=true;
                        userData.current_model={};
                        swal({
                            text:"保存成功",
                            type:"success",
                            timer:500
                        }).catch(swal.noop);
                    }else{
                        swal({
                            text:"保存失败"+result.message,
                            type:"error",
                            timer:500
                        }).catch(swal.noop);
                    }
                    that.init_tree();
                    //Vue.set(userData,"resource",userData.resource);//重新渲染(数据已经保存在zNodes中，无法直接渲染)
                }
            });

        },
        /**
         * 删除树节点
         * */
        delete_tree:function (resdata) {
            var that=this;
            swal({
                type:"warning",
                text:"删除后不可恢复,确定继续？",
                showCancelButton:true,
                confirmButtonText:"心意已决",
                cancelButtonText:"下次先"
            }).then(function () {
                $.ajax({
                    url:'/gcbin/delete_tree',
                    data:JSON.stringify(resdata),
                    dataType:'json',
                    type:'POST',
                    contentType:'application/json;charset=utf-8',
                    success:function (result) {
                        if(result.success===true){
                            topDatas.root_info=[];
                            for(var i=0;i<userData.resource.length;i++){
                                if(resdata.pk_resource===userData.resource[i].pk_resource){
                                    swal({
                                        type:"success",
                                        text:resdata.funcode+"-"+resdata.funname+"已删除"
                                    }).catch(swal.noop);
                                    userData.resource.splice(i,1);
                                    topDatas.treeInfo.splice(i,1);//导航栏
                                    that.init_tree();
                                    userData.init_button=true;
                                    userData.input_disable=true;
                                    userData.current_model={}//清空
                                }
                            }
                            for(var n=0;n<topDatas.treeInfo.length;n++){
                                if(topDatas.treeInfo[n].pk_parent===0){
                                    topDatas.root_info.push(topDatas.treeInfo[n]);
                                }
                            }
                            for(var j=0;j<topDatas.root_info.length;j++){
                                topDatas.root_info[j].children=[];
                                for(var k=0;k<topDatas.treeInfo.length;k++){
                                    if(topDatas.root_info[j].pk_resource===topDatas.treeInfo[k].pk_parent){
                                        topDatas.root_info[j].children.push(topDatas.treeInfo[k]);
                                    }
                                }
                            }
                        }
                    }
                })
            });

        },
        /**
         * 新增树节点
         * */
        add_node_tree:function (resdata) {
            var now_date=new Date();
            userData.switch_button=false;
            userData.input_disable=false;
            userData.current_model={
                pk_parent:resdata.pk_resource,//自动带父节点
                creator:topDatas.userInfo.user_name,
                creationtime:now_date.toLocaleString()
            }
        },
        /**
         * 新增树目录
         * */
        add_root_tree:function () {
            var now_date=new Date();
            userData.switch_button=false;
            userData.input_disable=false;
            userData.current_model={
                pk_parent:0,
                creator:topDatas.userInfo.user_name,
                creationtime:now_date.toLocaleString()
            }//清空current
        },
        edit_tree:function (resdata) {
            var now_date=new Date();
            if(resdata.pk_parent===null||resdata.pk_parent.length===0){
                userData.current_model.pk_parent=0;
            }
            userData.current_model.modifier=topDatas.userInfo.user_name;
            userData.current_model.modifiedtime=now_date.toLocaleString();
            userData.input_disable=false;
            userData.switch_button=false;
            userData.add_edit=false;//变为编辑态
        },
        /**
         * 初始化树
         * */
        init_tree:function () {
            //$.fn.zTree.destroy("funTree");
            userData.resource.forEach(function (data) {
                data.tree_name=data.funcode+" "+data.funname;
            });
            $.fn.zTree.init($("#funTree"),setting,userData.resource);
            var treeObj = $.fn.zTree.getZTreeObj("funTree");
            treeObj.expandAll(true);

        },
        /**
         * 取消
         * */
        cancel_tree:function () {
            userData.switch_button=true;
            userData.init_button=true;
            userData.input_disable=true;
            userData.current_model={}//清空
        },
        /**
         * 翻译用户类型
         * */
        translate_usertype:function (data) {
            if(data===0){
                return "会员"
            }else if(data===1){
                return "管理员"
            }
        },
        /**
         * 翻译在线状态
         * */
        translate_online:function (data) {
            if(data===0){
                return "离线";
            }else if(data===1){
                return "在线";
            }else if(data===2){
                return ""
            }
        },
        /**
         * 翻译锁定状态
         * */
        translate_islock:function (data) {
            if(data===0){
                return "未锁定";
            }else if(data===1){
                return "锁定";
            }
        },
        /**
         * 改变字段颜色
         * */
        color_change:function (data) {
            if(data===0){
                return "bg-red"
            }else if(data===1){
                return "bg-green"
            }
        }
    },
    mounted:function () {
        //this.$refs.ref_top.query_userinfo();//可以通过组件查询
        this.query_tree();
        this.query_usertype('all');
        this.$refs.ref_top.switch_nav("0");
    }
});
/*--------------树形菜单的js方法--------------*/
/*单击换到卡片界面*/
function editData(event, treeId, treeNode) {
    userData.init_button=false;//点击节点后，按钮可点击
    userData.switch_model=false;
    userData.current_model=treeNode;
}