package com.uchain.vo;

import lombok.Data;

/**
 * @ClassName UserVO
 * @Description 个人展示
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Data
public class UserVO {
    private String stuId;

    private String userName;

    private String groupType;

    private Integer role;

    private String userDesc;

    private String userSignature;

    private String headPictureUrl;
}

