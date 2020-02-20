package com.uchain.dao;

import com.uchain.entity.Contribute;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author czy
 */
@Mapper
public interface ContributeMapper {
    int deleteByPrimaryKey(Integer conId);

    int insert(Contribute record);

    Contribute selectByPrimaryKey(Integer conId);

    List<Contribute> selectAll();

    int updateByPrimaryKey(Contribute record);

    /**
     * 根据学生姓名模糊查询
     * @param stuName
     * @return
     */
    List<Contribute> queryByName(String stuName);

    List<Contribute> queryByGroupId(Integer groupId);

    List<Contribute> queryByTagId(Integer tagId);

    /**
     * 根据资源名称模糊查询
     * @param reName
     * @return
     */
    List<Contribute> queryByReName(String reName);
}
