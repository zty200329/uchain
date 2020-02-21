package com.uchain.dto;

import lombok.Data;

/**
 * @Author: LZH
 * @Date: 2020/2/10 下午3:42
 * @Description:
 */
@Data
public class ShowDTO {

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
}
