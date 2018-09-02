const Foo = {template:'<div>foo666</div>'};
const Bar = {template:'<div>bar666</div>'};
const routes = [
    {path:'/foo', component:Foo},
    {path:'/bar', component:Bar}
    ];
const router = new VueRouter({
    routes
});
const app = new Vue({
    router
}).$mount('#app');