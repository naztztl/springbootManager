<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'"
              style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%)">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
    </el-aside>

    <el-container>
      <el-header style="">
        <Header :collapse="collapse" :collapseBtnClass="collapseBtnClass"  :user="user" />
      </el-header>

      <el-main>
<!--        表示当前页面的子路由会在 router-view 里面展示  index.js里的children-->
        <router-view @refreshUser="getUser"/>
      </el-main>
    </el-container>
  </el-container>

</template>

<style>
el-menu {
}

.el-header {
  /*background-color: #B3C0D1;*/
  /*color: #333;*/
  border-bottom: #8c939d solid 1px;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

</style>

<script>
// @ is an alias to /src

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      user: {},
    }
  },
  created() {
    //一开始从后台获取最新的user数据
    this.getUser()
  },
  components: {
    Aside,
    Header,
  },
  methods: {
    collapse() { //点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) { //收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else { //展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    getUser() {
      let username = localStorage .getItem("user") ? JSON.parse(localStorage .getItem("user")).username : ''
      //从后台获取user数据
      this.request.get('/user/username/' + username).then(res => {
        // 重新赋值后台最新的user数据
        this.user = res.data
      })
    },
  }
}
</script>

