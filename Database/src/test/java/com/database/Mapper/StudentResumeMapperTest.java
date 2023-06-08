package com.database.Mapper;

import com.database.mapper.StudentMapper;
import com.database.mapper.StudentResumeMapper;
import com.database.pojo.StudentResume;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pankay
 * @create 2022-12-14-18:31
 */
public class StudentResumeMapperTest {
    @Test
    public void getStudentResumeByPostStudentLastTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一：getMapper
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        StudentResume studentResumeByPostStudentLast = mapper.getStudentResumeByPostStudentLast(343052005, "202014010530");
        System.out.println(studentResumeByPostStudentLast.getTime());
        System.out.println(studentResumeByPostStudentLast.getTime1());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        Date parse1 = null;
        try {
            parse = df.parse("2022-12-15 20:03:56");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            parse1 = df.parse("" + studentResumeByPostStudentLast.getTime() + " " + studentResumeByPostStudentLast.getTime1());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long startTime = parse.getTime();
        Long endTime = parse1.getTime();
        Long num = endTime - startTime;
        System.out.println(1.0 * num / 24 / 60 / 60 / 1000);
    }

    @Test
    public void getStudentResumeByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方式一：getMapper
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        StudentResume studentResumeById = mapper.getStudentResumeById(45782301);
        System.out.println(studentResumeById);
    }
}
