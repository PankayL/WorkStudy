<template>
    <div>
        <el-table :data="tableData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 0px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="resumeNo" label="申请号" width="100" align="center">
            </el-table-column>
            <el-table-column prop="student.studentName" label="姓名" width="80" align="center">
            </el-table-column>
            <el-table-column prop="resumeDetails" label="信息" width="170" align="center">
            </el-table-column>
            <el-table-column prop="post.postNo" label="招聘号" width="100" align="center">
            </el-table-column>
            <el-table-column prop="post.postDetails" label="招聘内容" width="171" align="center">
            </el-table-column>
            <el-table-column prop="post.company.companyName" label="单位名称" width="100" align="center">
            </el-table-column>
            <el-table-column prop="post.company.companyPeople" label="负责人" width="80" align="center">
            </el-table-column>
            <el-table-column prop="post.company.companyNo" label="联系方式" width="100" align="center">
            </el-table-column>
            <el-table-column prop="time,time1" label="投递时间" width="176" align="center">
                <template slot-scope="scope">
                    {{scope.row.time}} {{scope.row.time1}}
                </template>
            </el-table-column>
            <el-table-column prop="resumeState" label="状态" width="80" align="center">
                <template slot-scope="scope">
                    {{scope.row.resumeState === 0 ? '未审核' : (scope.row.resumeState === 1 ? '审核通过' : '未通过')}}
                </template>
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="100" align="center">
                <template v-if="scope.row.resumeState === 0" slot-scope="scope">
                    <el-popconfirm title="确定取消该申请？" @confirm="cancel(scope.row.resumeNo)">
                        <el-button slot="reference" size="small" type="danger">取消</el-button>
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
        name: "mySend",
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
                this.$axios.post(this.$httpUrl + '/studentResume/getStudentResumeListByStudentNo', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    deleted: 0,
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
            cancel(resumeNo) {
                this.$axios.post(this.$httpUrl + '/studentResume/deleteStudentResume', {
                    resumeNo: resumeNo
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