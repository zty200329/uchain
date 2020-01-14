package com.uchain.entity;

import java.io.Serializable;
import java.util.Date;

public class ProBrief implements Serializable {
    private Integer id;

    private String proName;

    private String proUserStuId;

    private Date proUploadTime;

    private String proShow;

    private String fileTypeId;

    private String proDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getProUserStuId() {
        return proUserStuId;
    }

    public void setProUserStuId(String proUserStuId) {
        this.proUserStuId = proUserStuId == null ? null : proUserStuId.trim();
    }

    public Date getProUploadTime() {
        return proUploadTime;
    }

    public void setProUploadTime(Date proUploadTime) {
        this.proUploadTime = proUploadTime;
    }

    public String getProShow() {
        return proShow;
    }

    public void setProShow(String proShow) {
        this.proShow = proShow == null ? null : proShow.trim();
    }

    public String getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId == null ? null : fileTypeId.trim();
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc == null ? null : proDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", proName=").append(proName);
        sb.append(", proUserStuId=").append(proUserStuId);
        sb.append(", proUploadTime=").append(proUploadTime);
        sb.append(", proShow=").append(proShow);
        sb.append(", fileTypeId=").append(fileTypeId);
        sb.append(", proDesc=").append(proDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}