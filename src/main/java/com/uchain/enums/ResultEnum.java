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

    PROJECT_EXIST(101, "项目存在"),
    PROJECT_NOT_EXIST(102, "项目不存在"),
    PRO_UPLOAD_ERROR(103, "文件上传出错"),
    PRO_UPLOAD_SUCCESS(104, "上传成功"),
    DELETE_ERROR(105, "文件删除有误！"),
    DELETE_SUCCESS(106, "文件删除有误！"),
    ARTICLE_IS_NOT_EXIST(107, "博客不存在"),
    FILE_EXIST(108, "文件已存在"),
    USERNAME_NOT_EXIST(109, "用户名不能为空"),
    LOGIN_INFO_IS_NULL(110, "用户名密码不能为空"),
    SQL_ERROR(111, "数据库操作失败"),
    PROJECT_ALREADY_EXIST(112, "博客标题已存在"),
    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式"),
    USER_NOT_EXIST(1,"此用户不存在" ),
    PASSWORD_ERROR(2,"密码错误" ),
    OLD_PASSWORD_ERROR(3,"原密码错误" ),
    IS_NOT_PERSONAL_OPERATION(4,"非本人操作" ),
    USER_ALREADY_EXIST(5, "此用户已存在"),
    PARAMETER_ERROR(6,"请注意必填项"),
    UPLOAD_SIGNATURES_FAIL(7,"修改签名失败"),
    PROJECT_HAS_EXIST(8,"项目已经存在了"),
    UPLOAD_FILE(9,"文件上传失败"),
    FILE_IS_NOT_EXIST(10,"文件不存在"),
    RESOURCE_DELETE_FAILED(11,"删除资源失败"),
    RESOURCE_ADD_FAILED(12,"添加资源失败"),
    RESOURCE_UPDATE_FAILED(13,"更新资源失败"),
    RESOURCE_NOT_EXISTED(14,"资源不存在"),
    RESOURCE_QUERY_FAILED(15,"查询资源失败"),
    USER_DELETE_FAILED(16,"用户删除失败"),
    ROLE_CANNOT_BE_NULL(17, "权限不能为空"),
    BASIC_INFO_CANNOT_BE_NULL(18, "基本信息不能为空"),
    USER_ARTICLE_NULL(19, "该用户未发表博客");
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
