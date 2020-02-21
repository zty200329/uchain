package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @ClassName UserDescriptionForm
 * @Author: baobao
 * @Date: Created in 22:14 2020/2/19
 */
@Data
public class UserDescriptionForm {

    @ApiModelProperty("个人简介")
    private String userDesc;
}
