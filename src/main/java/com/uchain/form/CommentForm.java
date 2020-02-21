package com.uchain.form;

import lombok.Data;

/**
 * @Author: LZH
 * @Date: 2020/2/18 下午5:01
 * @Description:
 */
@Data
public class CommentForm {

    /**
     * 博客id
     */
    private Integer commentBlog;

    /**
     * 评论人
     */
    private String commentUser;

    /**
     * 评论内容
     */
    private String commentMsg;

    /**
     * 评论父id
     */
    private Integer commentFather;

    /**
     * 评论对象
     */
    private String replyUserId;

    /**
     * 评论发布时间
     */
    private String createTime;
}
