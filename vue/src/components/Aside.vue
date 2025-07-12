<template>
  <el-aside id="asideNav" width="150px">
    <div class="logo-name" >
      <p>医保报销系统</p>
      <img src="logo图片" class="logo"/>
    </div>

    <el-menu
        active-text-color="#FFE84D"
        background-color="#BCD1E0"
        :default-active="$route.path"
        :router="true"
        unique-opened
        text-color="#fff">

      <template v-for="menu in $store.getters.menus" >
        <el-sub-menu :index="menu.menusIndex" >
          <template #title>
            <el-icon>
              <!-- 动态获取图标 -->
              <component :is="menu.icon" />
            </el-icon>
            <span>{{menu.title}}</span>
          </template>
          <template v-for="menu in menu.children">
            <el-menu-item :index="menu.path"><el-icon>
              <component :is="menu.icon" />
            </el-icon>{{menu.title}}</el-menu-item>
          </template>
        </el-sub-menu>
      </template>
    </el-menu>
  </el-aside>

</template>



<script>
export default {
  name: 'Aside',
  methods: {

  },
  watch: {
    // 监听浏览器直接输入路由，将此路由添加到tabs
    "$route.path": {
      handler: function (newVal, oldVal) {
        if(true){
          return;
        }
        //判断当前tab中是否已经存在
        let index = this.$store.getters.tabs.findIndex(function (value, key) {
          return value.path === newVal;
        });
        if (index != -1 || newVal == "/login") {
          return;
        }
        let menuName = this.$store.getters.getMenuNameByUrl(newVal);
        //手动构建tab
        this.$store.commit("addTab", {
          title: menuName,
          path: newVal
        });
      },
      immediate: true
    }
  },
}
</script>



<style scoped>

</style>