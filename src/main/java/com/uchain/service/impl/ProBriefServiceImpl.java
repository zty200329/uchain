package com.uchain.service.impl;

import com.uchain.dao.FileMapper;
import com.uchain.dao.ProBriefMapper;
import com.uchain.entity.ProBrief;
import com.uchain.enums.ResultEnum;
import com.uchain.form.ProBriefForm;
import com.uchain.service.ProBriefService;
import com.uchain.util.ResultVOUtil;
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.Arrays;
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
    @Override
    @Transactional
    public ResultVO uploadProBrief(ProBriefForm proBriefForm, MultipartFile[] files, BindingResult bindingResult) {
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

        List<MultipartFile>multipartFiles = Arrays.asList(files);
        MultipartFile file = null;
        for (MultipartFile multipartFile : multipartFiles) {
            file = multipartFile;
            if(!file.isEmpty()){
                try {
                    com.uchain.entity.File multipartfile = new com.uchain.entity.File();
                    //文件名
                    String fileName = file.getOriginalFilename();
                    log.info("上传文件名："+fileName);
                    FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath+proBriefForm.getProName()+"\\"+fileName));
                    multipartfile.setFileName(fileName);
                    multipartfile.setFileTypeId(proBriefForm.getFileType());
                    multipartfile.setFileUrl(realPath+proBriefForm.getProName()+fileName);
                    multipartfile.setProId(proId);
                    fileMapper.insert(multipartfile);
                }catch (Exception e){
                    return ResultVOUtil.error(ResultEnum.UPLOAD_FILE);
                }
            }
        }
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


}
