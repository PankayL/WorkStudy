package com.database.mapper;

import com.database.pojo.StudentResume;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface StudentResumeMapper {
    // 添加应聘单
    int addStudentResume(int postNo, String studentNo, String resume);

    // 获取最晚单个应聘单信息，通过学号、招聘号
    StudentResume getStudentResumeByPostStudentLast(int postNo, String studentNo);
    StudentResume getStudentResumeById(int studentResumeNo);

    // 学生获取自己的申请列表
    List<StudentResume> getStudentResumeAllListLimitByStudentNo(int pageSize, int pageNum, String studentNo, int deleted);
    List<StudentResume> getStudentResumeListLimitByStudentNo(int pageSize, int pageNum, int resumeState, String studentNo);
    // 学生取消申请
    int deleteStudentResume(int resumeNo);
    // 学生离职
    int leaveStudentResume(int resumeNo, int deleted);
    // 更新申请状态
    int updateStudentResumeState(int resumeNo, int resumeState);

    // 单位获取申请列表
    List<StudentResume> getStudentResumeListLimitByCompanyNo(int pageSize, int pageNum, String companyNo, int deleted);
    // 获取总数
    int getStudentResumeTotalByCompanyNo(String companyNo, int deleted);
}
