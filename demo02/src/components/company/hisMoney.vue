<template>
    <div>
        <el-table :data="tableData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 0px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="resumeNo" label="申请号" width="170" align="center">
            </el-table-column>
            <el-table-column prop="student.studentName" label="姓名" width="120" align="center">
            </el-table-column>
            <el-table-column prop="post.postNo" label="招聘号" width="115" align="center">
            </el-table-column>
            <el-table-column prop="post.postDetails" label="招聘内容" width="200" align="center">
            </el-table-column>
            <el-table-column prop="time,time1" label="投递时间" width="220" align="center">
                <template slot-scope="scope">
                    {{scope.row.time}} {{scope.row.time1}}
                </template>
            </el-table-column>
            <el-table-column prop="post.postMoney" label="时薪" width="200" align="center">
            </el-table-column>
            <el-table-column prop="resumeState" label="状态" width="80" align="center">
                <template slot-scope="scope">
                    {{scope.row.resumeState === 2 ? '已拒绝' : (scope.row.resumeState === -2 ? '同意离职' : '工作中')}}
                </template>
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="150" align="center">
                <template v-if="scope.row.resumeState === 1" slot-scope="scope">
                    <el-button slot="reference" size="small" type="success" @click="giveSalary(scope.row)">发放</el-button>
                </template>
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
            title="发放工资"
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
                <el-form-item label="姓名" prop="name" >
                    <el-col :span="20">
                        <el-input v-model="form.name" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="时薪" prop="name" >
                    <el-col :span="20">
                        <el-input v-model="form.salary" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="工时" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.time" @keyup.enter.native="getMoney"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="总计" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.money"  :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="give">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "hisMoney",
        data() {
            return {
                user : JSON.parse(sessionStorage.getItem('CurUser')),
                tableData: [],
                pageNum: 1,
                pageSize: 8,
                total: 0,
                centerDialogVisible: false,
                form: {
                    no: '',
                    name: '',
                    salary: '',
                    time: '',
                    money: '',  
                },
            }
        },
        methods: {
            getStudentResumeListLimitByCompanyNo() {
                console.log(this.user)
                this.$axios.post(this.$httpUrl + '/studentResume/getStudentResumeListLimitByCompanyNo', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    deleted: -1,
                    companyNo: this.user.no
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.tableData = res.data
                        this.total = res.total
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            give() {
                this.$axios.post(this.$httpUrl + '/studentIncome/giveStudentIncome', {
                    studentNo: this.form.no,
                    salary: this.form.money
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        alert("发放成功")
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            giveSalary(row) {
                this.centerDialogVisible = true
                this.form.no = row.student.studentNo
                this.form.name = row.student.studentName
                this.form.salary = row.post.postMoney
                this.form.money = this.form.salary * this.form.time
            },
            getMoney() {
                this.form.money = this.form.salary * this.form.time
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageSize=val
                this.getStudentResumeListLimitByCompanyNo()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.getStudentResumeListLimitByCompanyNo()
            },
            filterCompanyState(value, row) {
                return row.companyState === value;
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
            },
        },
        beforeMount() {
            this.getStudentResumeListLimitByCompanyNo();
        },
    }
</script>

<style scoped>

</style>