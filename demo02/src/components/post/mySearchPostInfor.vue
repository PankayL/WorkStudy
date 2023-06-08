
<template>
    <div>
        <div>
            <el-input v-model="comName" placeholder="请输入单位名称" style="width: 200px;" suffix-icon="el-icon-search" 
                @keyup.enter.native="loadPostTotal"></el-input>
            <el-select v-model="postType" clearable placeholder="请选择类别">
                <el-option
                v-for="item in typeDate"
                :key="item.typeNo"
                :label="item.typeName"
                :value="item.typeNo">
                </el-option>
            </el-select>
            <el-select v-model="sort" clearable placeholder="薪资排序">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
            </el-select>
            <el-select v-model="count" clearable placeholder="浏览次数">
                <el-option
                    v-for="item in optionsCount"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
            </el-select>
            <el-button type="primary" size="small" style="margin-left: 5px;" @click="getCompanyLimit">查询</el-button>
            <el-pagination style="margin-top: 25px;"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[4,8,100]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>
  
        <div v-for="o in tableData" :key="o" class="text item card_center paragraph_indent" style="float: left;">
            <el-card class="box-card" shadow="hover">
                <div slot="header" class="clearfix">
                    <span style="font-size: 18px;">{{o.postNo}}, {{o.postTitle}}</span>>
                    <el-button slot="reference" size="middle" style="float: right; padding: 3px 0" type="text" @click="lookOver(o)">详情</el-button>
                   
                </div>
                <div class="text item paragraph_indent">
                    {{'单位： ' + o.company.companyName }}
                </div>
                <div class="text item paragraph_indent">
                    {{'招聘内容： ' + o.postDetails.slice(0,8) + '......'  }}
                </div>
                <div class="text item paragraph_indent">
                    {{'薪资（元/小时）： ' + o.postMoney }}
                </div>
                <div class="text item paragraph_indent">
                    {{'开始时间： ' + o.startTime }}
                </div>
                <div class="text item paragraph_indent">
                    {{'结束时间： ' + o.endTime }}
                </div>
                <div class="text item paragraph_indent">
                    {{'浏览次数： ' + o.postCount }}
                </div>
            </el-card>

            <br/>
        </div>
  
    </div>
  </template>
  
  <script>
  import { onUpdated } from 'vue';
  
    export default {
        name: "MySearchPostInfor",
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
                postType: '',
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
                postState: 1,
                sort: '',
                options: [{
                    value: 1,
                    label: '升序'
                }, {
                    value: 0,
                    label: '降序'
                },],
                count: '',
                optionsCount: [{
                    value: 1,
                    label: '升序'
                }, {
                    value: 0,
                    label: '降序'
                },],
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
            lookOver(o) {
                sessionStorage.setItem("postDetails", JSON.stringify(o))
                this.$router.push("/myPostDetails");
            },
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
                console.log(this.postType)
                this.$axios.post(this.$httpUrl + '/post/getPostListLimit', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    deleted: 0,
                    postState: this.postState,
                    companyName: this.comName,
                    typeNo: this.postType,
                    sort: this.sort,
                    count: this.count
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
                this.$axios.get(this.$httpUrl + '/postType/getPostTypeList')
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
                this.$axios.post(this.$httpUrl + '/post/deletePost', {
                    postNo: id,
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
            this.getCompanyLimit();
        }
    }
  </script>
  
  <style scoped>
  .card_center {
        margin-left: 12vh;
    }

    .paragraph_indent {
        text-indent: 20px;
        margin-top: 10px;
    }

    .el-card_header{
        background-color: yellowgreen;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
  
  </style>