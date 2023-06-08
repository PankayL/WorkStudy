package com.database.mapper;

import com.database.pojo.Student;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface StudentMapper {
    // 获取学生数量
    int getStudentTotal(int type, int collegeNo, int majorNo, int classNo, String sex, int deleted);

    // 获取学生通过学号
    Student getStudentById(String studentNo);
    // 检查学号是否存在及删除
    int checkStudentNo(String studentNo);

    // 分页查询
    List<Student> getStudentLimit(int pageSize, int pageNum, String sex, int deleted);
    List<Student> getStudentLimitByClass(int pageSize, int pageNum, int classNo, String sex, int deleted);
    List<Student> getStudentLimitByMajor(int pageSize, int pageNum, int majorNo, String sex, int deleted);
    List<Student> getStudentLimitByCollege(int pageSize, int pageNum, int collegeNo, String sex, int deleted);

    // 添加学生
    int addStudent(HashMap hash);

    // 更改信息
    int updateStudent(HashMap hash);
}
