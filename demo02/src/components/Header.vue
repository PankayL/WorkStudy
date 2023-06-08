<template>
    <div>
        
        <el-dropdown>
            <span>{{user.no}}</span>
            <i class="el-icon-arrow-down" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
            <el-dropdown-item @click.native="logOut">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                user : JSON.parse(sessionStorage.getItem('CurUser'))
            }
        },
        methods: {
            toUser() {
                console.log('to_use')
                this.$router.push("/Home")
            },
            logOut() {
                console.log('log_out')

                this.$confirm('是否确定退出登录', '提示', {
                    confirmButtonText: '确定',
                    type: 'warning',
                    center: true
                })

                .then(() => {
                    this.$message({
                        type: 'success',
                        message: '退出成功'
                    })

                    this.$router.push("/")
                    sessionStorage.clear()
                })

                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '成功取消'
                    })
                })
                
            }
        },
        // created() {
        //     this.$router.push("/header")
        // }
    }
</script>

<style scoped>

</style>