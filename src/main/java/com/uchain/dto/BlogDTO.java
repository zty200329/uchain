package com.uchain.dto;

import lombok.Data;

/**
 * @Author: LZH
 * @Date: 2020/2/6 下午7:27
 * @Description:
 */
@Data
public class BlogDTO {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 博客类型
     */
    private Integer articleType;

    /**
     * 博主
     */
    private String articleOwner;

    /**
     * 博客标题
     */
    private String articleTittle;

    /**
     * 博客发布时间
     */
    private String articleCreateTime;

    /**
     * 是否置顶
     */
    private Integer istop;

    /**
     * 博客点赞数
     */
    private Integer articleLike;

    /**
     * 博客内容
     */
    private String articleContent;

    /**
     * 博客访问量
     */
    private Integer articleVisited;
}
