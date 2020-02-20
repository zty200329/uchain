package com.uchain.controller;


import com.uchain.enums.ResultEnum;
import com.uchain.form.LoginForm;
import com.uchain.service.UserService;
import com.uchain.util.ResultVOUtil;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ClassName AnonController
 * @Description 登录接口
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/anon")
@Api(tags = "登录接口")
@CrossOrigin
public class AnonController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultVO login(@Valid LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return userService.login(loginForm, response);
    }
}
