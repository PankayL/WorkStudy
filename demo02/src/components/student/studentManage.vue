<template>
    <div>
        <el-select v-model="collegeValue" clearable placeholder="请选择学院" @blur="getMajor">
            <el-option
            v-for="item in collegeData"
            :key="item.collegeNo"
            :label="item.collegeName"
            :value="item.collegeNo">
            </el-option>
        </el-select>
        <el-select v-model="majorValue" clearable placeholder="请选择专业" @focus="getMajor" @blur="getClass">
            <el-option
            v-for="item in majorData"
            :key="item.majorNo"
            :label="item.majorName"
            :value="item.majorNo">
            </el-option>
        </el-select>
        <el-select v-model="classValue" clearable placeholder="请选择班级" @focus="getClass">
            <el-option
            v-for="item in classData"
            :key="item.classNo"
            :label="item.className"
            :value="item.classNo">
            </el-option>
        </el-select>
        <el-select v-model="sex" clearable placeholder="请选择性别">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
        <el-button type="primary" size="small" style="margin-left: 5px;" @click="getStudent">查询</el-button>
        <el-button type="success" size="small" style="margin-left: 5px;" @click="addStudent">添加</el-button>

        <el-table :data="studentData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 40px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="studentNo" label="学号" width="150" align="center">
            </el-table-column>
            <el-table-column prop="studentName" label="姓名" width="100" align="center">
            </el-table-column>
            <el-table-column prop="studentSex" label="性别" width="100" align="center">
            </el-table-column>
            <el-table-column prop="collegeClass.collegeMajor.college.collegeName" label="学院" width="200" align="center">
            </el-table-column>
            <el-table-column prop="collegeClass.collegeMajor.majorName" label="专业" width="200" align="center">
            </el-table-column>
            <el-table-column prop="collegeClass.className" label="班级" width="150" align="center">
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
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
            title="添加学生"
            :visible.sync="centerDialogVisible"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                <el-form-item label="学号" prop="no">
                    <el-col :span="20">
                        <el-input v-model="form.no"></el-input>
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
                <el-form-item label="密码" prop="password1">
                    <el-col :span="20">
                        <el-input type="password" show-password autocomplete="off" v-model="form.password1"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="学院">
                    <el-select v-model="collegeValue1" clearable placeholder="请选择学院" @blur="getMajor1">
                        <el-option
                        v-for="item in collegeData"
                        :key="item.collegeNo"
                        :label="item.collegeName"
                        :value="item.collegeNo">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="专业" prop="brief">
                    <el-select v-model="majorValue1" clearable placeholder="请选择专业" @focus="getMajor1" @blur="getClass1">
                        <el-option
                        v-for="item in majorData1"
                        :key="item.majorNo"
                        :label="item.majorName"
                        :value="item.majorNo">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="班级" prop="no">
                    <el-select v-model="classValue1" clearable placeholder="请选择班级" @focus="getClass1">
                        <el-option
                        v-for="item in classData1"
                        :key="item.classNo"
                        :label="item.className"
                        :value="item.classNo">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog
            style="margin-top: -12vh;"
            title="编辑学生"
            :visible.sync="centerDialogVisible1"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form1" label-width="100px">
                <el-form-item label="学号" prop="no">
                    <el-col :span="20">
                        <el-input v-model="form1.no" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form1.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group style="margin-left: 2vh" v-model="form1.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女" style="margin-left: 5vh"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="密码" prop="password1">
                    <el-col :span="20">
                        <el-input type="password" show-password autocomplete="off" v-model="form1.password1"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="学院">
                    <el-select v-model="collegeValue2" clearable placeholder="请选择学院" @blur="getMajor2">
                        <el-option
                        v-for="item in collegeData"
                        :key="item.collegeNo"
                        :label="item.collegeName"
                        :value="item.collegeNo">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="专业" prop="brief">
                    <el-select v-model="majorValue2" clearable placeholder="请选择专业" @focus="getMajor2" @blur="getClass2">
                        <el-option
                        v-for="item in majorData2"
                        :key="item.majorNo"
                        :label="item.majorName"
                        :value="item.majorNo">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="班级" prop="no">
                    <el-select v-model="classValue2" clearable placeholder="请选择班级" @focus="getClass2">
                        <el-option
                        v-for="item in classData2"
                        :key="item.classNo"
                        :label="item.className"
                        :value="item.classNo">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible1 = false">取 消</el-button>
                <el-button type="primary" @click="save1">确 定</el-button>
            </span>
        </el-dialog>
    </div>
    
</template>

<script>
    export default {
        name: "StudentSearch",
        data() {
            return {
                collegeData: [],
                collegeValue: '',
                collegeValue1: '',
                collegeValue2: '',
                majorData: [],
                majorData1: [],
                majorData2: [],
                majorValue: '',
                majorValue1: '',
                majorValue2: '',
                classData: [],
                classData1: [],
                classData2: [],
                classValue: '',
                classValue1: '',
                classValue2: '',
                studentData: [],
                pageNum: 1,
                pageSize: 8,
                total: 0,
                options: [{
                    value: '男',
                    label: '男'
                }, {
                    value: '女',
                    label: '女'
                },],
                sex: '',
                centerDialogVisible:false,
                centerDialogVisible1:false,
                form: {
                    name: '',
                    password1: '',
                    password2: '',
                    no: '',
                    sex: '',
                },
                form1: {
                    name: '',
                    password1: '',
                    password2: '',
                    no: '',
                    sex: '',
                },
                // rules: {
                //     name: [
                //         { required: true, message: '请输入公司名称', trigger: 'blur' },
                //         { min: 6, max: 30, message: '长度在 6 到 30 个字符', trigger: 'blur' }
                //     ],
                //     no: [
                //         { required: true, message: '请输入公司账号', trigger: 'blur' },
                //         { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                //     ],
                //     person: [
                //         { required: true, message: '请输入公司法人', trigger: 'blur' },
                //         { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
                //     ],
                //     address: [
                //         { required: true, message: '请输入公司地址', trigger: 'blur' },
                //         { min: 6, max: 30, message: '长度在 6 到 30 个字符', trigger: 'blur' }
                //     ],
                //     brief: [
                //         { required: true, message: '请输入公司简介', trigger: 'blur' },
                //         { min: 6, max: 100, message: '长度在 6 到 100 个字符', trigger: 'blur' }
                //     ],
                //     password1: [
                //         { required: true, message: '请输入密码', trigger: 'blur' },
                //         { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
                //     ],
                //     password2: [
                //         { required: true, message: '请确认密码', trigger: 'blur' },
                //         { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' },
                //         {checkPassword, trigger: 'blur'}
                //     ],
                // }
            }
        },
        methods: {
            mod(row) {
                this.centerDialogVisible1 = true;
                this.form1.no = row.studentNo;
                this.form1.sex = row.studentSex;
                this.form1.name = row.studentName

            },
            save() {
                console.log(this.classValue)
                this.$axios.post(this.$httpUrl + '/student/addStudent', {
                    name: this.form.name,
                    sex: this.form.sex,
                    no: this.form.no,
                    password: this.form.password1,
                    classNo: this.classValue1
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        alert("添加成功")
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            save1() {
                console.log(this.classValue)
                this.$axios.post(this.$httpUrl + '/student/updateStudent', {
                    name: this.form1.name,
                    sex: this.form1.sex,
                    no: this.form1.no,
                    password: this.form1.password1,
                    classNo: this.classValue2
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        alert("更改成功")
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
            resetForm() {
                this.$refs.form.resetFields();
            },
            addStudent() {
                this.centerDialogVisible=true
                this.$nextTick(()=>{
                    this.resetForm();
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageSize=val
                this.getStudent()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.getStudent()
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
            getMajor1() {
                this.$axios.post(this.$httpUrl + '/collegeMajor/getMajorListByCollege', {
                    collegeNo: this.collegeValue1
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.majorData1 = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getMajor2() {
                this.$axios.post(this.$httpUrl + '/collegeMajor/getMajorListByCollege', {
                    collegeNo: this.collegeValue2
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.majorData2 = res.data
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
            getClass1() {
                this.$axios.post(this.$httpUrl + '/collegeClass/getClassListByMajor', {
                    majorNo: this.majorValue1
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.classData1 = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getClass2() {
                this.$axios.post(this.$httpUrl + '/collegeClass/getClassListByMajor', {
                    majorNo: this.majorValue2
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.classData2 = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getStudent() {
                console.log(this.collegeValue)
                console.log(this.majorValue)
                console.log(this.classValue)
                this.$axios.post(this.$httpUrl + '/student/getStudentListLimit', {
                    classNo: this.classValue,
                    majorNo: this.majorValue,
                    collegeNo: this.collegeValue,
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    sex: this.sex,
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.studentData = res.data
                        this.total = res.total
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
        },
        beforeMount() {
            this.getCollege();
        }
    }
</script>

<style scoped>

</style>