package com.database.Mapper;

import com.database.mapper.PostTypeMapper;
import com.database.mapper.StudentMapper;
import com.database.pojo.PostType;
import com.database.pojo.Student;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:31
 */
public class StudentMapperTest {
    @Test
    public void getStudentTotalTest() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int type = 0;
        int collegeNo = 110000;
        int majorNo = 210000;
        int classNo = 310000;
        String sex = "男";
        int deleted = 0;

        // 方式一：getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        int studentTotal = mapper.getStudentTotal(type, collegeNo, majorNo, classNo, sex, deleted);
        System.out.println(studentTotal);

        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getStudentByIdTest() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        String studentNo = "202014010528";

        // 方式一：getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student studentById = mapper.getStudentById(studentNo);
        System.out.println(studentById);


        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getStudentLimit() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        String studentNo = "202014010528";

        // 方式一：getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentLimit = mapper.getStudentLimit(5, 2, "", 0);
        studentLimit.forEach(System.out::println);

        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getStudentLimitByCollege() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一：getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentLimit = mapper.getStudentLimitByCollege(5, 1, 110002, "", 0);
        studentLimit.forEach(System.out::println);

        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getStudentLimitByMajor() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一：getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentLimit = mapper.getStudentLimitByMajor(5, 2, 210001, "", 0);
        studentLimit.forEach(System.out::println);

        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getStudentLimitByClass() {
        // 第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一：getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentLimit = mapper.getStudentLimitByClass(5, 1, 310010, "", 0);
        studentLimit.forEach(System.out::println);

        // 关闭sqlSession
        sqlSession.close();
    }
}
