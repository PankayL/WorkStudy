package com.database.service;

import com.database.mapper.StudentMapper;
import com.database.mapper.StudentResumeMapper;
import com.database.pojo.Result;
import com.database.pojo.StudentResume;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class StudentResumeService {
    public Result addStudentResume(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int postNo = (int) map.get("postNo");
        String studentNo = (String) map.get("studentNo");
        String resume = (String) map.get("resume");

        StudentResume last = mapper.getStudentResumeByPostStudentLast(postNo, studentNo);
        if (last != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = null;
            try {
                parse = df.parse("" + last.getTime() + " " + last.getTime1());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long nowTime = new Date().getTime();
            long lastTime = new Date().getTime();
            if (parse != null) {
                lastTime = parse.getTime();
            }

            double days = (nowTime - lastTime) * 1.0 / 24 / 60 / 60 / 1000;
            if (days >= 3) {
                int i = mapper.addStudentResume(postNo, studentNo, resume);
                sqlSession.commit();
                // 关闭sqlSession
                sqlSession.close();
                return Result.suc();
            }

            // 关闭sqlSession
            sqlSession.close();
            return Result.fail();
        } else {
            int i = mapper.addStudentResume(postNo, studentNo, resume);
            sqlSession.commit();
            // 关闭sqlSession
            sqlSession.close();
            return Result.suc();
        }
    }

    public Result getStudentResumeListLimitByStudentNo(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int pageSize = (int) map.get("pageSize");
        int pageNum = (int) map.get("pageNum");
        String studentNo = (String) map.get("studentNo");
        int resumeState = (int) map.get("resumeState");

        List<StudentResume> studentResumeListLimitByStudentNo = mapper.getStudentResumeListLimitByStudentNo(pageSize, pageNum, resumeState, studentNo);

        sqlSession.close();
        return Result.suc(studentResumeListLimitByStudentNo, studentResumeListLimitByStudentNo.size());
    }

    public Result getStudentResumeAllListLimitByStudentNo(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int pageSize = (int) map.get("pageSize");
        int pageNum = (int) map.get("pageNum");
        String studentNo = (String) map.get("studentNo");
        int deleted = (int) map.get("deleted");

        List<StudentResume> studentResumeListLimitByStudentNo = mapper.getStudentResumeAllListLimitByStudentNo(pageSize, pageNum, studentNo, deleted);

        sqlSession.close();
        return Result.suc(studentResumeListLimitByStudentNo, studentResumeListLimitByStudentNo.size());
    }

    public Result deleteStudentResume(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int resumeNo = (int) map.get("resumeNo");

        int i = mapper.deleteStudentResume(resumeNo);

        sqlSession.commit();
        sqlSession.close();
        return Result.suc();
    }

    public Result leaveStudentResume(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int resumeNo = (int) map.get("resumeNo");
        int deleted = (int) map.get("deleted");

        int i = mapper.leaveStudentResume(resumeNo, deleted);

        sqlSession.commit();
        sqlSession.close();
        return Result.suc();
    }

    public Result getStudentResumeListLimitByCompanyNo(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int pageSize = (int) map.get("pageSize");
        int pageNum = (int) map.get("pageNum");
        int deleted = (int) map.get("deleted");
        String companyNo = (String) map.get("companyNo");

        List<StudentResume> studentResumeListLimitByCompanyNo = mapper.getStudentResumeListLimitByCompanyNo(pageSize, pageNum, companyNo, deleted);
        int studentResumeTotalByCompanyNo = getStudentResumeTotalByCompanyNo(map);

        sqlSession.close();
        return Result.suc(studentResumeListLimitByCompanyNo, studentResumeTotalByCompanyNo);
    }

    public int getStudentResumeTotalByCompanyNo(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int deleted = (int) map.get("deleted");
        String companyNo = (String) map.get("companyNo");

        int studentResumeTotalByCompanyNo = mapper.getStudentResumeTotalByCompanyNo(companyNo, deleted);

        sqlSession.close();
        return studentResumeTotalByCompanyNo;
    }

    public Result updateStudentResumeState(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentResumeMapper mapper = sqlSession.getMapper(StudentResumeMapper.class);

        int resumeState = (int) map.get("resumeState");
        int resumeNo = (int) map.get("resumeNo");

        int i = mapper.updateStudentResumeState(resumeNo, resumeState);

        sqlSession.commit();
        sqlSession.close();
        if (i == 1) {
            return Result.suc();
        }
        return Result.fail();
    }
}
