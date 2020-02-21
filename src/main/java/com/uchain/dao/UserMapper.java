package com.uchain.dao;

import com.uchain.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String stuId);

    int insert(User record);

    User selectByPrimaryKey(String stuId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 通过小组查找用户
     *
     * @param groupId
     * @return
     */
    List<User> selectByGroupId(Integer groupId);
}