package com.uchain.entity;

import java.io.Serializable;

public class CommentInfo implements Serializable {
    private Integer id;

    private String commentUser;

    private String commentMsg;

    private Integer commentBlog;

    private Integer commentFather;

    private String createTime;

    private String replyUserId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser == null ? null : commentUser.trim();
    }

    public String getCommentMsg() {
        return commentMsg;
    }

    public void setCommentMsg(String commentMsg) {
        this.commentMsg = commentMsg == null ? null : commentMsg.trim();
    }

    public Integer getCommentBlog() {
        return commentBlog;
    }

    public void setCommentBlog(Integer commentBlog) {
        this.commentBlog = commentBlog;
    }

    public Integer getCommentFather() {
        return commentFather;
    }

    public void setCommentFather(Integer commentFather) {
        this.commentFather = commentFather;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentUser=").append(commentUser);
        sb.append(", commentMsg=").append(commentMsg);
        sb.append(", commentBlog=").append(commentBlog);
        sb.append(", commentFather=").append(commentFather);
        sb.append(", createTime=").append(createTime);
        sb.append(", replyUserId=").append(replyUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}