<template>
    <div>
        <h1 style="margin-bottom: 10px;">申请岗位</h1>
        标题：
        <el-input v-model="title" placeholder="请输入标题" style="width: 450px; margin-right:30px;"></el-input>
        类别：
        <el-select v-model="postType" clearable placeholder="请选择类别" style="margin-right:30px;">
            <el-option
            v-for="item in typeDate"
            :key="item.typeNo"
            :label="item.typeName"
            :value="item.typeNo">
            </el-option>
        </el-select>
        薪资：
        <el-input v-model="salary" placeholder="请输入薪资" style="width: 150px; margin-right:30px;"></el-input>
        <br><br>
        <template>
            <div class="block">
                <span class="demonstration">开始时间：</span>
                <el-date-picker
                v-model="startTime"
                type="date"
                placeholder="选择日期">
                </el-date-picker>

                <span style="margin-left:15px;" class="demonstration">结束时间：</span>
                <el-date-picker
                v-model="endTime"
                align="right"
                type="date"
                placeholder="选择日期">
                </el-date-picker>
            </div>
        </template>
        <br>
        <span class="demonstration">详细内容：</span><br>
        <el-input type="textarea" :rows="3" placeholder="请输入内容" v-model="textarea" style="width: 580px; margin-top: 10px;">
        </el-input>
        
        <el-button size="small" style="float: right; margin-right: 80vh; margin-top: 50px;" type="primary" @click="publish">发布</el-button>
        <el-divider content-position="right"></el-divider>

        <h1 style="margin-bottom: 10px;">我的申请</h1>

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
            <el-table-column prop="postDetails" label="内容" width="250" align="center">
            </el-table-column>
            <el-table-column prop="postType.typeName" label="类别" width="80" align="center">
            </el-table-column>
            <el-table-column prop="postMoney" label="薪资" width="65" align="center">
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="125" align="center">
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间" width="125" align="center">
            </el-table-column>
            <el-table-column prop="postState" label="状态" width="101" align="center">
                <template slot-scope="scope">
                    {{scope.row.deleted === 1 ? '已取消' : (scope.row.postState === 0 ? '审核中' : (scope.row.postState === -1 ? '审核未通过' : '已通过'))}}
                </template>
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="100" align="center">
                <template v-if="scope.row.deleted === 0" slot-scope="scope">
                    <el-popconfirm v-if="scope.row.postState === 0" title="确定取消该申请？" @confirm="cancel(scope.row.postNo)">
                        <el-button slot="reference" size="small" type="warning">取消</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 25px;"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[3,5,8,100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
    
</template>

<script>
    export default {
        name: "hisPostApply",
        data() {
            return {
                user : JSON.parse(sessionStorage.getItem('CurUser')),
                tableData: [],
                pageNum: 1,
                pageSize: 3,
                postState: '',
                total: 0,
                title: '',
                typeDate: [],
                postType: '',
                startTime: '',
                endTime: '',
                salary: 0,
                textarea: '',
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                        picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24);
                        picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', date);
                        }
                    }]
                },
            }
        },
        methods: {
            publish() {
                this.$axios.post(this.$httpUrl + '/post/addPost', {
                    companyNo: this.user.no,
                    postType: this.postType,
                    postTitle: this.title,
                    postDetails: this.textarea,
                    startTime: this.startTime,
                    endTime: this.endTime,
                    postMoney: this.salary,
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.getApply()
                        alert("申请成功")
                        this.empty() 
                    } else {
                        alert("申请失败")
                    }     
                })
            },
            empty() {
                this.postType='';
                this.title='';
                this.textarea='';
                this.startTime='';
                this.endTime='';
                this.salary='';
            },
            getType() {
                this.$axios.get(this.$httpUrl + '/postType/getPostTypeList')
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.typeDate = res.data
                        this.total = res.total
                    } else {
                        alert("数据获取失败")
                    }     
                })
            },
            getApply() {
                this.$axios.post(this.$httpUrl + '/post/getPostListLimitByCompanyNoDesc', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    companyNo: this.user.no,
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.tableData = res.data
                        this.total = res.total
                    } else {
                        alert("数据获取失败")
                    }     
                })
            },
            cancel(postNo) {
                this.$axios.post(this.$httpUrl + '/post/deletePost', {
                    postNo: postNo,
                    deleted: 1
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.getApply()
                        alert("取消成功")
                    } else {
                        alert("数据获取失败")
                    }     
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageSize=val
                this.getApply()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.getApply()
            },
        },
        beforeMount() {
            this.getType();
            this.getApply();
        }
    }
</script>

<style scoped>

</style>