<template>
    <el-container style="height: 100%; border: 1px solid #eee">
        <el-aside width="250px" style="background-color: rgb(238, 241, 246); margin-left: -1px; margin-top: -1px;">
            <Aside></Aside>
        </el-aside>
        
        <el-container style="height: 100%">
            <el-header style="text-align: right; font-size: 16px; height: 100%;  margin-left: -1px;
            border-bottom: rgba(168, 168, 168, 0.3) 1px solid;">
                <Header></Header>
            </el-header>
            
            <el-main style="height: 100vh;">
                <!-- <Main></Main> -->
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import Aside from "./Aside.vue";
import Header from './Header.vue';
import Main from './Main.vue'

    export default {
        name: "Index",
        components: {
            Aside,
            Header,
            Main
        },
        
    }
</script>

<style scoped>
    .el-header {
        /* background-color: #B3C0D1; */
        color: #333;
        line-height: 60px;
    }

    .el-main {
        padding: 5px;
    }
    
    .el-aside {
        color: #333;
  }
</style>