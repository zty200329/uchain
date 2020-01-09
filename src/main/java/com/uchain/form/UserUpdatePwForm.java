package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: zty
 * @Date: 2020/1/8 10:22
 */
@Data
public class UserUpdatePwForm {
    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String stuId;

    @NotNull(message = "旧密码不能为空")
    @ApiModelProperty("旧密码")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private String newPassword;
}
