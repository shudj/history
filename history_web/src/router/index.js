import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import menuDetail from '@/pages/menuDetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      children: [
        {
          path: 'menuDetail',
          name: 'menuDetail',
          component: menuDetail
        }
      ]
    }
  ]
})
