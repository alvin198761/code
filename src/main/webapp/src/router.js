import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Swagger from './views/Swagger.vue'
import Code from './views/Code.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: '主页',
            component: Home,
            children: [
                {
                    path: 'code',
                    name: '代码生成',
                    component: Code,
                },
                {
                    path: 'api',
                    name: 'Swagger客户端生成',
                    component: Swagger,
                }
            ]
        }
    ]
})
