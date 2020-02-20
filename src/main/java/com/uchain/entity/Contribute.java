package com.uchain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author czy
 */
@Data
public class Contribute implements Serializable {

    /**
     * 主键
     */
    private Integer conId;


    /**
     * 学号
     */
    private String stuId;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 方向编号
     */
    private Integer groupId;

    /**
     * 资源名称
     */
    private String reName;

    /**
     * 资源链接
     */
    private String reUrl;

    /**
     * 资源简介
     */
    private String reBrief;

    /**
     * 资源添加时间
     */
    private Date reTime;

    private Integer reTag;

    public Contribute(String stuId, String stuName, Integer groupId,
                      String reName, String reUrl, String reBrief, Integer reTag) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.groupId = groupId;
        this.reName = reName;
        this.reUrl = reUrl;
        this.reBrief = reBrief;
        this.reTag = reTag;
    }

    public Contribute() {
    }

    private static final long serialVersionUID = 1L;

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName == null ? null : reName.trim();
    }

    public String getReUrl() {
        return reUrl;
    }

    public void setReUrl(String reUrl) {
        this.reUrl = reUrl == null ? null : reUrl.trim();
    }

    public String getReBrief() {
        return reBrief;
    }

    public void setReBrief(String reBrief) {
        this.reBrief = reBrief == null ? null : reBrief.trim();
    }

    public Date getReTime() {
        return reTime;
    }

    public void setReTime(Date reTime) {
        this.reTime = reTime;
    }

    public Integer getReTag() {
        return reTag;
    }

    public void setReTag(Integer reTag) {
        this.reTag = reTag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", conId=").append(conId);
        sb.append(", stuId=").append(stuId);
        sb.append(", stuName=").append(stuName);
        sb.append(", groupId=").append(groupId);
        sb.append(", reName=").append(reName);
        sb.append(", reUrl=").append(reUrl);
        sb.append(", reBrief=").append(reBrief);
        sb.append(", reTime=").append(reTime);
        sb.append(", reTag=").append(reTag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
