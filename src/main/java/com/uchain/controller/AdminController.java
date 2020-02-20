package com.uchain.controller;



import com.uchain.accessctro.RoleContro;
import com.uchain.enums.ResultEnum;
import com.uchain.enums.RoleEnum;
import com.uchain.form.UserRegisterForm;
import com.uchain.form.UserUpdateForm;
import com.uchain.service.UserService;
import com.uchain.util.ResultVOUtil;
import com.uchain.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @ClassName AdminController
 * @Description 管理员接口类
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Slf4j
@RequestMapping("/admin")
@RestController
@Api(tags = "管理员接口")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public Object addUser(@Valid UserRegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return userService.addUser(registerForm);
    }

    @GetMapping("/showAll")
    @ApiOperation("展示所有用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO showAll() {
        return userService.showAll();
    }

    @GetMapping("/showAllByGroup")
    @ApiOperation("按照分组展示所有用户展示所有用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO showAllByGroup() {
        return userService.showAllByGroup();
    }

    @PostMapping("/deleteUser")
    @ApiOperation("删除用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO deleteUser(@RequestParam("stuId") String stuId) {
        return userService.deleteUser(stuId);
    }

    @PostMapping("/updateUser")
    @ApiOperation("修改用户信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO updateUser(@Valid UserUpdateForm userUpdateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return userService.updateUser(userUpdateForm);
    }
}
