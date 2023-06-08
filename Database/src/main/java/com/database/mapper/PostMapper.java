package com.database.mapper;

import com.database.pojo.Company;
import com.database.pojo.Post;

import java.util.Date;
import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface PostMapper {
    // 通过招聘单号获取招聘信息
    Post getPostById(int postNo);

    // 分页查询
    List<Post> getPostListLimit(int pageSize, int pageNum, String companyName, String typeNo, int postState, int deleted);
    List<Post> getPostListLimitAsc(int pageSize, int pageNum, String companyName, String typeNo, int postState, int deleted);
    List<Post> getPostListLimitDesc(int pageSize, int pageNum, String companyName, String typeNo, int postState, int deleted);
    List<Post> getPostListLimitCountAsc(int pageSize, int pageNum, String companyName, String typeNo, int postState, int deleted);
    List<Post> getPostListLimitCountDesc(int pageSize, int pageNum, String companyName, String typeNo, int postState, int deleted);
    // 分页查询时获取总数
    int getPostTotal(String companyName, String typeNo, int postState, int deleted);

    // 删除招聘
    void deletePost(int postNo);

    // 审核招聘申请
    void checkPost(int postNo, int postState);

    // 浏览次数加1
    int setPostCount(int postNo, int postCount);

    // 获取某单位招聘总数
    int getPostTotalByCompanyNo(String companyNo);
    // 获取单位招聘列表
    List<Post> getPostListLimitByCompanyNo(int pageSize, int pageNum, String companyNo, String postState, int deleted);
    // 获取单位申请列表
    List<Post> getPostListLimitByCompanyNoDesc(int pageSize, int pageNum, String companyNo);
    // 更新状态
    // -1 审核拒绝，0 未审核，1 审核通过，2 已结束，3 已删除
    int updateCompanyPostStateByPostNo(int postNo, int postState);

    // 添加招聘
    int addPost(String companyNo, int postType, String postTitle, String postDetails, Date startTime, Date endTime, double postMoney);
}
