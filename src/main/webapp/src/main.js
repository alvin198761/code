import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import http from './utils/http';

Vue.config.productionTip = false
Vue.use(iView);

const HttpPlugin = {
    install: function (Vue, options) {
        Vue.$http = http;
        Vue.prototype.$http = http;
    }
}

Vue.use(HttpPlugin);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
