package com.uchain.form;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @Author: zty
 * @Date: 2020/1/7 19:07
 * 注册所用表单（初始密码固定为123456）
 */
@Data
public class UserRegisterForm {
    /**
     * 姓名
     */
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("姓名")
    String username;

    /**
     * 学号
     */
    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    String stuId;

    /**
     * 小组
     */
    @NotNull(message = "分组不能为空")
    @ApiModelProperty("分组（1.后端 2.前端 3.区块链 4.算法 5.UI）")
    Integer userType;

    /**
     * 我目前的想法是下面这两个后期自己修改
     * 管理员注册时不用理会
     */
    /**
     * 自拍(上传的是图片 数据库存的是url！！)
     */
//    @ApiModelProperty("照片")
//    MultipartFile userPicture;

    /**
     * 个性签名
     */
//    @ApiModelProperty("个性签名")
//    String userSignature;
}
