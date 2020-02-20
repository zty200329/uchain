package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author czy
 * @date 2020/2/1 - 15:42
 * @Classname
 * @Description
 */
@Data
public class ReForm {

    /**
     * 学生学号
     */
    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String stuId;

    /**
     * 学生姓名
     */
    @NotNull(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String stuName;

    /**
     * 方向编号
     */
    @NotNull(message = "方向不能为空")
    @ApiModelProperty("方向名称")
    private Integer groupId;

    /**
     * 资源名称
     */
    @NotNull(message = "资源不能为空")
    @ApiModelProperty("资源名称")
    private String reName;

    /**
     * 资源链接
     */
    @NotNull(message = "链接不能为空")
    @ApiModelProperty("链接")
    private String reUrl;

    /**
     * 资源简介
     */
    @NotNull(message = "资源简介不能为空")
    @ApiModelProperty("资源简介")
    private String reBrief;


    /**
     * 资源分类
     */
    @NotNull(message = "资源标签不能为空")
    @ApiModelProperty("资源标签")
    private Integer reTag;
}
