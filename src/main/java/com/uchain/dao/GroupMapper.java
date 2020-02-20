package com.uchain.dao;

import com.uchain.entity.Group;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    Group selectByPrimaryKey(Integer groupId);

    List<Group> selectAll();

    int updateByPrimaryKey(Group record);
}