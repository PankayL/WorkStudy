package com.database.mapper;

import com.database.pojo.PostType;

import java.util.List;

/**
 * @author pankay
 * @create 2022-12-14-17:58
 */
public interface PostTypeMapper {
    // 获取岗位种类列表
    List<PostType> getPostTypeList();
}
