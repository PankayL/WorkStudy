package com.database.service;

import com.database.mapper.StudentAssetsMapper;
import com.database.mapper.StudentIncomeMapper;
import com.database.mapper.StudentMapper;
import com.database.pojo.Result;
import com.database.pojo.Student;
import com.database.pojo.StudentAssets;
import com.database.pojo.StudentIncome;
import com.database.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
public class StudentIncomeService {
    public Result getStudentIncomeListList(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentIncomeMapper mapper = sqlSession.getMapper(StudentIncomeMapper.class);

        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String no = (String) map.get("no");
        String isFlag;
        if ("".equals(map.get("isFlag")) || null == map.get("isFlag")) {
            isFlag = "%%";
        } else {
            isFlag = "%" + (int) map.get("isFlag") + "%";
        }
        int studentIncomeTotal = getStudentIncomeTotal(no, isFlag);
        List<StudentIncome> studentIncomeListByNo = mapper.getStudentIncomeListLimit(pageNum, pageSize, no, isFlag);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(studentIncomeListByNo, studentIncomeTotal);
    }

    @Async("taskExecutor")
    public int getStudentIncomeTotal(String studentNo, String isFlag) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentIncomeMapper mapper = sqlSession.getMapper(StudentIncomeMapper.class);

        int studentIncomeTotal = mapper.getStudentIncomeTotal(studentNo, isFlag);

        // 关闭sqlSession
        sqlSession.close();
        return studentIncomeTotal;
    }

    @Async("taskExecutor")
    public Result drawStudentIncome(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentIncomeMapper mapper = sqlSession.getMapper(StudentIncomeMapper.class);
        StudentMapper mapper1 = sqlSession.getMapper(StudentMapper.class);
        StudentAssetsMapper mapper2 = sqlSession.getMapper(StudentAssetsMapper.class);
        int isFlag = 0;
        String no = (String) map.get("no");
        String password = (String) map.get("password");
        double money;
        StudentAssets studentAssetsById = mapper2.getStudentAssetsById(no);
        if ("".equals(map.get("money")) || null == map.get("money")) {
            // 取款金款金额不符合规范
            isFlag = 10;
        } else {
            money = Double.valueOf((String) map.get("money"));
            if (money <= 0.0001 || money > studentAssetsById.getAssetsTotal()) {
                isFlag = 10;
            }
        }

        money = Double.valueOf((String) map.get("money"));
        if (isFlag != 10) {
            Student studentById = mapper1.getStudentById(no);
            if (studentById.getStudentPassword().equals(password)) {
                mapper.drawStudentIncome(no, (-money));
                sqlSession.commit();
                // 取款成功
                isFlag = 12;

                money = studentAssetsById.getAssetsTotal() - money;
                while( mapper2.updateStudentAssets(no, money, studentAssetsById.getVersion()) == 0) {
                    studentAssetsById = mapper2.getStudentAssetsById(no);
                    money = studentAssetsById.getAssetsTotal() - Double.valueOf((String) map.get("money"));
                    if (money < 0) {
                        isFlag = 10;
                        break;
                    }
                }
                sqlSession.commit();
            } else {
                // 密码错误
                isFlag = 11;
            }
        }
        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(null, isFlag);
    }

    @Async("taskExecutor")
    public Result giveStudentIncome(HashMap map) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentIncomeMapper studentIncomeMapper = sqlSession.getMapper(StudentIncomeMapper.class);
        StudentAssetsMapper studentAssetsMapper = sqlSession.getMapper(StudentAssetsMapper.class);

        String studentNo = (String) map.get("studentNo");
        double salary = (double) map.get("salary");

        studentIncomeMapper.giveStudentIncome(studentNo, salary);
        sqlSession.commit();

        StudentAssets studentAssetsById = studentAssetsMapper.getStudentAssetsById(studentNo);
        double money = studentAssetsById.getAssetsTotal() + salary;
        while( studentAssetsMapper.updateStudentAssets(studentNo, money, studentAssetsById.getVersion()) == 0) {
            studentAssetsById = studentAssetsMapper.getStudentAssetsById(studentNo);
            money = studentAssetsById.getAssetsTotal() + salary;
        }

        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
        return Result.suc();
    }
}
