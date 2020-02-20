package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserInformationForm
 * @Description 修改个性签名和个人简介的表单
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/
@Data
public class UserSignatureForm {

    @ApiModelProperty("个性签名")
    private String userSignature;
}
