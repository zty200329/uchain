package com.uchain.service;

import com.uchain.entity.User;
import com.uchain.form.LoginForm;
import com.uchain.form.UserRegisterForm;
import com.uchain.form.UserSignatureForm;
import com.uchain.form.UserUpdatePwForm;
import com.uchain.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: zty
 * @Date: 2020/1/7 18:24
 */
public interface UserService {

    /**
     * 管理员添加普通用户
     * @param registerForm
     * @param bindingResult
     * @return
     */
    ResultVO addUser(UserRegisterForm registerForm,  BindingResult bindingResult);

    /**
     * 从token中解析用户
     * @return
     */
    User getCurrentUser();

    /**
     * 根据学号获取用户
     * @param stuId
     * @return
     */
    User getUserByStuId(String stuId);

    /**
     * 上传（修改个性签名）
     * @param userSignatureForm
     * @return
     */
    ResultVO userSignature(UserSignatureForm userSignatureForm,BindingResult bindingResult);

    /**
     * 更新操作
     * @param user
     * @return
     */
    Boolean update(User user);
    /**
     * 修改密码
     * @param userUpdatePwForm
     * @return
     */
    ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm,BindingResult bindingResult);

    /**
     * 自拍上传接口
     * @param file
     * @return
     */
    ResultVO uploadPhoto(MultipartFile file);
    /**
     * 登录
     * @param loginForm
     * @param response
     * @param bindingResult
     * @return
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response, BindingResult bindingResult);

    /**
     * 许多功能没有写上去
     */
}
