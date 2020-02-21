package com.uchain.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description 分小组展示的
 * @ClassName AllUserVO1
 * @Author: baobao
 * @Date: Created in 9:38 2020/2/16
 */
@Data
public class GroupUserVO {
    private Integer groupId;

    private String groupName;

    List<AllUserVO> allUserVOS;
}
