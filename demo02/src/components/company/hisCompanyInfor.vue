<template>
    <div>
        <el-descriptions class="margin-top" title="单位信息" :column="3" :size="size" border>
            <template slot="extra">
                <el-button type="primary" size="small" @click="mod">修改信息</el-button>
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
                    单位名
                </template>
                {{company.companyName}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    单位类型
                </template>
                {{company.companyType.typeName}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    责任人
                </template>
                {{company.companyPeople}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    招聘数量
                </template>
                {{postTotal}}
            </el-descriptions-item>
        </el-descriptions>

        
    </div>
</template>

<script>
    export default {
        name: "hisCompanyInfor",
        data() {
            return {
                company: {},
                user : JSON.parse(sessionStorage.getItem('CurUser')),
                postTotal: 0,
            }
        },
        methods: {
            getCompanyInfor() {
                this.$axios.post(this.$httpUrl + '/company/getCompanyInfor', {
                    companyNo: this.user.no
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.company = res.data
                        this.getPostTotal();
                    } else {
                        alert("数据获取失败")
                    }     
                });
            },
            getPostTotal() {
                this.$axios.post(this.$httpUrl + '/post/getPostTotalByCompanyNo', {
                    companyNo: this.user.no
                })
                .then(res=>res.data).then(res=>{
                    console.log(res)
                    if (res.code == 200) {
                        this.postTotal = res.data
                    } else {
                        alert("数据获取失败")
                    }     
                });
            }
        },
        beforeMount() {
            this.getCompanyInfor();
        }
    }
</script>

<style scoped>

</style>