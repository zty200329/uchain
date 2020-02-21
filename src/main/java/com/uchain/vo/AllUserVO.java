package com.uchain.vo;

import lombok.Data;

/**
 * @ClassName AllUserVO
 * @Description 管理员展示用户
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Data
public class AllUserVO {

    private Integer key;

    private String stuId;

    private String userName;

    private String groupType;

    private Integer role;
}
