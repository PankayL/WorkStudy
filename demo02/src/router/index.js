// import Vue from 'vue'
// import Router from 'vue-router'

// import Login from '../components/Login'
// import Main from '../components/Main'
// import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'

// Vue.use(Router)
// Vue.use(ElementUI)

import VueRouter, { RouterLink } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'login',
    component:()=>import('../components/Login'),
  },
  {
    path:'/Register',
    name:'register',
    component:()=>import('../components/student/stuRegister'),
  },
  {
    path: '/Index',
    name: 'index',
    component:()=>import('../components/index'),
    children: [
      {
        path:'/CompanyManage',
        name:'companyManage',
        meta: {
          title: '商户管理'
        },
        component:()=>import('../components/company/companyManage'),
      },
      {
        path:'/StudentInfor',
        name:'studentInfor',
        meta: {
          title: '学生信息'
        },
        component:()=>import('../components/student/studentManage'),
      },
      {
        path:'/PostInfor',
        name:'postInfor',
        meta: {
          title: '岗位信息'
        },
        component:()=>import('../components/post/postManage'),
      },
      {
        path:'/PostApply',
        name:'postApply',
        meta: {
          title: '岗位信息'
        },
        component:()=>import('../components/post/postApply'),
      },
      {
        path:'/InformationManage',
        name:'informationManage',
        meta: {
          title: '我的信息'
        },
        component:()=>import('../components/Information/informationManage'),
      },
      // 学生登录
      {
        path:'/myStudentInfor',
        name:'myStudentInfor',
        meta: {
          title: '学生我的信息'
        },
        component:()=>import('../components/student/myStudentInfor'),
      },
      {
        path:'/mySearchPostInfor',
        name:'mySearchPostInfor',
        meta: {
          title: '学生岗位信息'
        },
        component:()=>import('../components/post/mySearchPostInfor'),
      },
      {
        path:'/mySend',
        name:'mySend',
        meta: {
          title: '学生我的投递'
        },
        component:()=>import('../components/post/mySend'),
      },
      {
        path:'/myPost',
        name:'myPost',
        meta: {
          title: '学生我的岗位'
        },
        component:()=>import('../components/post/myPost'),
      },
      {
        path:'/myInfor',
        name:'myInfor',
        meta: {
          title: '学生我的消息'
        },
        component:()=>import('../components/Information/myInfor'),
      },
      {
        path:'/mySet',
        name:'mySet',
        meta: {
          title: '学生设置'
        },
        component:()=>import('../components/Information/mySet'),
      },
      {
        path:'/myPostDetails',
        name:'myPostDetails',
        meta: {
          title: '学生岗位详情'
        },
        component:()=>import('../components/post/myPostDetails'),
      },
      // 单位登录
      {
        path:'/hisCompanyInfor',
        name:'hisCompanyInfor',
        meta: {
          title: '单位单位信息'
        },
        component:()=>import('../components/company/hisCompanyInfor'),
      },
      {
        path:'/hisPostInfor',
        name:'hisPostInfor',
        meta: {
          title: '单位岗位信息'
        },
        component:()=>import('../components/company/hisPostInfor'),
      },
      {
        path:'/hisPostApply',
        name:'hisPostApply',
        meta: {
          title: '单位申请岗位'
        },
        component:()=>import('../components/company/hisPostApply'),
      },
      {
        path:'/hisStudentPostApply',
        name:'hisStudentPostApply',
        meta: {
          title: '单位岗位申请'
        },
        component:()=>import('../components/company/hisStudentPostApply'),
      },
      {
        path:'/hisLeaveApply',
        name:'hisLeaveApply',
        meta: {
          title: '单位离职申请'
        },
        component:()=>import('../components/company/hisLeaveApply'),
      },
      {
        path:'/hisMoney',
        name:'hisMoney',
        meta: {
          title: '单位工资发放'
        },
        component:()=>import('../components/company/hisMoney'),
      },
      {
        path:'/hisInfor',
        name:'hisInfor',
        meta: {
          title: '单位我的消息'
        },
        component:()=>import('../components/company/hisInfor'),
      },
      {
        path:'/hisSet',
        name:'hisSet',
        meta: {
          title: '单位设置'
        },
        component:()=>import('../components/company/hisSet'),
      },
    ]
  },
]

const router = new VueRouter(
  {
    mode:'history',
    routes
  }
)

export default router;