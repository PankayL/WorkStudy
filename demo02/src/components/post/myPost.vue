<template>
    <div>
        <el-table :data="tableData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 0px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="post.company.companyName" label="单位名称" width="221" align="center">
            </el-table-column>
            <el-table-column prop="post.company.companyPeople" label="负责人" width="221" align="center">
            </el-table-column>
            <el-table-column prop="post.company.companyNo" label="联系方式" width="221" align="center">
            </el-table-column>
            <el-table-column prop="post.postMoney" label="薪资（元/小时）" width="221" align="center">
            </el-table-column>
            <el-table-column prop="deleted" label="状态" width="221" align="center">
                <template slot-scope="scope">
                    {{scope.row.deleted === 0 ? '任职中' : (scope.row.deleted === -1 ? '申请离职' : '已离职')}}
                </template>
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="150" align="center">
                <template slot-scope="scope">
                        <el-popconfirm v-if="scope.row.deleted === 0" title="确定申请离职？" @confirm="leave(scope.row.resumeNo)">
                            <el-button slot="reference" size="small" type="danger">申请离职</el-button>
                        </el-popconfirm>
                        <el-popconfirm v-if="scope.row.deleted === -1" title="确定取消申请？" @confirm="cancel(scope.row.resumeNo)">
                            <el-button slot="reference" size="small" type="primary">取消申请</el-button>
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
  
    </div>
  </template>
  
  <script>
  import { onUpdated } from 'vue';
  
    export default {
        name: "myPost",
        data() {
            return {
                user : JSON.parse(sessionStorage.getItem('CurUser')),
                tableData: [],
                pageNum: 1,
                pageSize: 8,
                total: 0,
            }
        },
        methods: {
            getStudentResumeListLimitByStudentNo() {
                console.log(this.user)
                this.$axios.post(this.$httpUrl + '/studentResume/getStudentResumeByStudentNo', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    resumeState: 1,
                    studentNo: this.user.no
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
            leave(resumeNo) {
                this.$axios.post(this.$httpUrl + '/studentResume/leaveStudentResume', {
                    resumeNo: resumeNo,
                    deleted: -1
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.getStudentResumeListLimitByStudentNo()
                        alert("申请成功")
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            cancel(resumeNo) {
                this.$axios.post(this.$httpUrl + '/studentResume/leaveStudentResume', {
                    resumeNo: resumeNo,
                    deleted: 0
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.getStudentResumeListLimitByStudentNo()
                        alert("取消成功")
                    } else {
                        alert("数据获取失败")
                    }
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
        },
        beforeMount() {
            this.getStudentResumeListLimitByStudentNo();
        }
    }
  </script>
  
  <style scoped>
  
  </style>