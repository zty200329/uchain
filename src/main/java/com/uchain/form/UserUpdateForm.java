package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserUpdateForm
 * @Description 管理员更新用户信息
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Data
public class UserUpdateForm {

    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String stuId;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String userName;

    @NotNull(message = "小组不能为空")
    @ApiModelProperty("小组")
    private String groupType;
}
