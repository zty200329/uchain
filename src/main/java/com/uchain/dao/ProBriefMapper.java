package com.uchain.dao;

import com.uchain.entity.ProBrief;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProBriefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProBrief record);

    ProBrief selectByPrimaryKey(Integer id);

    List<ProBrief> selectAll();

    int updateByPrimaryKey(ProBrief record);

    ProBrief slectByProName(String name);
}