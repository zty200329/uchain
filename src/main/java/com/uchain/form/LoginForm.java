package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: zty
 * @Date: 2020/1/8 10:06
 * 登录表单
 */
@Data
public class LoginForm {
    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String stuId;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
}
