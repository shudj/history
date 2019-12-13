<template>
  <div class="menu">
    <div class="logo-con">
      <div class="title" v-show="!isCollapse">
        <span class="title__sider-title is-active">{{logo}}</span>
      </div>
      <div class="title" v-show="isCollapse">
        <span class="title__sider-title el-tag--mini">LG</span>
      </div>
    </div>
    <el-menu 
      mode="vertical"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :default-active="$route.meta.pageId"
      :collapse="isCollapse"
    >
      <template v-for="item in list">
        <router-link :to="item.url" :key="item.id" v-if="item.children.length===0">
          <el-menu-item :index="item.id.toString()" class="submenu-title-noDropdown">
            <i :class="item.icon" aria-hidden="true"></i>
            <span slot="title">{{item.name}}</span>
          </el-menu-item>
        </router-link>
        <subMenu v-else :data="item" :key="item.id"></subMenu>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import subMenu from "./submenu";
export default {
  name: "menuList",
  components: {
    subMenu
  },
  computed: {
    ...mapGetters([
      'menus',
      'barEffect'
    ]),
    isCollapse() {
      return !this.barEffect.open
    }
  },
  methods: {
    getData() {
      var api = "/menu/listMenu"
      console.log("22222222222")
      this.$ajax.get(api).then(res => {
        this.list = res.data
        console.log(res)
        console.log(res.data)
        console.log(JSON.stringify(res.data))
      }).catch(error => {
        console.log(error.message)
      })
    }
  },
  data() {
    return {
      //当行菜单数据源
      list: [], 
      backgroundColor: "#304156", //导航菜单背景颜色
      textColor: "#BFCBD9", //导航菜单文字颜色
      logo: "LOGO" //logo
    };
  },
  mounted() {
    console.log("333333333")
    this.getData()
  }
};
</script>

<style lang="scss" scoped>
.el-menu {
  border-right: none;
  a {
    text-decoration: none;
  }
}
.logo-con {
  height: 23px;
  padding: 14px 5px;
  text-align: center;

  .title {
    position: relative;
    font-size: 20px;
    height: 23px;
    line-height: 23px;
    text-align: center;

    span {
      padding: 0 0px 0 0;
      color: #409eff;
      font-size: 20px;
    }
  }
}
</style>