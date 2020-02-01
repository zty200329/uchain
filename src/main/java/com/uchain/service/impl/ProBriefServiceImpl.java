package com.uchain.service.impl;

import com.sun.deploy.net.HttpResponse;
import com.uchain.dao.FileMapper;
import com.uchain.dao.ProBriefMapper;
import com.uchain.entity.ProBrief;
import com.uchain.enums.ResultEnum;
import com.uchain.form.ProBriefForm;
import com.uchain.service.ProBriefService;
import com.uchain.util.ResultVOUtil;
import com.uchain.util.ZipUtils;
import com.uchain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.Main;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: zty
 * @Date: 2020/1/11 12:14
 */
@Service
@Slf4j
public class ProBriefServiceImpl implements ProBriefService {
    @Autowired
    private ProBriefMapper proBriefMapper;
    @Autowired
    private FileMapper fileMapper;
    @Value("${Pro.realPath}")
    private String realPath;
    @Value("${Pro.linshi}")
    private String linshiPath;
    @Override
    @Transactional

    public ResultVO uploadProBrief(ProBriefForm proBriefForm, MultipartFile file, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("参数出错，注意必填项");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        if(isProExist(proBriefForm.getProName())){
            log.error("项目已经存在！");
            return ResultVOUtil.error(ResultEnum.PROJECT_HAS_EXIST);
        }

        log.info("项目名："+proBriefForm.getProName());
        log.info("上传者学号："+proBriefForm.getProUserStuId());
        log.info("上传时间"+proBriefForm.getProUploadTime());
        log.info("文件类型"+proBriefForm.getFileType());
        log.info("展示路径"+proBriefForm.getProShow());
        log.info("多文件上传路径："+realPath+proBriefForm.getProName());
        File dest = new File(realPath+proBriefForm.getProName());

        //检查该文件夹是否存在
        if(!dest.exists()){
            dest.mkdirs();
        }

        //项目表
        ProBrief proBrief = new ProBrief();
        BeanUtils.copyProperties(proBriefForm,proBrief);
        proBrief.setFileTypeId(proBriefForm.getFileType());
        proBriefMapper.insert(proBrief);
        int proId = proBrief.getId();


        log.info("开始上传文件");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath+proBriefForm.getProName()+File.separator+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        com.uchain.entity.File multipartfile = new com.uchain.entity.File();
        multipartfile.setFileName(fileName);
        multipartfile.setFileTypeId(proBriefForm.getFileType());
        multipartfile.setFileUrl(realPath+proBriefForm.getProName());
        multipartfile.setProId(proId);
        fileMapper.insert(multipartfile);

        return ResultVOUtil.success();
    }


    /**
     * 根据传入的项目名来判断项目是否存在
     * @param proName
     * @return
     */
    @Override
    public Boolean isProExist(String proName) {
        if(proBriefMapper.slectByProName(proName)!=null){
            return true;
        }
        return false;
    }

    /**
     * 前端发来的probrief表的主键id
     * @param id
     * @return
     */
    @Override
    public ResultVO downloadPro(Integer id, HttpServletResponse response) {
        List<com.uchain.entity.File> files = fileMapper.selectByProId(id);
        if(!(files!=null && files.size()>0 && files.get(0)!=null)){
            return ResultVOUtil.error(ResultEnum.FILE_IS_NOT_EXIST);
        }
        String fileUrl = files.get(0).getFileUrl();
        log.info("项目下载路径："+fileUrl);
        /**
         * 创建临时文件夹
         */
        String proName = proBriefMapper.selectByPrimaryKey(id).getProName();
        File temDir = new File(linshiPath+proName);
        if (!temDir.exists()) {
            temDir.mkdirs();
        }
        /**
         * 生成需要下载的文件，存放入临时文件夹
         */
        try {
            ZipUtils.copyDir(fileUrl, linshiPath+proName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 设置response的header
         */
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=uchainfile.zip");
        /**
         * 4.调用工具类，下载zip压缩包
         */
        try {
            ZipUtils.toZip(temDir.getPath(), response.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 5.删除临时文件和文件夹
         */
        // 这里我没写递归，直接就这样删除了
        File[] listFiles = temDir.listFiles();
        if(listFiles.length==0){
            return ResultVOUtil.error(ResultEnum.FILE_IS_NOT_EXIST);
        }
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
            log.info("正在删除第"+(i+1)+"个文件");
        }
        temDir.delete();

        return null;
    }

    @Override
    @Transactional
    public ResultVO delProBrief(Integer id) {
        List<com.uchain.entity.File> files = fileMapper.selectByProId(id);
        if(!(files!=null && files.size()>0 && files.get(0)!=null)){
            return ResultVOUtil.error(ResultEnum.FILE_IS_NOT_EXIST);
        }
        String fileUrl = files.get(0).getFileUrl();
        log.info("项目下载路径："+realPath);

        File delPath = new File(fileUrl);

        File[] listFiles = delPath.listFiles();
        if(listFiles.length==0){
            return ResultVOUtil.error(ResultEnum.FILE_IS_NOT_EXIST);
        }
        for (int i =0;i<listFiles.length;i++){
            listFiles[i].delete();
            log.info("正在删除第"+(i+1)+"个文件");
        }
        delPath.delete();
        //删除file表中记录
        fileMapper.deleteByProId(id);
        //删除probrief表中记录
        proBriefMapper.deleteByPrimaryKey(id);
        return ResultVOUtil.success();
    }

    /**
     * 传分组id
     * @param typeId
     * @return
     */
    @Transactional
    @Override
    public ResultVO getGroupPro(String typeId) {
        List<ProBrief> proBriefs = proBriefMapper.selectByTypeId(typeId);
        return ResultVOUtil.success(proBriefs);
    }

    @Override
    public ResultVO getAllPro() {
        List<ProBrief> proBriefs = proBriefMapper.selectAll();
        return ResultVOUtil.success(proBriefs);
    }


}
