var top_template='<div class="header col-lg-12" style="background-color: #FFFFFF;margin-bottom: 10px">\n' +
    '       <div class="col-lg-2 col-md-2 col-sm-1 col-xs-1"><img class="pull-right" v-bind:src="logo" style="width: 65%;height: 65%;"></div>'+
    '        <div class="col-lg-10 col-md-10 col-sm-9 col-xs-9" style="padding-top: 5px">\n' +
    '          <div class="col-lg-12" >\n' +
    '            <span class="col-lg-9 col-md-9 col-sm-7 col-xs-7">' +
    '            <ul class="nav nav-tabs">\n' +
    '                <li @click="switch_nav(tree_node.funcode)" :class="{active:topDatas.nav_node===tree_node.funcode}" v-for="tree_node,index in root_info" role="presentation">' +
    '                    <a :href="tree_node.urls===undefined?\'#\':tree_node.urls" class="dropdown-toggle" :data-toggle="tree_node.children.length>0?topDatas.dropdown:\'\'">{{tree_node.funname}} <i :class="tree_node.resicon"></i></a>' +
    '                    <ul v-if="tree_node.children.length!==undefined&&tree_node.children.length>0" class="dropdown-menu">\n' +
    '                        <li v-for="second_node,index in tree_node.children"><a :href="second_node.urls===undefined?\'#\':second_node.urls">{{second_node.funname}}<i :class="second_node.resicon" class="col-lg-4 pull-right"></i></a></li>\n' +
    '                    </ul>' +
    '                </li>\n' +
    '            </ul>' +
    '            </span>'+
    '            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 dropdown user user-menu">' +
    '               <a href="#" class="dropdown-toggl" data-toggle="dropdown">'+
    '               <span class="col-lg-3"><img id="little_head" class="user-image" style="height: 40px;width: 40px" v-bind:src="head_image"></span>'+
    '               <span class="col-lg-8"><i style="font-family:\'楷体\';font-size: 16px;">{{topDatas.userInfo.user_name}}</i></span>'+
    '               </a>'+
    '               <ul class="dropdown-menu">'+
    '               <a href="#" class="user-header"><img @click="modify_headPic()" v-bind:src="head_image" class="img-circle" style="height: 160px;width: 175px" alt="User Image">\n' +
    '               <input id="file" type="file" class="hidden">'+
    '               </a>'+
    '               <li class="user-body"><div class="row">\n' +
    '                  <div class="col-xs-4 text-center">\n' +
    '                    <a href="#"></a>\n' +
    '                  </div>\n' +
    '                  <div class="col-xs-4 text-center">\n' +
    '                    <a href="#"></a>\n' +
    '                  </div>\n' +
    '                  <div class="col-xs-4 text-center">\n' +
    '                    <a href="#"></a>\n' +
    '                  </div>\n' +
    '                </div></li>'+
    '               <li class="user-footer"><div class="pull-left">\n' +
    '                  <a href="javascript:void(0)" class="btn btn-default btn-flat" @click="edit_password()">修改密码</a>\n' +
    '                </div>\n' +
    '                <div class="pull-right">\n' +
    '                  <a href="javascript:void(0)" class="btn btn-default btn-flat" @click="loginout" style="margin-right: 0;">注销登录</a>\n' +
    '                </div></li>'+
    '               </ul>'+
    '            </div>'+
    '          </div>\n' +
    '        </div>\n' +
    '      </div>';
var topDatas={
    head_image:null,
    logo:"../../resource/images/one_piece.jpg",
    currentInfo:{},
    userInfo:[],
    treeInfo:[],//根据tree生成的导航栏信息
    update_pwd:false,
    base64:'',
    nav_node:'',
    root_info:[],
    dropdown:'dropdown'
};
var img_result = {

};
var base64='';
/**
 * fileReader 修改头像
 * */
/*var oFReader=new FileReader();
oFReader.onload=function (oFREvent) {
    var vm=this;
    document.getElementById("little_head").src=oFREvent.target.result;
    topDatas.head_image=oFREvent.target.result;
};
function loadImageFile() {
    if(document.getElementById("file").files.length===0){return;}
    var oFile=document.getElementById("file").files[0];
    oFReader.readAsDataURL(oFile);
}*/


Vue.component("top",{
    template:top_template,
    data:function () {
        return topDatas;
    },
    watch:{

    },
    methods:{
        loginout:function () {
            if(topDatas.update_pwd===true){
                    $.ajax({
                        url:'/PetsCT/loginout',
                        data:JSON.stringify({"user_name":topDatas.userInfo.user_name}),
                        dataType:'json',
                        type:'POST',
                        contentType: "application/json; charset=utf-8",
                        success:function (result) {
                            location.href='/PetsCT';
                        }
                    });
            }else{
                swal({
                    text:"确认注销？",
                    type:"question",
                    showCancelButton:true,
                    confirmButtonText:"确定",
                    cancelButtonText:"取消",
                    reverseButtons:true
                }).then(function () {
                    swal({
                        title:"注销成功",
                        imageUrl:"../../resource/images/dog_load.gif",
                        showConfirmButton: false,
                        allowOutsideClick: false,
                        allowEscapeKey: false
                    });
                    setTimeout(function () {
                        $.ajax({
                            url:'/PetsCT/login_out',
                            data:JSON.stringify({"user_name":topDatas.userInfo.user_name}),
                            dataType:'json',
                            type:'POST',
                            contentType: "application/json; charset=utf-8",
                            success:function (result) {
                                location.href='/PetsCT';
                            }
                        });
                    },2000)
                });
            }
        },
        query_userinfo:function () {
            $.ajax({
                url:'/PetsCT/query_userinfo',
                data:JSON.stringify({"head_img":topDatas.head_image}),
                dataType:'json',
                type:'POST',
                contentType: "application/json; charset=utf-8",
                success:function (result) {
                    //直接粘贴网址是没有登陆信息的
                    if(result.data===null){
                        location.href='/PetsCT';
                    }/*else if(result.data.user_type===1){
                        location.href='/PetsCT/home/admin.html';
                    }else if(result.data.user_type===0){
                        location.href='/PetsCT/home/userindex.html';
                    }*/
                    topDatas.userInfo=result.data;
                    topDatas.head_image=topDatas.userInfo.head_img;//头像字段
                    //判断head_img是否有数据，设置默认头像
                    if(topDatas.head_image===null||topDatas.head_image===""){
                        Vue.set(topDatas,"head_image","../../resource/images/user_head_test.jpg");
                        //topDatas.head_image="../resource/images/user_head_test.jpg";
                    }

                    console.log(result.data);
                    console.log(topDatas.userInfo);
                },
                error:function (result) {
                    location.href='/PetsCT';
                }
            });
            },
        edit_password:function () {
            var that=this;
            swal({
                html: '请输入新密码<input type="text" id="newpwd" class="swal2-input" placeholder="请输入新密码" value="">'+
                    '请确认新密码<input type="text" id="newpwd2" class="swal2-input" placeholder="请确认新密码" value="">',
                //type: "info",
                imageUrl:'../../resource/images/modify_password.gif',
                cancelButtonText:"我再想想...",
                showCancelButton: true,
                reverseButtons:true
            }).then(
                function (){
                    var password=$("#newpwd").val().trim();
                    var password2=$("#newpwd2").val().trim();
                    if(password===password2){
                        $.ajax({
                            url:"/PetsCT/edit_password",
                            data:JSON.stringify({"user_name":topDatas.userInfo.user_name,"password":password}),
                            dataType:'json',
                            type:'POST',
                            contentType: "application/json; charset=utf-8",
                            success:function (result) {
                                if(result.success){
                                    topDatas.update_pwd=true;
                                    swal({
                                        type:"success",
                                        text:"您已修改密码，请重新登录",
                                        //imageUrl:"../resource/images/dog_load.gif",
                                        closeOnConfirm:false,
                                        showConfirmButton: false,
                                        allowOutsideClick: false,
                                        allowEscapeKey: false
                                    });
                                    setTimeout(function () {
                                        that.loginout();
                                    },1500)
                                }
                            }
                        });
                    }else{
                        swal({
                            type:"error",
                            text:"密码不一致请重新输入",
                            //imageUrl:"../resource/images/dog_load.gif",
                            closeOnConfirm:false,
                            showConfirmButton: true,
                            allowOutsideClick: false,
                            allowEscapeKey: false
                        });
                    }
                }
            )
        },
        /**
         * 修改头像,FileReader(gif)
         * */
        modify_headPic:function () {
            var vm=this;
            $("#file").click();
            $("#file").change(function () {
                if(document.getElementById("file").files.length===0){return;}
                var oFReader=new FileReader();
                var oFile=document.getElementById("file").files[0];
                oFReader.readAsDataURL(oFile);
                oFReader.onload=function (oFREvent) {
                    //document.getElementById("little_head").src=oFREvent.target.result;
                    topDatas.base64=oFREvent.target.result;
                    $.ajax({
                        url:'/PetsCT/head_portrait',
                        data:JSON.stringify({"base64":topDatas.base64,"user_name":topDatas.userInfo.user_name}),
                        dataType:'json',
                        type:'POST',
                        contentType: "application/json; charset=utf-8",
                        success:function (result) {
                            if(result.success){
                                topDatas.head_image=result.data;
                                vm.query_userinfo();
                                swal({
                                    title:"修改成功",
                                    imageUrl:topDatas.head_image,
                                    showConfirmButton: false,
                                    allowOutsideClick: true,
                                    allowEscapeKey: false
                                }).catch(swal.noop());
                            }else{
                                Vue.set(topDatas,"head_image",topDatas.head_image);
                                swal({
                                    type:"error",
                                    showConfirmButton: true,
                                    allowOutsideClick: true,
                                    text:result.message
                                }).catch(swal.noop());
                            }
                        },
                        error:function (result) {
                            swal({
                                type:"error",
                                showConfirmButton: true,
                                allowOutsideClick: true,
                                text:result.message
                            }).catch(swal.noop());
                        }
                    });
                };
            })
        },
        /**
         * 修改头像,canvas方法(不能放gif)
         * */
        /*modify_headPic:function () {
            var vm=this;
            $("#file").click();
            $("#file").change(function () {
                var fileObj = $("#file")[0];
                var windowURL = window.URL || window.webkitURL;
                var dataURL;
                if (fileObj && fileObj.files && fileObj.files[0]) {
                    dataURL = windowURL.createObjectURL(fileObj.files[0]);
                    //Vue.set(topDatas,"head_image",dataURL)//blob地址头像的预览
                }
                var img=new Image();
                img.src=dataURL;//这里是blob的数据
                img.onload=function () {
                    var that=this;
                    var w=that.width;
                    var h=that.height;
                    var scale=w/h;
                    //生成canvas
                    var canvas=document.createElement('canvas');
                    var ctx=canvas.getContext('2d');
                    ctx.fillStyle='rgba(255,255,255,0)';
                    $(canvas).attr({
                        width:w,
                        height:h
                    });
                    ctx.drawImage(that,0,0,w,h);
                    var filename=fileObj.value;
                    var suffix_point=filename.indexOf(".");
                    var suffix=filename.slice(suffix_point+1,filename.length);//后缀名
                    topDatas.base64 = canvas.toDataURL("image/png");
                    $.ajax({
                        url:'/PetsCT/head_portrait',
                        data:JSON.stringify({"base64":topDatas.base64,"user_name":topDatas.userInfo.user_name,"suffix":suffix}),
                        dataType:'json',
                        type:'POST',
                        contentType: "application/json; charset=utf-8",
                        success:function (result) {
                            if(result.success){
                                topDatas.head_image=result.data;
                                vm.query_userinfo();
                                swal({
                                    title:"修改成功",
                                    imageUrl:topDatas.head_image,
                                    showConfirmButton: false,
                                    allowOutsideClick: true,
                                    allowEscapeKey: false
                                }).catch(swal.noop());
                            }else{
                                Vue.set(topDatas,"head_image",topDatas.head_image);
                                swal({
                                    type:"error",
                                    showConfirmButton: true,
                                    allowOutsideClick: true,
                                    text:result.message
                                }).catch(swal.noop());
                            }
                        },
                        error:function (result) {
                            swal({
                                type:"error",
                                showConfirmButton: true,
                                allowOutsideClick: true,
                                text:result.message
                            }).catch(swal.noop());
                        }
                    });
                };

            });
        }*/
        /**
         * 获取Ztree的信息，并在导航栏自动生成
         * */
        query_tree_nav:function () {
            var that=this;
            $.ajax({
                url:'/PetsCT/query_tree',
                data:JSON.stringify({"username":"test"}),
                type:"POST",
                contentType: "application/json;charset=utf-8",
                success:function (result) {
                    topDatas.treeInfo=result.data;
                    for(var i=0;i<topDatas.treeInfo.length;i++){
                        if(topDatas.treeInfo[i].pk_parent===0){
                            topDatas.root_info.push(topDatas.treeInfo[i]);
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
            })
        },
        switch_nav:function (resdata) {//设置当前点击的颜色
            topDatas.nav_node=resdata;
        },
        /**
         * 定时查是否会话失效
         * */
        query_session:function () {
            $.ajax({
                url:"/PetsCT/query_session",
                type:"POST",
                data:JSON.stringify(topDatas.userInfo),
                contentType: "application/json;charset=utf-8",
                success:function (result) {
                    console.log("定时发送后台消息："+new Date());
                    if(result.success){

                    }else{
                        swal({
                            type:"warning",
                            allowOutsideClick: false,
                            text:result.message
                        }).then(function () {
                            location.href='/PetsCT';
                        })
                    }
                }

            });
        },
        /**
         * 直接发一个POST到后台多线程任务
         * */
        update_lasttime:function () {
            $.ajax({
                url:"/PetsCT/update_lasttime",
                type:"POST",
                data:JSON.stringify({"user_name":"test"}),
                contentType: "application/json;charset=utf-8",
                success:function (result) {
                    if(result.success){

                    }else{

                    }
                }

            });
        }

        },
        mounted:function () {
            this.query_userinfo();
            this.query_tree_nav();
            this.update_lasttime();
            setInterval(this.query_session,11*60*1000);
        }
});
var topVue=new Vue({
    el:"#top_el"
});
