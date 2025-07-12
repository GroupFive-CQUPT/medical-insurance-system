<template>
  <el-header id="header">
    <div >
      <img style="width:150px;height:40px;margin:20px 0 0 -12px" src=""/><!-- 替换为logo图片路径 -->
    </div>

    <el-dropdown split-button type="default" >
      {{user.nickname}}
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item  @click="logout">注销</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>

  </el-header>
</template>
<script>
import {getSessionStorage,setSessionStorage} from '@/utils/common.js'
export default {
  name: "Header",
  data(){
    return{
      user:getSessionStorage('user')
    }
  },
  methods: {
    logout(){
      sessionStorage.setItem('token',null)
      setSessionStorage('user',null)
      this.$store.commit('addMenus',[])
      this.$store.commit('clearTab',[])
      this.$router.push("/")
    }
  },

}
</script>


<style scoped>

</style>