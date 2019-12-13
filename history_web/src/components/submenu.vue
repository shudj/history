<template functional>
  <div class="menu-item-wrapper">
    <el-submenu :index="props.data.id.toString()">
      <template slot="title">
        <i :class="props.data.icon" aria-hidden="true"></i>
        <span>{{props.data.name}}</span>
      </template>
      <template v-for="item in props.data.children">
        <router-link :to="item.url" :key="item.id" v-if="item.children.length===0">
          <el-menu-item class="submenu-title-noDropdown" :index="item.id.toString()">
            <i :class="item.icon" aria-hidden="true"></i>
            <span slot="title">{{item.name}}</span>
          </el-menu-item>
        </router-link>
        <sub-menu v-else :data="item" :key="item.id"></sub-menu>
      </template>
    </el-submenu>
  </div>
</template>

<script>
export default {
  name: "submenu",
  props: {
    data: []
  }
}
</script>