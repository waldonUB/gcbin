var page_template='<ul class="pagination">\n' +
    '      <li  @click="goto(1)"><a href="#">首页</a></li>\n' +
    '      <li v-show="currentpage != 1" @click="currentpage&#45;&#45; && goto(currentpage&#45;&#45;)"><a href="#">上一页</a></li>\n' +
    '      <li v-show="all_page != 1" v-for="index in pages" @click="goto(index)"\n' +
    '           :key="index">\n' +
    '        <a href="#">{{index}}</a>\n' +
    '      </li>\n' +
    '      <li v-show="all_page != currentpage && all_page != 0" @click="currentpage++ && goto(currentpage++)"><a\n' +
    '              href="#">下一页</a></li>\n' +
    '      <li v-show="all_page != 0" @click="goto(all_page)"><a href="#">共{{all_page}}页</a></li>\n' +
    '    </ul>';
var page_temlate2='<h1>ssss</h1>'
var pageData={
    pages:20,
    currentpage:0,
    all_page:5,
    show_item:5
};
Vue.component("page_component",{
    template:page_template,
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
                url:'http://localhost:8080/SpringMybatis_001/query_usertype',
                data:JSON.stringify({"user_type":resdata,"page":index}),
                dataType:'json',
                type:'POST',
                contentType: "application/json; charset=utf-8",
                success:function (result) {
                    userData.users=result.data;
                }
            });
        }
    },
    mounted:function () {
        console.log("success_page");
    }
});