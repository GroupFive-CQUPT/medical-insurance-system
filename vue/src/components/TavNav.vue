<template>
  <div class="container_tab">
    <ul class="tab_nav_box">
      <li v-for="(item, index) in $store.getters.tabs"
          :key="item.title"  :class="{ active: $route.path === item.path }" >
        <router-link :to="item.path">{{item.title}}</router-link>
        <el-icon v-if="index !== 0">
          <CloseBold @click="onCloseTabIndex(index)"/>
        </el-icon>
      </li>
    </ul>

  </div>


</template>

<script>
export default {
  name: "TabNav",

  methods: {

    onCloseTabIndex(index){
      this.$store.commit("deleteTabByIndex",index);
      //获取上一个tab
      let path = this.$store.getters.tabs[this.$store.getters.tabs.length-1].path;
      this.$router.push(path);
    }
  },
}
</script>

<style scoped>

</style>