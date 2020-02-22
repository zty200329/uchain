package com.uchain.controller;

import com.uchain.accessctro.RoleContro;
import com.uchain.enums.RoleEnum;
import com.uchain.form.ReForm;
import com.uchain.service.impl.ReServiceImpl;
import com.uchain.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author czy
 * @date 2020/2/1 - 16:39
 * @Classname
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/re")
@Api(tags = "资源管理接口")
@CrossOrigin
public class ReController {
    @Autowired
    private ReServiceImpl reService;

    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/addRe")
    @ApiOperation("上传资源")
    public ResultVO addResource(@Valid ReForm reForm, BindingResult bindingResult){
        return reService.addResource(reForm, bindingResult);
    }


    @RoleContro(role = RoleEnum.ADMIN)
    @PostMapping("/deleteRe")
    @ApiOperation("删除资源")
    public ResultVO deleteResource(Integer reId){
        return reService.deleteResource(reId);
    }

    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/updateRe")
    @ApiOperation("更新资源")
    public ResultVO updateResource(Integer id, @Valid ReForm reForm, BindingResult bindingResult){
        return reService.updateResource(id, reForm, bindingResult);
    }

    /**
     * 不需要
     * @param stuName
     * @return
     */
    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/queryReByStuName")
    @ApiOperation("根据学生姓名查询资源")
    public ResultVO queryReByStuName(String stuName){
        return reService.queryReByStuName(stuName);
    }

    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/queryReByGroupId")
    @ApiOperation("根据方向查询资源")
    public ResultVO queryReByGroupId(Integer groupId){
        return reService.queryReByGroupId(groupId);
    }
    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/queryReByTagId")
    @ApiOperation("根据分类查询资源")
    public ResultVO queryReByTagId(Integer tagId){
        return reService.queryReByTagId(tagId);
    }

    /**
     * 不需要
     * @param name
     * @return
     */
    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/queryReByName")
    @ApiOperation("根据资源名称查询资源")
    public ResultVO queryReByName(String name){
        return reService.queryReByName(name);
    }

    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/queryReById")
    @ApiOperation("根据id查询资源")
    public ResultVO queryReById(Integer conId){
        return reService.queryReById(conId);
    }
}
