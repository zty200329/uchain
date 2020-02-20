package com.uchain.service;


import com.uchain.entity.User;
import com.uchain.form.*;
import com.uchain.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserService
 * @Description 操作用户
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

public interface UserService {

    /**
     * 增加用户
     *
     * @param registerForm
     * @return
     */
    ResultVO addUser(UserRegisterForm registerForm);

    /**
     * 获取当前用户
     *
     * @return
     */
    User getCurrentUser();

    /**
     * 通过用户名获取用户
     *
     * @param stuId
     * @return
     */
    User getUserByStuId(String stuId);

    /**
     * 登录
     *
     * @param loginForm
     * @param response
     * @return
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response);

    /**
     * 展示所有用户
     *
     * @return
     */
    ResultVO showAll();

    /**
     * 更新密码
     *
     * @param userUpdatePwForm
     * @return
     */
    ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm);

    /**
     * 更新操作
     *
     * @param user
     * @return
     */
    Boolean update(User user);

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    ResultVO uploadPhoto(MultipartFile file);

    /**
     * 删除用户
     *
     * @param stuId
     * @return
     */
    ResultVO deleteUser(String stuId);

    /**
     * 更新个人签名和个人简介
     *
     * @param userUpdateForm
     * @return
     */
    ResultVO updateUser(UserUpdateForm userUpdateForm);


    /**
     * 更新个人签名
     *
     * @param userSignatureForm
     * @return
     */
    ResultVO updateUserSignature(UserSignatureForm userSignatureForm);

    /**
     * 更新个人签名
     *
     * @param userDescriptionForm
     * @return
     */
    ResultVO updateUserDescription(UserDescriptionForm userDescriptionForm);

    /**
     * 查看个人信息
     *
     * @return
     */
    ResultVO selfInformation();

    /**
     * 分小组展示
     *
     * @return
     */
    ResultVO showAllByGroup();

    /**
     * 修改用户权限
     *
     * @param stuId
     * @return
     */
    ResultVO updateRole(String stuId);

    /**
     * 首页展示所有的用户
     *
     * @return
     */
    ResultVO showUsers();

}
