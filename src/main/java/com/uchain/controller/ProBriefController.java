package com.uchain.controller;

import com.uchain.accessctro.RoleContro;
import com.uchain.enums.RoleEnum;
import com.uchain.form.ProBriefForm;
import com.uchain.service.ProBriefService;
import com.uchain.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @Author: zty
 * @Date: 2020/1/10 22:24
 */
@Slf4j
@RestController
@RequestMapping("/probrief")
@Api(tags = "项目管理接口")
@CrossOrigin
public class ProBriefController {
    @Autowired
    private ProBriefService proBriefService;

    @RoleContro(role = RoleEnum.ADMIN)
    @PostMapping("/uploadPro")
    @ApiOperation(("上传项目"))
    public ResultVO uploadProBiref(@Valid ProBriefForm proBriefForm, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult){
        System.out.println(files);
        return proBriefService.uploadProBrief(proBriefForm,files,bindingResult);
    }

}
