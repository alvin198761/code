import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import CodeGen from './views/sys/CodeGen.vue';
import SawggerClientGen from './views/sys/SawggerClientGen.vue';

Vue.use(Router)


export default new Router({
    routes: [
        {
            path: '/',
            component: Home,
            name: '首页',
            redirect: '/code',
            children: [
                {
                    path: 'code', component: CodeGen, name: '代码生成',
                    path: 'swagger', component: SawggerClientGen, name: 'Swagger 客户端'
                }
            ]
        }
    ]
})
