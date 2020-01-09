package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: zty
 * @Date: 2020/1/9 15:07
 */
@Data
public class UserSignatureForm {
    @NotNull(message = "个性签名不能为空")
    @ApiModelProperty("个性签名")
    String userSignature;
}
