<template>
    <el-menu :default-openeds="['1']" style="height: 100%;"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#1fff"
        default-active="/companyManage"
        router
        >
        <div class="block"><el-avatar :size="130" :src="circleUrl" style="margin-top:25%; margin-left:20%; margin-bottom: 8vh;"></el-avatar></div>
        <!-- <el-submenu index="1" style="margin-top: 8vh">
            <template slot="title"><i class="el-icon-message"></i>商家管理</template>
            <el-menu-item index="/companyManage">商家信息</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>岗位管理</template>
            <el-menu-item index="/postInfor">岗位信息</el-menu-item>
            <el-menu-item index="/postApply">岗位申请</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title"><i class="el-icon-user-solid"></i>学生管理</template>
            <el-menu-item index="/studentInfor">学生信息</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title"><i class="el-icon-chat-dot-round"></i>信息管理</template>
            <el-menu-item index="/informationManage">我的信息</el-menu-item>
        </el-submenu> -->
        <li v-for="v in aside" :key="v.index">
            <el-submenu :index="v.index">
                <template slot="title"><i :class="v.class"></i>{{v.title}}</template>
                <li v-for="f in v.index1" :key="f.index">
                    <el-menu-item :index="f.index">{{f.title}}</el-menu-item>
                </li>
            </el-submenu>
        </li>
        
    </el-menu>
</template>

<script>
    export default {
        name: "Aside",
        data() {
            return {
                aside: JSON.parse(sessionStorage.getItem('Aside'))
            }
        }
    }
</script>

<style scoped>

</style>