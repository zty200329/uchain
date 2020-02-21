package com.uchain.dao;


import com.uchain.entity.CommentInfo;

import java.util.List;
import org.mapstruct.Mapper;
@Mapper
public interface CommentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);

    CommentInfo selectByPrimaryKey(Integer id);

    List<CommentInfo> selectAll();

    int updateByPrimaryKey(CommentInfo record);

    List<CommentInfo> selectByBlogId(Integer bid);
}