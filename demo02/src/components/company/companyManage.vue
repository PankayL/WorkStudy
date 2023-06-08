<template>
    <div>
        <div>
            <el-input v-model="comName" placeholder="请输入单位名称" style="width: 200px;" suffix-icon="el-icon-search" 
                @keyup.enter.native="loadPostTotal"></el-input>
                <el-input v-model="peopleName" placeholder="请输入责任人名称" style="width: 200px;" suffix-icon="el-icon-search" 
                @keyup.enter.native="loadPostTotal"></el-input>
            <el-select v-model="companyType" clearable placeholder="请选择类别">
                <el-option
                v-for="item in typeDate"
                :key="item.typeNo"
                :label="item.typeName"
                :value="item.typeNo">
                </el-option>
            </el-select>
            <el-button type="primary" size="small" style="margin-left: 5px;" @click="getCompanyLimit">查询</el-button>
            <el-button type="warning" size="small" style="margin-left: 5px;" @click="resetParam">重置</el-button>
            <el-button type="success" size="small" style="margin-left: 5px;" @click="add">新增</el-button>
            <el-button type="info" size="small" style="margin-left: 5px;" @click="getCompanyLimit1">已删除</el-button>
        </div>
  
        <el-table :data="tableData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 40px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="companyNo" label="ID" width="250" align="center">
            </el-table-column>
            <el-table-column prop="companyName" label="名称" width="250" align="center">
            </el-table-column>
            <el-table-column prop="companyType.typeName" label="类型" width="250" align="center">
            </el-table-column>
            <el-table-column prop="companyPeople" label="责任人" width="250" align="center">
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
                    <el-popconfirm title="确定删除该用户？" @confirm="del(scope.row.companyNo)">
                        <el-button slot="reference" size="small" type="danger">删除</el-button>
                    </el-popconfirm>
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
            title="添加单位"
            :visible.sync="centerDialogVisible"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                <el-form-item label="单位名称" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="单位账号" prop="no">
                    <el-col :span="20">
                        <el-input v-model="form.no"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="密    码" prop="password1">
                    <el-col :span="20">
                        <el-input v-model="form.password1"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="责任人" prop="person">
                    <el-col :span="20">
                        <el-input v-model="form.person"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="类型">
                    <el-select v-model="companyType1" clearable placeholder="请选择类别">
                        <el-option
                        v-for="item in typeDate"
                        :key="item.typeNo"
                        :label="item.typeName"
                        :value="item.typeNo">
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
            title="编辑单位"
            :visible.sync="centerDialogVisible1"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form1" label-width="100px">
                <el-form-item label="单位名称" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form1.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="单位账号" prop="no">
                    <el-col :span="20">
                        <el-input v-model="form1.no" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="密    码" prop="password1">
                    <el-col :span="20">
                        <el-input v-model="form1.password1"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="责任人" prop="person">
                    <el-col :span="20">
                        <el-input v-model="form1.person"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="类型">
                    <el-select v-model="companyType2" clearable placeholder="请选择类别">
                        <el-option
                        v-for="item in typeDate"
                        :key="item.typeNo"
                        :label="item.typeName"
                        :value="item.typeNo">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
  
            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible1 = false">取 消</el-button>
                <el-button type="primary" @click="update">确 定</el-button>
            </span>
        </el-dialog>
  
  
    </div>
  </template>
  
  <script>
  import { onUpdated } from 'vue';
  
    export default {
        name: "companyManage",
        data() {
            let checkPassword = (rule, value, callback) => {
                if (value == this.password1) {
                    callback();
                } else {
                    callback(new Error("两次密码不一致"));
                }
            };
            return {
                typeDate: [],
                companyType: '',
                companyType1: '',
                companyType2: '',
                tableData: [],
                pageNum: 1,
                pageSize: 8,
                total: 0,
                comName: '',
                peopleName:'',
                isFlagLike:0,
                isFlagAddOrUpadte: 0,
                centerDialogVisible:false,
                centerDialogVisible1:false,
                form: {
                    name: '',
                    password1: '',
                    password2: '',
                    no: '',
                    person: ''
                },
                form1: {
                    name: '',
                    password1: '',
                    password2: '',
                    no: '',
                    person: ''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入单位名称', trigger: 'blur' },
                        { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
                    ],
                    no: [
                        { required: true, message: '请输入单位账号', trigger: 'blur' },
                        { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                    ],
                    person: [
                        { required: true, message: '请输入公司责任人', trigger: 'blur' },
                        { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
                    ],
                    address: [
                        { required: true, message: '请输入公司地址', trigger: 'blur' },
                        { min: 6, max: 30, message: '长度在 6 到 30 个字符', trigger: 'blur' }
                    ],
                    brief: [
                        { required: true, message: '请输入公司简介', trigger: 'blur' },
                        { min: 6, max: 100, message: '长度在 6 到 100 个字符', trigger: 'blur' }
                    ],
                    password1: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
                    ],
                    password2: [
                        { required: true, message: '请确认密码', trigger: 'blur' },
                        { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' },
                        {checkPassword, trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            update() {
                this.$axios.post(this.$httpUrl + '/company/updateCompany', {
                    companyNo: this.form1.no,
                    companyName: this.form1.name,
                    peopleName: this.form1.person,
                    companyPassword: this.form1.password1,
                    type: this.companyType2
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        alert("修改成功")
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            getCompanyLimit() {
                console.log(this.companyType)
                this.$axios.post(this.$httpUrl + '/company/getCompanyListLimit', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    deleted: 0,
                    companyName: this.comName,
                    peopleName: this.peopleName,
                    type: this.companyType
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
            getCompanyLimit1() {
                console.log(this.companyType)
                this.$axios.post(this.$httpUrl + '/company/getCompanyListLimit', {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        deleted: 1,
                        companyName: this.comName,
                        peopleName: this.peopleName,
                        type: this.companyType
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
            getType() {
                this.$axios.get(this.$httpUrl + '/type/getTypeList')
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.typeDate = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                })
            },
            resetForm() {
                this.$refs.form.resetFields();
            },
            mod(row) {
                this.centerDialogVisible1=true
                this.form1.name = row.companyName
                this.form1.no = row.companyNo
                this.form1.person = row.companyPeople
            },
            finDeleted() {
                this.$axios.get(this.$httpUrl + '/company/getDeleted')
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.total = res.total
                        this.tableData = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                })
            },
            del(id) {
                console.log(id)
                this.$axios.post(this.$httpUrl + '/company/deleteCompany', {
                    companyNo: id,
                }). 
                then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.loadPostTotal()
                    } else {
                        this.$message({
                            message: '删除失败',
                            type: 'error'
                        });
                    }
                })
            },
            save() {
                this.$axios.post(this.$httpUrl + '/company/addCompany', {
                    companyName: this.form.name,
                    companyNo: this.form.no,
                    companyPassword: this.form.password1,
                    companyPeople: this.form.person,
                    companyType: this.companyType1
                }).
                then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            message: '添加成功',
                            type: 'success'
                        });
                        this.centerDialogVisible=false
                        this.loadPostTotal()
                    } else {
                        this.$message({
                            message: '账号重复，添加失败',
                            type: 'error'
                        });
                    }
                })
            },
            add() {
                this.isFlagAddOrUpadte=0
                this.centerDialogVisible=true
                this.$nextTick(()=>{
                    this.resetForm();
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageSize=val
                this.getCompanyLimit()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.getCompanyLimit()
            },
            resetParam() {
                this.pageSize = 8
                this.isFlagLike = 0
                this.comName=''
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
            this.getType();
        }
    }
  </script>
  
  <style scoped>
  
  </style>