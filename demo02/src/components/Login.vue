<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
                <h1>
                    <el-link href="/register" target="_blank" style="margin-top:2%; margin-left:35%; font-size: 30px;font-weight: 10000;"
                    >用户登录</el-link>
                </h1>
                
                <!-- <h1 class="login-title">用户登录</h1> -->
                <el-form :model="loginForm" label-width="100px"
                ref="loginForm" style="margin-top:5%;">
                    <el-form-item label="账号" prop="no">
                        <el-input style="width: 200px;" type="text" v-model="loginForm.no"
                        autocomplete="off" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input style="width: 200px;" type="password" v-model="loginForm.password"
                        show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    
                    <el-form-item style="margin-left: -5vh; margin-top:-3vh">
                        <el-radio-group v-model="loginForm.type"> 
                        <el-radio label="学生"></el-radio>
                        <el-radio label="单位"></el-radio>
                        <el-radio label="管理员"></el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="confirm" :disabled="confirm_disabled"
                        style="margin-left: -1vh; width: 200px; height: 36px;">确 定</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                confirm_disabled:false,
                loginForm:{
                    no:'',
                    password:'',
                    type:'学生'
                },
                rules:{
                    no: [
                        { require: true, message: "请输入账号", trigger: 'blur' }
                    ],
                    password: [
                        { require: true, message: "请输入密码", trigger: 'blur' }
                    ]
                },
                aside: []
            }
        },

        methods: {
            confirm() {
                this.confirm_disabled=true
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        if (this.loginForm.type == '学生') {
                            console.log(this.loginForm.type)
                            this.$axios.post(this.$httpUrl + '/student/login', this.loginForm).
                            then(res=>res.data).then(res=>{
                                console.log(res)
                                if (res.code == 200) {
                                    this.aside = []
                                    this.aside.push({index: '1', class: 'el-icon-s-home', title: '我的首页', index1: [{index: '/myStudentInfor', title: '个人信息'}]})
                                    this.aside.push({index: '2', class: 'el-icon-search', title: '岗位搜索', index1: [{index: '/mySearchPostInfor', title: '岗位信息'}, {index: '/mySend', title: '我的申请'}]})
                                    this.aside.push({index: '3', class: 'el-icon-user-solid', title: '我的岗位', index1: [{index: '/myPost', title: '我的岗位'}]})
                                    this.aside.push({index: '4', class: 'el-icon-chat-dot-round', title: '信息中心', index1: [{index: '/myInfor', title: '我的消息'}]})
                                    this.aside.push({index: '5', class: 'el-icon-s-tools', title: '设置中心', index1: [{index: '/mySet', title: '设置'}]})
                                    sessionStorage.setItem("Aside", JSON.stringify(this.aside))
                                    sessionStorage.setItem("CurUser", JSON.stringify(this.loginForm))
                                    this.$router.replace('/Index')
                                } else {
                                    this.$message({
                                        message: '登录失败',
                                        type: 'error'
                                    });
                                }
                            });
                        } else if (this.loginForm.type == '单位') {
                            console.log(this.loginForm.type)
                            this.$axios.post(this.$httpUrl + '/company/login', this.loginForm).
                            then(res=>res.data).then(res=>{
                                console.log(res)
                                if (res.code == 200) {
                                    this.aside = []
                                    this.aside.push({index: '1', class: 'el-icon-s-home', title: '我的首页', index1: [{index: '/hisCompanyInfor', title: '单位信息'}]})
                                    this.aside.push({index: '2', class: 'el-icon-search', title: '岗位管理', index1: [{index: '/hisPostInfor', title: '岗位信息'}, {index: '/hisPostApply', title: '申请岗位'}]})
                                    this.aside.push({index: '3', class: 'el-icon-user-solid', title: '申请列表', index1: [{index: '/hisStudentPostApply', title: '岗位申请'}, {index: '/hisLeaveApply', title: '离职申请'}]})
                                    this.aside.push({index: '4', class: 'el-icon-collection-tag', title: '工资管理', index1: [{index: '/hisMoney', title: '工资发放'}]})
                                    this.aside.push({index: '5', class: 'el-icon-chat-dot-round', title: '信息中心', index1: [{index: '/hisInfor', title: '我的消息'}]})
                                    this.aside.push({index: '6', class: 'el-icon-s-tools', title: '设置中心', index1: [{index: '/hisSet', title: '设置'}]})
                                    sessionStorage.setItem("Aside", JSON.stringify(this.aside))
                                    sessionStorage.setItem("CurUser", JSON.stringify(this.loginForm))
                                    this.$router.replace('/Index')
                                } else {
                                    this.$message({
                                        message: '登录失败',
                                        type: 'error'
                                    });
                                }
                            });
                        } else if (this.loginForm.type == '管理员') {
                            console.log(this.loginForm.type)
                            this.$axios.post(this.$httpUrl + '/manager/login', this.loginForm).
                            then(res=>res.data).then(res=>{
                                console.log(res)
                                if (res.code == 200) {
                                    this.aside = []
                                    this.aside.push({index: '1', class: 'el-icon-message', title: '商家管理', index1: [{index: '/companyManage', title: '商家信息'}]})
                                    this.aside.push({index: '2', class: 'el-icon-menu', title: '岗位管理', index1: [{index: '/postInfor', title: '岗位信息'},{index: '/postApply', title: '岗位申请'}]})
                                    this.aside.push({index: '3', class: 'el-icon-user-solid', title: '学生管理', index1: [{index: '/studentInfor', title: '学生信息'}]})
                                    this.aside.push({index: '4', class: 'el-icon-chat-dot-round', title: '信息管理', index1: [{index: '/informationManage', title: '我的信息'}]})
                                    console.log(this.aside)
                                    sessionStorage.setItem("CurUser", JSON.stringify(this.loginForm))
                                    sessionStorage.setItem("Aside", JSON.stringify(this.aside))
                                    this.$router.replace('/Index')
                                } else {
                                    this.$message({
                                        message: '登录失败',
                                        type: 'error'
                                    });
                                }
                            });
                        }
                    } else {
                        console.log('校验失败');
                        return false;
                    }
                    this.confirm_disabled=false;
                });
            }
        }
    }
</script>

<style scoped>
    .loginBody {
        position:absolute;
        width:100%;
        height:100%;
        background-color:#B3c0D1;
    }
    .loginDiv {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -200px;
        margin-left: -250px;
        width: 450px;
        height: 330px;
        background: #fff;
        border-radius: 5%;
    }
    .login-title {
        margin: 20px 0;
        text-align: center;
    }
    .login-content {
        width: 400px;
        height: 250px;
        position: absolute;
        top: 25px;
        left: 25px;
    }
</style>