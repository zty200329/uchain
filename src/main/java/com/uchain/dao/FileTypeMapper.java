package com.uchain.dao;

import com.uchain.entity.FileType;
import java.util.List;

public interface FileTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileType record);

    FileType selectByPrimaryKey(Integer id);

    List<FileType> selectAll();

    int updateByPrimaryKey(FileType record);
}