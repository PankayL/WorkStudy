<template>
    <div>
        <h3  style="margin-left: 16vh; margin-bottom: 2vh;" >信息详情</h3>

        <el-card class="box-card " shadow="hover">
            <div slot="header" class="clearfix ">
                <span style="font-size: 18px;">{{newPostDetails.postTitle}}</span>>
                <span style="font-size: 10px;">浏览次数：{{newPostDetails.postCount}}，评论数：{{postEvaluateListTotal}}，{{'时间：' + newPostDetails.startTime + ' 至 ' + newPostDetails.endTime}}</span>>
                <el-button slot="reference" size="middle" style="float: right; padding: 3px 0" type="text" @click="myClick">应聘</el-button>
            </div>
            
            <div class="text item paragraph_indent">
                {{ newPostDetails.postDetails}}
            </div>
            <br/>
            <div class="text item paragraph_indent">
                {{'类型：' + newPostDetails.postType.typeName}}
            </div>
            <div class="text item paragraph_indent">
                {{'薪资（元/小时）：' + newPostDetails.postMoney}}
            </div>
            <div class="text item paragraph_indent">
                {{'联系人：' + newPostDetails.company.companyPeople}}
            </div>
            <div class="text item paragraph_indent">
                {{'电话：' + newPostDetails.company.companyNo}}
            </div>
        </el-card>
        <el-divider></el-divider>
        <el-descriptions style="margin-left: 16vh; margin-top:3vh;" class="margin-top" title="评论" :column="3" :size="size" border>
        </el-descriptions>

        <el-input type="textarea" :rows="3" placeholder="请输入内容" v-model="textarea" style="margin-bottom:2vh; width: 960px; margin-left: 16vh;">
        </el-input>
        <el-button size="small" style="float: right; margin-right: 22vh; margin-bottom: 10px;" type="primary" @click="publish">发布</el-button>

        <div v-for="o in postEvaluateList" :key="o" class="text item card_center paragraph_indent" >
            <el-card class="box-card1" shadow="hover">
                <div slot="header" class="clearfix headerset">
                    <span style="font-size: 10px;">{{"#" + o.studentNo + "# " + o.time1 + " " + o.time2}}</span>>
                </div>
                <div class="text item paragraph_indent">
                    {{ o.evaluateDetails }}
                </div>
            </el-card>
        </div>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>

        <el-dialog
            style="margin-top: -12vh;"
            title="填写信息"
            :visible.sync="centerDialogVisible"
            width="35%"
            :before-close="handleClose"
            center>
            
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                <el-form-item label="招聘单号" prop="postNo">
                    <el-col :span="20">
                        <el-input v-model="form.postNo" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="学号" prop="studentNo">
                    <el-col :span="20">
                        <el-input v-model="form.studentNo" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="简介" prop="studentNo">
                    <el-col :span="20">
                        <el-input type="textarea" :rows="8" placeholder="请输入内容" v-model="form.resume" style="margin-bottom:2vh;">
                        </el-input>
                    </el-col>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="send">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "myPostDetails",
        data() {
            return {
                postDetails : JSON.parse(sessionStorage.getItem('postDetails')),
                student : JSON.parse(sessionStorage.getItem('CurUser')),
                newPostDetails: {},
                postEvaluateList: [],
                postEvaluateListTotal: 0,
                textarea: '',
                centerDialogVisible:false,
                form: {
                    postNo: 0,
                    studentNo: '',
                    resume: '',
                },
            }
        },
        methods: {
            myClick() {
                this.centerDialogVisible = true;
                this.form.postNo = this.newPostDetails.postNo;
                this.form.studentNo = this.student.no
            },
            send() {
                this.$axios.post(this.$httpUrl + '/studentResume/addStudentResume', {
                    postNo: this.postDetails.postNo,
                    studentNo: this.student.no,
                    resume: this.form.resume,
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.form.resume = '';
                        alert("发送成功！")
                    } else {
                        alert("距离上次发送间隔少于三天！")
                    }
                })
            },
            publish() {
                this.$axios.post(this.$httpUrl + '/postEvaluate/addPostEvaluate', {
                    postNo: this.postDetails.postNo,
                    studentNo: this.student.no,
                    evaluateDetails: this.textarea,
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.textarea = '';
                        this.getPostEvaluateList();
                        alert("评论成功！")
                    } else {
                        alert("评论失败！")
                    }
                })
            },
            getPost() {
                this.$axios.post(this.$httpUrl + '/post/getPostById', {
                    no: this.postDetails.postNo
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.newPostDetails = res.data
                    } else {
                        alert("数据获取失败")
                    }
                })
            },
            getPostEvaluateList() {
                this.$axios.post(this.$httpUrl + '/postEvaluate/getPostEvaluateListByPostNo', {
                    postNo: this.postDetails.postNo
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.postEvaluateList = res.data
                        this.postEvaluateListTotal = res.total
                    } else {
                        alert("数据获取失败")
                    }
                })
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
            this.getPost();
            this.getPostEvaluateList();
        }
    }
</script>

<style scoped>
    .headerset {
        height: 5px;
    }
    .card_center {
        margin-left: 12vh;
    }

    .paragraph_indent {
        text-indent: 20px;
        margin-top: 0px;
    }

    .el-card_header{
        background-color: rgb(112, 205, 50);
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
        margin-left: 16vh;
        width: 960px;
        height: 420px;
    }
    .box-card1 {
        margin-left: 4vh;
        width: 960px;
        height: 100px;
    }

</style>