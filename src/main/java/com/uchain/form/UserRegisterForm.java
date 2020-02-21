package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Lenovo
 */
@Data
public class UserRegisterForm {
    /**
     * 姓名
     */
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("姓名")
    String userName;

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
    String groupType;

}
