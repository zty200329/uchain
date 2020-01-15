package com.uchain.controller;

import com.uchain.accessctro.RoleContro;
import com.uchain.enums.RoleEnum;
import com.uchain.form.LoginForm;
import com.uchain.form.UserRegisterForm;
import com.uchain.form.UserSignatureForm;
import com.uchain.form.UserUpdatePwForm;
import com.uchain.service.UserService;
import com.uchain.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: zty
 * @Date: 2020/1/8 11:43
 */
@Slf4j
@RestController
@RequestMapping("/anon")
@Api(tags = "用户登录注册接口")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultVO login(@Valid LoginForm loginForm, HttpServletResponse response,
                          BindingResult bindingResult) {
        return userService.login(loginForm, response, bindingResult);
    }
    @PostMapping("/userRegister")
    @ApiOperation("用户注册")
    public Object userRegister(@Valid UserRegisterForm registerForm, BindingResult bindingResult) {
        return userService.addUser(registerForm,bindingResult);
    }
    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/updatePw")
    @ApiOperation("修改密码")
    public ResultVO updatePw(@Valid UserUpdatePwForm userUpdatePwForm,BindingResult bindingResult){
        return userService.updateUserPw(userUpdatePwForm,bindingResult);
    }
    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/uploadPhoto")
    @ApiOperation("上传自拍")
    public ResultVO uploadPhoto( @RequestParam("selfie") MultipartFile file){
        return userService.uploadPhoto(file);
    }
    @RoleContro(role = RoleEnum.USER)
    @PostMapping("/userSignature")
    @ApiOperation(("（发布）修改签名"))
    public ResultVO uploadUserSignature(@Valid UserSignatureForm userSignatureForm,BindingResult bindingResult){
        return userService.userSignature(userSignatureForm,bindingResult);
    }

}
