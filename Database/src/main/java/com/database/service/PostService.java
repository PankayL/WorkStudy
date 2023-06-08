package com.database.service;

import com.database.mapper.CompanyTypeMapper;
import com.database.mapper.PostMapper;
import com.database.pojo.CompanyType;
import com.database.pojo.Post;
import com.database.pojo.Result;
import com.database.utils.ApplicationContextHelperUtil;
import com.database.utils.MybatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-18:13
 */
@Slf4j
@Component
public class PostService {

    private static PostThreadService postThreadService;

    @Autowired
    public void setPostThreadService(PostThreadService postThreadService) {
        PostService.postThreadService = postThreadService;
    }

    public Result getPostById(int postNo) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        Post postById = mapper.getPostById(postNo);
        Thread t = Thread.currentThread();
        System.out.println(" 1 " + t.getName());

        postThreadService.setCount(postById.getPostNo(), postById.getPostCount());
        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(postById);
    }

    public Result updateCompanyPostStateByPostNo(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        int postNo = (int) hashMap.get("postNo");
        int postState = (int) hashMap.get("postState");
        int i = mapper.updateCompanyPostStateByPostNo(postNo, postState);
        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
        if (i == 1) {
            return Result.suc();
        }
        return Result.fail();
    }

    public Result addPost(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd" );

        String companyNo = (String) hashMap.get("companyNo");
        int postType = (int) hashMap.get("postType");
        String postTitle = (String) hashMap.get("postTitle");
        String postDetails = (String) hashMap.get("postDetails");

        java.util.Date startTime = null;
        try {
            startTime = sdf.parse(StringUtils.substringBefore((String) hashMap.get("startTime"), "T"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.util.Date endTime = null;
        try {
            endTime = sdf.parse(StringUtils.substringBefore((String) hashMap.get("endTime"), "T"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double postMoney = Double.valueOf((String) hashMap.get("postMoney")).doubleValue();

        int i = mapper.addPost(companyNo, postType, postTitle, postDetails, startTime, endTime, postMoney);

        sqlSession.commit();
        // 关闭sqlSession
        sqlSession.close();
        if (i == 1) {
            return Result.suc();
        }
        return Result.fail();
    }

    public Result getPostTotalByCompanyNo(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        String companyNo = (String) hashMap.get("companyNo");

        int postTotalByCompanyNo = mapper.getPostTotalByCompanyNo(companyNo);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(postTotalByCompanyNo);
    }

    public Result getPostListLimitByCompanyNoDesc(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        int pageSize = (int) hashMap.get("pageSize");
        int pageNum = (int) hashMap.get("pageNum");
        String companyNo = (String) hashMap.get("companyNo");

        List<Post> postListLimitByCompanyNoDesc = mapper.getPostListLimitByCompanyNoDesc(pageSize, pageNum, companyNo);
        int postTotalByCompanyNo = mapper.getPostTotalByCompanyNo(companyNo);

        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(postListLimitByCompanyNoDesc, postTotalByCompanyNo);
    }

    public Result getPostListLimitByCompanyNo(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        int pageSize = (int) hashMap.get("pageSize");
        int pageNum = (int) hashMap.get("pageNum");
        String companyNo = (String) hashMap.get("companyNo");
        String postState = null;
        int deleted = (int) hashMap.get("deleted");
        if ("".equals(hashMap.get("postState")) || null == hashMap.get("postState")) {
            postState = "%" + "%";
        } else {
            postState = "%" + (int) hashMap.get("postState") + "%";
        }

        List<Post> postListLimitByCompanyNo = mapper.getPostListLimitByCompanyNo(pageSize, pageNum, companyNo, postState, deleted);
        int postTotalByCompanyNo = mapper.getPostTotalByCompanyNo(companyNo);
        // 关闭sqlSession
        sqlSession.close();
        return Result.suc(postListLimitByCompanyNo, postTotalByCompanyNo);
    }

//    @Async
//    public void setCount(int postNo, int postCount) {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
//        Thread t = Thread.currentThread();
//        System.out.println(" 2 " + t.getName());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        while(mapper.setPostCount(postNo, postCount) == 0) {
//            Post postById = mapper.getPostById(postNo);
//            postCount = postById.getPostCount();
//        }
//        sqlSession.commit();
//        sqlSession.close();
//    }


    public Result getPostListLimit(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        int pageSize = (int) hashMap.get("pageSize");
        int pageNum = (int) hashMap.get("pageNum");
        int postState = (int) hashMap.get("postState");
        int deleted = (int) hashMap.get("deleted");
        String companyName = "%" + (String) hashMap.get("companyName") + "%";
        String typeNo;
        int sort = -1;
        if ("".equals(hashMap.get("typeNo"))) {
            typeNo = "%" + "%";
        } else {
            typeNo = "%" + (int) hashMap.get("typeNo") + "%";
        }
        if (!"".equals(hashMap.get("sort"))) {
            sort = (int) hashMap.get("sort");
        }
        int postTotal = getPostTotal(companyName, typeNo, postState, deleted);
        List<Post> postListLimit;

        if ("".equals(hashMap.get("count")) || null == hashMap.get("count")) {
            switch (sort) {
                case 0:
                    postListLimit = mapper.getPostListLimitDesc(pageSize, pageNum, companyName, typeNo, postState, deleted);
                    break;
                case 1:
                    postListLimit = mapper.getPostListLimitAsc(pageSize, pageNum, companyName, typeNo, postState, deleted);
                    break;
                default:
                    postListLimit = mapper.getPostListLimit(pageSize, pageNum, companyName, typeNo, postState, deleted);
            }
        } else {
            int count = (int) hashMap.get("count");
            switch (count) {
                case 1:
                    postListLimit = mapper.getPostListLimitCountAsc(pageSize, pageNum, companyName, typeNo, postState, deleted);
                    break;
                default:
                    postListLimit = mapper.getPostListLimitCountDesc(pageSize, pageNum, companyName, typeNo, postState, deleted);
            }
        }


        // 关闭sqlSession
        sqlSession.close();
        if (postListLimit.size() >= 1) {
            return Result.suc(postListLimit, postTotal);
        }
        return Result.fail();
    }

    public int getPostTotal(String companyName, String typeNo, int postState, int deleted) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        int postTotal = mapper.getPostTotal(companyName, typeNo, postState, deleted);

        // 关闭sqlSession
        sqlSession.close();
        return postTotal;
    }

    public Result deletePost(int postNo) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);

        mapper.deletePost(postNo);

        Post postById = mapper.getPostById(postNo);

        sqlSession.commit();

        // 关闭sqlSession
        sqlSession.close();
        if (postById.getDeleted() == 1) {
            return Result.suc();
        }
        return Result.fail();
    }

    public Result checkPost(HashMap hashMap) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        PostMapper mapper = sqlSession.getMapper(PostMapper.class);
        int postNo = (int) hashMap.get("postNo");
        int postState = (int) hashMap.get("postState");
        mapper.checkPost(postNo,postState);

        Post postById = mapper.getPostById(postNo);
        sqlSession.commit();

        if (postById.getPostState() == postState) {
            return Result.suc();
        }

        return Result.fail();
    }
}
