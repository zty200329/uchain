package com.uchain.dto;

import lombok.Data;

/**
 * @Author: LZH
 * @Date: 2020/2/7 下午7:49
 * @Description:
 */
@Data
public class VisitedDTO {

    /**
     * 博客标题
     */
    private String bid;

    /**
     * 博客访问量
     */
    private Integer amount;
}
