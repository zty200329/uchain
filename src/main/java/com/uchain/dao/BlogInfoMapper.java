package com.uchain.dao;


import com.uchain.entity.BlogInfo;

import java.util.List;

public interface BlogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogInfo record);

    BlogInfo selectByPrimaryKey(Integer id);

    List<BlogInfo> selectAll();

    int updateByPrimaryKey(BlogInfo record);

    List<BlogInfo> selectByArticleOwner(String articleOwner);

    BlogInfo selectByArticleTittle(String articleTittle);

    List<BlogInfo> selectByArticleType(Integer type);

}