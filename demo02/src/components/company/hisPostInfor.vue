<template>
    <div>
        <el-table :data="tableData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 0px;" 
        header-align:center max-height="600p"
        :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
        border=""
        >
            <el-table-column prop="postNo" label="ID" width="110" align="center">
            </el-table-column>
            <el-table-column prop="company.companyName" label="单位名称" width="100" align="center">
            </el-table-column>
            <el-table-column prop="company.companyPeople" label="单位责任人" width="100" align="center">
            </el-table-column>
            <el-table-column prop="postTitle" label="标题" width="100" align="center">
            </el-table-column>
            <el-table-column prop="postDetails" label="内容" width="170" align="center">
            </el-table-column>
            <el-table-column prop="postType.typeName" label="类别" width="80" align="center">
            </el-table-column>
            <el-table-column prop="postMoney" label="薪资" width="65" align="center">
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="125" align="center">
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间" width="125" align="center">
            </el-table-column>
            <el-table-column prop="postCount" label="浏览次数" width="90" align="center">
            </el-table-column>
            <el-table-column prop="postState" label="状态" width="90" align="center">
                <template slot-scope="scope">
                    {{scope.row.postState === 1 ? '进行中' : (scope.row.postState === 2 ? '已结束' : '已删除')}}
                </template>
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="100" align="center">
                <template slot-scope="scope">
                    <el-popconfirm v-if="scope.row.postState === 1" title="确定结束该招聘？" @confirm="end(scope.row.postNo)">
                        <el-button slot="reference" size="small" type="warning">结束</el-button>
                    </el-popconfirm>
                    <el-popconfirm v-if="scope.row.postState === 2" title="确定删除该招聘？" @confirm="del(scope.row.postNo)">
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
    </div>
</template>

<script>
    export default {
        name: "hisPostInfor",
        data() {
            return {
                user : JSON.parse(sessionStorage.getItem('CurUser')),
                tableData: [],
                pageNum: 1,
                pageSize: 8,
                postState: '',
                total: 0,
            }
        },
        methods: {
            getPostLimit() {
                console.log(this.postType)
                this.$axios.post(this.$httpUrl + '/post/getPostListLimitByCompanyNo', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    deleted: 0,
                    postState: this.postState,
                    companyNo: this.user.no,
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
            end(postNo) {
                console.log(this.postType)
                this.$axios.post(this.$httpUrl + '/post/updateCompanyPostStateByPostNo', {
                    postNo: postNo,
                    postState: 2,
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.getPostLimit();
                        alert("操作成功")
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            del(postNo) {
                console.log(this.postType)
                this.$axios.post(this.$httpUrl + '/post/updateCompanyPostStateByPostNo', {
                    postNo: postNo,
                    postState: 3,
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.getPostLimit();
                        alert("操作成功")
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageSize=val
                this.getPostLimit()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.getPostLimit()
            },
        },
        beforeMount() {
            this.getPostLimit();
        }
    }
</script>

<style scoped>

</style>