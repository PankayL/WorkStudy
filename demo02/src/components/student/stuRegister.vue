<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
                <h1 class="login-title">用户注册</h1>
                <el-form :model="registerForm" label-width="100px"
                :rules="rules" ref="registerForm" style="margin-top:5%;">
                    
                    <el-form-item label="学号" prop="no">
                        <el-input style="width: 200px;" type="text" v-model="no"
                        autocomplete="off" size="small"></el-input>
                    </el-form-item>

                    <el-form-item label="姓名" prop="no">
                        <el-input style="width: 200px;" type="text" v-model="name"
                        autocomplete="off" size="small"></el-input>
                    </el-form-item>

                    <el-form-item label="性别" prop="no">
                        <el-radio-group v-model="sex"> 
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item label="密码" prop="no">
                        <el-input style="width: 200px;" type="text" v-model="password"
                        autocomplete="off" size="small"></el-input>
                    </el-form-item>
                    

                    <el-form-item label="学院" prop="no">
                        <el-select v-model="collegeValue" clearable placeholder="请选择学院" @blur="getMajor">
                            <el-option
                            v-for="item in collegeData"
                            :key="item.collegeNo"
                            :label="item.collegeName"
                            :value="item.collegeNo">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="专业" prop="no">
                        <el-select v-model="majorValue" clearable placeholder="请选择专业" @focus="getMajor" @blur="getClass">
                            <el-option
                            v-for="item in majorData"
                            :key="item.majorNo"
                            :label="item.majorName"
                            :value="item.majorNo">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="班级" prop="no">
                        <el-select v-model="classValue" clearable placeholder="请选择班级" @focus="getClass">
                            <el-option
                            v-for="item in classData"
                            :key="item.classNo"
                            :label="item.className"
                            :value="item.classNo">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <el-button type="primary" size="middle" style="margin-left: 90px; width: 200px; margin-top:10px;" @click="submit">注册</el-button>
                
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "StuRegister",
        data() {
            return {
                collegeData: [],
                collegeValue: '',
                majorData: [],
                majorValue: '',
                classData: [],
                classValue: '',
                    no: '',
                    password: '',
                    sex: '',
                    name: '',
                
            }
        },

        methods: {
            submit() {
                this.$axios.post(this.$httpUrl + '/student/add', {
                    stuNo: this.no,
                    stuPassword: this.password,
                    stuSex: this.sex,
                    classNo: this.classValue,
                    stuName: this.name,
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        alert("注册成功")
                    } else {
                        alert("注册失败")
                    }     
                });
            },
            getCollege() {
                this.$axios.get(this.$httpUrl + '/college/getCollegeList')
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.collegeData = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getMajor() {
                this.$axios.post(this.$httpUrl + '/major/getMajorListByCollege', {
                    collegeNo: this.collegeValue
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.majorData = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getClass() {
                this.$axios.post(this.$httpUrl + '/class/getClassListByMajor', {
                    majorNo: this.majorValue
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.classData = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            confirm() {
                this.confirm_disabled=true
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.$axios.post(this.$httpUrl + '/login', this.loginForm).
                        then(res=>res.data).then(res=>{
                            console.log(res)
                            if (res.code == 200) {
                                sessionStorage.setItem("CurUser", JSON.stringify(res.data))
                                this.$router.replace('/Index')
                            } else {
                                this.$message({
                                    message: '登录失败',
                                    type: 'error'
                                });
                            }
                        });
                    } else {
                        console.log('校验失败');
                        return false;
                    }
                    this.confirm_disabled=false;
                });
            }
        },
        beforeMount() {
            this.getCollege();
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
        top: 30%;
        left: 50%;
        margin-top: -200px;
        margin-left: -250px;
        width: 450px;
        height: 640px;
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