package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: zty
 * @Date: 2020/1/10 21:47
 */
@Data
public class ProBriefForm {

    @NotNull(message = "项目名称不为空")
    @ApiModelProperty("项目名称")
    private String proName;

    @NotNull(message = "上传者学号不能为空")
    @ApiModelProperty("上传者学号")
    private String proUserStuId;

    @NotNull(message = "上传时间不能为空")
    @ApiModelProperty("文件上传时间")
    private Date proUploadTime;

    @NotNull(message = "文件类型不能为空")
    @ApiModelProperty("文件类型")
    private String fileType;

    @ApiModelProperty("展示路径")
    private String proShow;

    @ApiModelProperty("项目描述（）")
    private String proDesc;
}
