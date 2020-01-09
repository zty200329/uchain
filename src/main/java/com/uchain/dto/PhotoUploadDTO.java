package com.uchain.dto;

import lombok.Data;

/**
 * @Author: zty
 * @Date: 2020/1/8 11:25
 */
@Data
public class PhotoUploadDTO {
    /**
     * 文件原始名
     */
    private String fileName;
    /**
     * 后缀 类似.ppt
     */
    private String suffix;
    /**
     * 新名称 以19700101开始的毫秒
     */
    private String newFileName;

    private String photoUrl;
}
