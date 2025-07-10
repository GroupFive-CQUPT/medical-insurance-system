<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>



<script lang="ts">
import {defineComponent} from 'vue'

export default defineComponent({
name: "Aside"
})
</script>



<style scoped>

</style>