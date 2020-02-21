package com.uchain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogInfo implements Serializable {
    private Integer id;

    private Integer articleType;

    private String articleOwner;

    private String articleTittle;

    private String articleCreateTime;

    private Integer istop;

    private Integer articleLike;

    private String articleContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public String getArticleOwner() {
        return articleOwner;
    }

    public void setArticleOwner(String articleOwner) {
        this.articleOwner = articleOwner == null ? null : articleOwner.trim();
    }

    public String getArticleTittle() {
        return articleTittle;
    }

    public void setArticleTittle(String articleTittle) {
        this.articleTittle = articleTittle == null ? null : articleTittle.trim();
    }

    public String getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(String articleCreateTime) {
        this.articleCreateTime = articleCreateTime == null ? null : articleCreateTime.trim();
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Integer getArticleLike() {
        return articleLike;
    }

    public void setArticleLike(Integer articleLike) {
        this.articleLike = articleLike;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleType=").append(articleType);
        sb.append(", articleOwner=").append(articleOwner);
        sb.append(", articleTittle=").append(articleTittle);
        sb.append(", articleCreateTime=").append(articleCreateTime);
        sb.append(", istop=").append(istop);
        sb.append(", articleLike=").append(articleLike);
        sb.append(", articleContent=").append(articleContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}