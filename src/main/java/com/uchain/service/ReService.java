package com.uchain.service;

import com.uchain.form.ReForm;
import com.uchain.vo.ResultVO;
import org.springframework.validation.BindingResult;

/**
 * @author czy
 * @date 2020/2/1 - 15:37
 * @Classname
 * @Description
 */
public interface ReService {

    /**
     * 添加资源
     * @param reForm  资源表单
     * @return
     */
    public ResultVO addResource(ReForm reForm, BindingResult bindingResult);

    /**
     * 删除资源
     * @param reId
     * @return
     */
    public ResultVO deleteResource(Integer reId);

    /**
     * 更新资源
     * @param reForm
     * @return
     */
    public ResultVO updateResource(Integer id, ReForm reForm, BindingResult bindingResult);


//    /**
//     * 根据学生学号查询资源
//     * @param stuId 学生学号
//     * @return TODO 已经作废
//     */
//    public List<Contribute> queryReByStuId(Integer stuId);

    /**
     * 根据学生姓名模糊查询资源
     * @param stuName
     * @return
     */
    public ResultVO queryReByStuName(String stuName);

    /**
     * 根据方向查询
     * @param groupId
     * @return
     */
    public ResultVO queryReByGroupId(Integer groupId);


    /**
     * 根据标签查询
     * @param tagId
     * @return
     */
    public ResultVO queryReByTagId(Integer tagId);

    /**
     * 添加根据资源名称模糊查询方法
     * @param name
     * @return
     */
    public ResultVO queryReByName(String name);


}
