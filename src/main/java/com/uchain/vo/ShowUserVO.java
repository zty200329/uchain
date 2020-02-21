package com.uchain.vo;

import lombok.Data;

/**
 * @Description TODO
 * @ClassName ShowUserVO
 * @Author: baobao
 * @Date: Created in 21:21 2020/2/20
 */
@Data
public class ShowUserVO {

    private Integer key;

    private String stuId;

    private String userName;

    private String groupType;

    private String userDesc;

    private String userSignature;

    private String headPictureUrl;

}
