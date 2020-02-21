package com.uchain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LZH
 * @Date: 2020/2/9 上午11:17
 * @Description:
 */
@Data
public class BlogForm {

    /**
     * 博客内容类别
     */
    @ApiModelProperty("文章类别")
    private Integer articleType;

    /**
     * 博客发布人
     */
    @ApiModelProperty("文章作者")
    private String articleOwner;

    /**
     * 博客标题
     */
    @ApiModelProperty("文章标题")
    private String articleTittle;

    /**
     * 博客发布时间
     */
    @ApiModelProperty("文章发布时间")
    private String articleCreateTime;

    /**
     * 博客内容
     */
    @ApiModelProperty("文章内容")
    private String articleContent;
}
