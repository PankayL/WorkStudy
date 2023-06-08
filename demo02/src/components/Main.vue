<template>
  <div>
      <div>
          <el-input v-model="comName" placeholder="请输入名称" style="width: 200px;" suffix-icon="el-icon-search" 
              @keyup.enter.native="loadPostTotal"></el-input>
          <el-button type="primary" size="small" style="margin-left: 5px;" @click="loadPostTotal">查询</el-button>
          <el-button type="warning" size="small" style="margin-left: 5px;" @click="resetParam">重置</el-button>
          <el-button type="success" size="small" style="margin-left: 5px;" @click="add">新增</el-button>
          <el-button type="info" size="small" style="margin-left: 5px;" @click="finDeleted">已删除</el-button>
      </div>

      <el-table :data="tableData" style="margin-left: 0vh; overflow: auto; margin-top: 10px; margin-left: 40px;" 
      header-align:center max-height="600p"
      :header-cell-style="{background: '#f3f6ff', color: '#555555'}"
      border=""
      >
          <el-table-column prop="companyNo" label="ID" width="150" align="center">
          </el-table-column>
          <el-table-column prop="companyName" label="名称" width="100" align="center">
          </el-table-column>
          <el-table-column prop="type" label="类型" width="100" align="center">
          </el-table-column>
          <el-table-column prop="briefIntro" label="简介" width="220" align="center">
          </el-table-column>
          <el-table-column prop="legalPerson" label="法人" width="100" align="center">
          </el-table-column>
          <el-table-column prop="address" label="地址" width="200" align="center">
          </el-table-column>
          <el-table-column prop="companyState" label="状态" width="100" align="center"
          :filters="[{ text: '开业', value: '开业' }, { text: '未开业', value: '未开业' }]"
          :filter-method="filterCompanyState"> 
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
          title="操作"
          :visible.sync="centerDialogVisible"
          width="35%"
          :before-close="handleClose"
          center>
          
          <el-form ref="form" :rules="rules" :model="form" label-width="100px">
              <el-form-item label="公司名称" prop="name">
                  <el-col :span="20">
                      <el-input v-model="form.name"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="密    码" prop="password1">
                  <el-col :span="20">
                      <el-input v-model="form.password1"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="确认密码" prop="password2">
                  <el-col :span="20">
                      <el-input v-model="form.password2"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="状    态">
                  <el-radio-group v-model="form.state">
                  <el-radio label="开业"></el-radio>
                  <el-radio label="未开业"></el-radio>
                  </el-radio-group>
              </el-form-item>
              <el-form-item label="简    介" prop="brief">
                  <el-col :span="20">
                      <el-input type="textarea" v-model="form.brief"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="公司账号" prop="no">
                  <el-col :span="20">
                      <el-input v-model="form.no"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="公司法人" prop="person">
                  <el-col :span="20">
                      <el-input v-model="form.person"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="地    址" prop="address">
                  <el-col :span="20">
                      <el-input v-model="form.address"></el-input>
                  </el-col>
              </el-form-item>
              <el-form-item label="类型">
                  <el-radio-group>
                  <el-radio label="科技类"></el-radio>
                  <el-radio label="化工类"></el-radio>
                  <el-radio label="互联网"></el-radio>
                  </el-radio-group>
              </el-form-item>
          </el-form>

          <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
          </span>
      </el-dialog>


  </div>
</template>

<script>
import { onUpdated } from 'vue';

  export default {
      name: "Main",
      data() {
          let checkPassword = (rule, value, callback) => {
              if (value == this.password1) {
                  callback();
              } else {
                  callback(new Error("两次密码不一致"));
              }
          };
          return {
              tableData: [],
              pageNum: 1,
              pageSize: 8,
              total: 0,
              comName: '',
              isFlagLike:0,
              isFlagAddOrUpadte: 0,
              centerDialogVisible:false,
              form: {
                  name: '',
                  password1: '',
                  password2: '',
                  no: '',
                  state: '开业',
                  brief: '',
                  person: '',
                  address: '',
                  type: '科技类'
              },
              rules: {
                  name: [
                      { required: true, message: '请输入公司名称', trigger: 'blur' },
                      { min: 6, max: 30, message: '长度在 6 到 30 个字符', trigger: 'blur' }
                  ],
                  no: [
                      { required: true, message: '请输入公司账号', trigger: 'blur' },
                      { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                  ],
                  person: [
                      { required: true, message: '请输入公司法人', trigger: 'blur' },
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
          resetForm() {
              this.$refs.form.resetFields();
          },
          mod(row) {
              // this.isFlagAddOrUpadte=1
              // this.form.name = row.companyName 
              // this.form.password1 = '' 
              // this.form.password2 = '' 
              // this.form.no = row.no 
              // this.form.state = row.companyState 
              // this.form.brief = row.briefIntro 
              // this.form.person = row.legalPerson 
              // this.form.address = row.address 
              // this.form.type = row.type 
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
              this.$axios.get(this.$httpUrl + '/company/delete?id='+id).
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
              if (this.isFlagAddOrUpadte = 0) {
                  this.$refs.form.validate((valid) => {
                      if (valid) {
                          this.$axios.post(this.$httpUrl + '/company/save', this.form).
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
                                      message: '添加失败',
                                      type: 'error'
                                  });
                              }
                          })
                      } else {
                          console.log('error submit!!');
                          return false;
                      }
                  });
              } else {

              }
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
              this.loadPost()
          },
          handleCurrentChange(val) {
              console.log(`当前页: ${val}`);
              this.pageNum=val
              this.loadPost()
          },
          resetParam() {
              this.pageSize = 8
              this.isFlagLike = 0
              this.loadGet()
              this.loadPost()
              this.comName=''
          },
          loadGet() {
              this.$axios.get(this.$httpUrl + '/company/list').then(res=>res.data).then(res=>{
                  console.log(res)
                  if (res.code == 200) {
                      this.tableData = res.data
                      this.total = res.total
                  } else {
                      alert("数据获取失败")
                  }
                  
              })
          },
          loadPost() {
              if (this.isFlagLike == 0) {
                  this.$axios.post(this.$httpUrl + '/company/listLimit', {
                      isFlagLike: this.isFlagLike,
                      pageSize: this.pageSize,
                      pageNum: this.pageNum,
                      comName: this.comName
                  }).then(res=>res.data).then(res=>{
                      console.log(res)
                      if (res.code == 200) {
                          this.tableData = res.data
                      } else {
                          alert("数据获取失败")
                      }
                  })
              } else {
                  this.$axios.post(this.$httpUrl + '/company/listLimit', {
                      isFlagLike: this.isFlagLike,
                      pageSize: this.pageSize,
                      pageNum: this.pageNum,
                      comName: this.comName
                  }).then(res=>res.data).then(res=>{
                      console.log(res)
                      if (res.code == 200) {
                          this.tableData = res.data
                      } else {
                          alert("数据获取失败")
                      }
                  })
              }
          },
          loadPostTotal() {
              this.isFlagLike = 1
              this.$axios.post(this.$httpUrl + '/company/listLimit', {
                  isFlagLike: this.isFlagLike,
                  pageSize: -1,
                  pageNum: -1,
                  comName: this.comName
              }).then(res=>res.data).then(res=>{
                  console.log(res)
                  if (res.code == 200) {
                      this.total = res.total
                  } else {
                      alert("数据获取失败")
                  }     
              })
              this.$axios.post(this.$httpUrl + '/company/listLimit', {
                      isFlagLike: this.isFlagLike,
                      pageSize: this.pageSize,
                      pageNum: this.pageNum,
                      comName: this.comName
                  }).then(res=>res.data).then(res=>{
                      console.log(res)
                      if (res.code == 200) {
                          this.tableData = res.data
                      } else {
                          alert("数据获取失败")
                      }
                  })
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
          this.loadGet();
          this.loadPost();
      }
  }
</script>

<style scoped>

</style>