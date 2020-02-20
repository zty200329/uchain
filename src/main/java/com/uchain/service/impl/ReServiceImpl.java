package com.uchain.service.impl;

import com.uchain.dao.ContributeMapper;
import com.uchain.entity.Contribute;
import com.uchain.enums.ResultEnum;
import com.uchain.form.ReForm;
import com.uchain.service.ReService;
import com.uchain.util.ResultVOUtil;
import com.uchain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author czy
 * @date 2020/2/2 - 23:43
 * @Classname
 * @Description
 */
@Service
@Slf4j
public class ReServiceImpl implements ReService {

    @Autowired
    private ContributeMapper contributeMapper;

    public Contribute transfer(ReForm reForm){
        Contribute contribute = new Contribute();
        contribute.setGroupId(reForm.getGroupId());
        contribute.setReBrief(reForm.getReBrief());
        contribute.setReName(reForm.getReName());
        contribute.setReTag(reForm.getReTag());
        contribute.setReUrl(reForm.getReUrl());
        contribute.setStuId(reForm.getStuId());
        contribute.setStuName(reForm.getStuName());
        return contribute;
    }

    @Override
    public ResultVO addResource(ReForm reForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        if(contributeMapper.insert(transfer(reForm)) == 1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.RESOURCE_ADD_FAILED);
    }

    /**
     * @param reId
     * @return 返回成功信息/失败信息
     */
    @Override
    public ResultVO deleteResource(Integer reId) {
        if(contributeMapper.deleteByPrimaryKey(reId) == 1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.RESOURCE_DELETE_FAILED);
    }

    @Override
    public ResultVO updateResource(Integer id, ReForm reForm
    , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        Contribute contribute = transfer(reForm);
        contribute.setConId(id);
        log.info("更新资源",contribute);
        if(contributeMapper.updateByPrimaryKey(contribute) == 1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.RESOURCE_UPDATE_FAILED);
    }

    @Override
    public ResultVO queryReByStuName(String stuName) {
        List<Contribute> contributeList = contributeMapper.queryByName(stuName);
        return ResultVOUtil.success(contributeList);
    }

    @Override
    public ResultVO queryReByGroupId(Integer groupId) {
        List<Contribute> contributeList = contributeMapper.queryByGroupId(groupId);
        return ResultVOUtil.success(contributeList);
    }

    @Override
    public ResultVO queryReByTagId(Integer tagId) {
        List<Contribute> contributeList = contributeMapper.queryByTagId(tagId);
        return ResultVOUtil.success(contributeList);
    }

    @Override
    public ResultVO queryReByName(String name) {
        List<Contribute> contributeList = contributeMapper.queryByReName(name);
        return ResultVOUtil.success(contributeList);
    }
}

