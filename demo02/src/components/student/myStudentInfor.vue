<template>
    <div>
        <el-descriptions class="margin-top" title="个人信息" :column="3" :size="size" border>
            <template slot="extra">
                <el-button type="primary" size="small" @click="mod">修改信息</el-button>
                <el-button type="success" size="small" @click="drawMod">取钱</el-button>
            </template>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    用户名
                </template>
                {{user.no}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    姓名
                </template>
                {{myself.studentName}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    性别
                </template>
                {{myself.studentSex}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    学院
                </template>
                {{myself.collegeClass.collegeMajor.college.collegeName}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    专业
                </template>
                {{myself.collegeClass.collegeMajor.majorName}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    班级
                </template>
                {{myself.collegeClass.className}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    资产（元）
                </template>
                <el-statistic group-separator="," :precision="2" :value="assets.assetsTotal" style="margin-left:-20vh"></el-statistic>
                <!-- {{assets.assetsTotal}} -->
            </el-descriptions-item>
        </el-descriptions>

        <el-descriptions class="margin-top" style="margin-top: 3vh;" title="账户明细" :column="3" :size="size" border>
            <template slot="extra">
                <el-select v-model="isFlag" clearable placeholder="请选择类别">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
                <el-button type="primary" size="small" style="margin-left: 5px;" @click="getIncomeData">查询</el-button>
            </template>
        </el-descriptions>

        <el-table :data="incomeData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 0px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="student.studentNo" label="学号" width="180" align="center">
            </el-table-column>
            <el-table-column prop="incomeMoney" label="金额（元）" width="180" align="center">
            </el-table-column>
            <el-table-column prop="time1" label="日期" width="180" align="center">
            </el-table-column>
            <el-table-column prop="time2" label="时间" width="180" align="center">
            </el-table-column>
            <el-table-column prop="company.companyNo" label="单位账号" width="180" align="center">
            </el-table-column>
            <el-table-column prop="company.companyName" label="单位名称" width="180" align="center">
            </el-table-column>
            <el-table-column prop="company.companyPeople" label="责任人" width="177" align="center">
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 25px;"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5,8,100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>

        <el-dialog
            style="margin-top: -12vh;"
            title="修改信息"
            :visible.sync="centerDialogVisible"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                <el-form-item label="学号" prop="no">
                    <el-col :span="20">
                        <el-input v-model="form.no" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group style="margin-left: 2vh" v-model="form.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女" style="margin-left: 5vh"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-col :span="20">
                        <el-input type="password" show-password autocomplete="off" v-model="form.password"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="学院">
                    <el-select v-model="collegeValue" clearable placeholder="请选择学院" @blur="getMajor">
                        <el-option
                        v-for="item in collegeData"
                        :key="item.collegeNo"
                        :label="item.collegeName"
                        :value="item.collegeNo">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="专业" prop="brief">
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

            <span slot="footer" class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog
            style="margin-top: 0vh;"
            title="取钱"
            :visible.sync="centerDialogVisible1"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form1" label-width="100px">
                <el-form-item label="余额（元）" prop="no">
                    <el-col :span="20">
                        <el-input v-model="assets.assetsTotal" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="待取（元）" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form1.money"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-col :span="20">
                        <el-input type="password" show-password autocomplete="off" v-model="form1.password"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="cancel1">取 消</el-button>
                <el-button type="primary" @click="draw">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "MyStudentInfor",
        data() {
            return {
                user : JSON.parse(sessionStorage.getItem('CurUser')),
                myself: {},
                assets: {},
                incomeData: [],
                pageNum: 1,
                pageSize: 5,
                total: 0,
                isFlag: '',
                options: [{
                    value: 1,
                    label: '收入'
                }, {
                    value: 0,
                    label: '提现'
                },],
                centerDialogVisible:false,
                form: {
                    name: '',
                    password: '',
                    no: '',
                    sex: '',
                },
                collegeData: [],
                collegeValue: '',
                majorData: [],
                majorValue: '',
                classData: [],
                classValue: '',
                form1: {
                    money: 0.0,
                    password: ''
                },
            }
        },
        methods: {
            cancel1() {
                this.centerDialogVisible1 = false;
                this.form1.password = '';
                this.form1.money = 0.0;
            },
            draw() {
                console.log(this.classValue)
                this.$axios.post(this.$httpUrl + '/studentIncome/drawStudentIncome', {
                    no: this.user.no,
                    password: this.form1.password,
                    money: this.form1.money
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        if (res.total == 10) {
                            alert("取款金额不符合规范！");
                        } else if(res.total == 11) {
                            alert("密码错误！");
                        }else if(res.total == 12) {
                            alert("提现成功！");
                            this.getMelf();
                            this.getIncomeData();
                        }
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            drawMod() {
                this.centerDialogVisible1 = true;
                this.getMelf();
            },
            save() {
                console.log(this.classValue)
                this.$axios.post(this.$httpUrl + '/student/updateStudent', {
                    name: this.form.name,
                    sex: this.form.sex,
                    no: this.form.no,
                    password: this.form.password,
                    classNo: this.classValue
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        alert("更改成功")
                        this.getMelf();
                        this.getIncomeData();
                        if (res.total == 10) {
                            alert("密码已修改，请重新登录！");
                            this.$router.replace('/')
                        }
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            cancel() {
                this.centerDialogVisible = false;
                this.collegeValue = '';
                this.majorValue = '';
                this.classValue = '';
                this.form.password = ''
            },
            mod() {
                this.centerDialogVisible = true;
                this.getCollege();
                this.form.no = this.myself.studentNo;
                this.form.sex = this.myself.studentSex;
                this.form.name = this.myself.studentName;
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
                this.$axios.post(this.$httpUrl + '/collegeMajor/getMajorListByCollege', {
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
                this.$axios.post(this.$httpUrl + '/collegeClass/getClassListByMajor', {
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
            handleClose(done) {
                this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
            },
            getMelf() {
                this.$axios.post(this.$httpUrl + '/student/getStudentById', {
                    no: this.user.no
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        console.log(res.data)
                        this.myself = res.data
                        // alert("添加成功")
                    } else {
                        alert("数据获取失败")
                    }     
                });
                this.$axios.post(this.$httpUrl + '/studentAssets/getStudentAssetsById', {
                    no: this.user.no
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        console.log(res.data)
                        this.assets = res.data
                        // alert("添加成功")
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getIncomeData() {
                this.$axios.post(this.$httpUrl + '/studentIncome/getStudentIncomeListLimit', {
                    no: this.user.no,
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    isFlag: this.isFlag
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        console.log(res.data)
                        this.incomeData = res.data
                        this.total = res.total
                        // alert("添加成功")
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageSize=val
                this.getIncomeData()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.getIncomeData()
            },
        },
        beforeMount() {
            this.getMelf();
            this.getIncomeData();
        }
    }
</script>

<style scoped>

</style>