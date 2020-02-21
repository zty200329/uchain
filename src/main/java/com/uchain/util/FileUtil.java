package com.uchain.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: LZH
 * @Date: 2020/2/7 下午8:39
 * @Description:
 */
@Slf4j
public class FileUtil {

    public static Boolean uploadPic(String filepath, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        //指定文件保存路径
        String pathName = filepath;
        //获取文件名，包括后缀
        String fileName = file.getOriginalFilename();
        pathName = pathName + "/" +fileName;
        log.info(fileName);
        log.info("保存的路径为：" + pathName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathName);
            //写入文件
            fos.write(file.getBytes());
            log.info("文件上传成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("文件上传失败！");
            return false;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
