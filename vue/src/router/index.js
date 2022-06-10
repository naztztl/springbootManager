import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
import HomeView from '../views/Manage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage.vue'),
    // redirect: "/home",
    children: [
      { path: 'home', name: '首页', component: () => import('../views/Home.vue')},
      { path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      { path: 'role', name: '角色管理', component: () => import('../views/Role.vue')},
      { path: 'menu', name: '菜单管理', component: () => import('../views/Menu.vue')},
      { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      { path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '*',
    name: 'NotFound',
    component: () => import('../views/404.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//注意 刷新页面会导致页面路由重置
// export const setRoutes = () => {
//   const storeMenus = localStorage.getItem("menus")
//   if (storeMenus) {
//     //拼装动态路由
//     const manageRoute = { path: '/', name: 'Manage', component: () => import('../views/Manage.vue'), redirect: "/home", children: [] }
//     //
//     const menus = JSON.parse(storeMenus)
//     menus.forEach(item => {
//       if (item.path) {
//         let itemMenu = {
//           path: item.path.replace('/', ''),
//           name: item.name,
//           component: () => import('../views/' + item.pagePath + '.vue')
//         }
//         manageRoute.children.push(itemMenu)
//       } else if (item.children.length) {
//         item.children.forEach(item => {
//           if (item.path) {
//             let itemMenu = {
//               path: item.path.replace('/', ''),
//               name: item.name,
//               component: () => import('../views/' + item.pagePath + '.vue')
//             }
//             manageRoute.children.push(itemMenu)
//           }
//         })
//       }
//     })
//
//     //获取当前的路由对象名称数组
//     const currentRouteNames = router.getRoutes().map(v => v.name)
//     if (!currentRouteNames.includes('Manage')) {
//       //动态添加到现在的路由对象中去
//       router.addRoute(manageRoute)
//     }
//   }
// }

// setRoutes()


router.beforeEach((to, from, next) => {
  console.log(from)
  localStorage.setItem("currentPathName", to.name)  //设置当前的路由名称,为了在Header组件中去使用
  store.commit("setPath")  //触发store的数据更新

  let user = JSON.parse(localStorage.getItem('user'));
  if (to.path === '/login' || to.path === '/register') return next()
  // //获取用户页面token信息
  // console.log('1: ' + localStorage.getItem('user'))
  // let token = localStorage.getItem('token')
  // //如果token数据为null则跳转到指定路径
  if (!user) {
    return next("/login")
  } else {
    console.log('1: ' + user.token)
    if (!user.token) return next("/login")
  }
  next()  //放行路由
})

export default router
