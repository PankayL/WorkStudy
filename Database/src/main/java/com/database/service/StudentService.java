package com.database.service;

import com.database.mapper.StudentMapper;
import com.database.pojo.Result;
import com.database.pojo.Student;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class StudentService {
    @Async("taskExecutor")
    public Result getStudentListByClassOrMajorOrCollege(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList;
        int classNo;
        int majorNo;
        int collegeNo;
        String sex;
        if ("".equals(map.get("classNo"))) {
            classNo = 0;
        } else {
            classNo = (int) map.get("classNo");
        }
        if ("".equals(map.get("majorNo"))) {
            majorNo = 0;
        } else {
            majorNo = (int) map.get("majorNo");
        }
        if ("".equals(map.get("collegeNo"))) {
            collegeNo = 0;
        } else {
            collegeNo = (int) map.get("collegeNo");
        }
        if ("".equals(map.get("sex"))) {
            sex = "";
        } else {
            sex = (String) map.get("sex");
        }
        System.out.println(sex);
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        int total = 0;

        if (collegeNo == 0) {
            if ("".equals(sex)) {
                total = studentMapper.getStudentTotal(0, collegeNo, majorNo, classNo, "", 0);
            } else {
                total = studentMapper.getStudentTotal(10, collegeNo, majorNo, classNo, sex, 0);
            }
            sex = "%" + sex + "%";
            studentList = studentMapper.getStudentLimit(pageSize, pageNum, sex, 0);
        } else if (majorNo == 0) {
            if ("".equals(sex)) {
                total = studentMapper.getStudentTotal(1, collegeNo, majorNo, classNo, "", 0);
            } else {
                total = studentMapper.getStudentTotal(11, collegeNo, majorNo, classNo, sex, 0);
            }
            sex = "%" + sex + "%";
            studentList = studentMapper.getStudentLimitByCollege(pageSize, pageNum, collegeNo, sex, 0);
        } else if (classNo == 0) {
            if ("".equals(sex)) {
                total = studentMapper.getStudentTotal(2, collegeNo, majorNo, classNo, "", 0);
            } else {
                total = studentMapper.getStudentTotal(12, collegeNo, majorNo, classNo, sex, 0);
            }
            sex = "%" + sex + "%";
            studentList = studentMapper.getStudentLimitByMajor(pageSize, pageNum, majorNo, sex, 0);
        } else {
            if ("".equals(sex)) {
                total = studentMapper.getStudentTotal(3, collegeNo, majorNo, classNo, "", 0);
            } else {
                total = studentMapper.getStudentTotal(13, collegeNo, majorNo, classNo, sex, 0);
            }
            sex = "%" + sex + "%";
            studentList = studentMapper.getStudentLimitByClass(pageSize, pageNum, classNo, sex, 0);
        }

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(studentList, total);
    }

    @Async("taskExecutor")
    public Result addStudent(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("no", map.get("no"));
        hashMap.put("password", map.get("password"));
        hashMap.put("classNo", map.get("classNo"));
        hashMap.put("sex", map.get("sex"));
        hashMap.put("name", map.get("name"));

        int i = studentMapper.addStudent(hashMap);
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
        if (i == 1) {
            return Result.suc();
        }
        return Result.fail();
    }

    @Async("taskExecutor")
    public Result updateStudent(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        int flag = 0;

        if ("".equals(map.get("password")) || null == map.get("password")) {
            if("".equals(map.get("classNo")) || null == map.get("classNo")) {
                map.put("type", 1);
            } else {
                map.put("type", 3);
            }
        } else {
            flag = 10;
            if("".equals(map.get("classNo")) || null == map.get("classNo")) {
                map.put("type", 2);
            } else {
                map.put("type", 4);
            }
        }

        int i = studentMapper.updateStudent(map);
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
        if (i == 1) {
            return Result.suc(null, flag);
        }
        return Result.fail();
    }

    public Result login(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        String no = (String) map.get("no");
        String password = (String) map.get("password");
        int i = studentMapper.checkStudentNo(no);

        if (i == 1) {
            Student studentById = studentMapper.getStudentById(no);
            if (studentById.getStudentPassword().equals(password)) {
                sqlSession.close();
                return Result.suc(studentById);
            }
        }

        // 关闭sqlSession
        sqlSession.close();
        return Result.fail();
    }

    public Result getStudentById(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        String no = (String) map.get("no");
        Student studentById = studentMapper.getStudentById(no);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(studentById);
    }
}
