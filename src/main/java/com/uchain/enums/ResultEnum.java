package com.uchain.enums;

import lombok.Getter;

/**
 * @author hobo
 * @description
 */
@Getter
public enum ResultEnum {
    /**
     *
     */
    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式"),
    USER_NOT_EXIST(1, "用户不存在"),
    PASSWORD_ERROR(2, "密码错误"),
    SQL_ERROR(3, "添加用户失败"),
    PARAMETER_ERROR(4,"请注意必填项"),
    USER_HAS_EXIST(5, "用户已经存在"),
    IS_NOT_PERSONAL_OPERATION(6,"用户名不存在,修改密码失败"),
    UPLOAD_SIGNATURES_FAIL(7,"修改签名失败"),
    PROJECT_HAS_EXIST(8,"项目已经存在了"),
    UPLOAD_FILE(9,"文件上传失败");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
