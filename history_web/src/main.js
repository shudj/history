// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Router from './router'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import Store from './store'
import './style/index.scss' //global css
import Axios from './axios'


Vue.use(ElementUI) //使用elementUI
Vue.use(Axios)
Vue.prototype.$ajax = Axios

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: Router,
  store: Store,
  components: { App },
  template: '<App/>'
})
