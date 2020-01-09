package com.uchain.service.impl;

import com.uchain.dao.UserMapper;
import com.uchain.dto.PhotoUploadDTO;
import com.uchain.entity.User;
import com.uchain.enums.ResultEnum;
import com.uchain.form.LoginForm;
import com.uchain.form.UserRegisterForm;
import com.uchain.form.UserSignatureForm;
import com.uchain.form.UserUpdatePwForm;
import com.uchain.security.JwtProperties;
import com.uchain.security.JwtUserDetailServiceImpl;
import com.uchain.service.UserService;
import com.uchain.util.JwtTokenUtil;
import com.uchain.util.ResultVOUtil;
import com.uchain.util.UploadUtil;
import com.uchain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zty
 * @Date: 2020/1/8 10:19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;
    @Value("${img.location}")
    private static String imageFilePath;
    @Value("${img.url}")
    private static String imageUrl;
    @Override
    public ResultVO addUser(@Valid UserRegisterForm registerForm, BindingResult bindingResult) {
        String password = "123456";
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        boolean isHave = (userMapper.getUserByStuId(registerForm.getStuId()) != null);
        if (isHave){
            return ResultVOUtil.error(ResultEnum.USER_HAS_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(registerForm,user);
        user.setRole(0);
        user.setPassword(passwordEncoder.encode(password));
        log.info("用户信息" + user);
        int result = userMapper.insert(user);
        if (result != 1) {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String key = "anonymousUser";
        if (!userName.equals(key)) {
            return getUserByStuId(userName);
        }
        return null;
    }

    @Override
    public User getUserByStuId(String stuId) {
        return userMapper.getUserByStuId(stuId);
    }

    @Override
    public ResultVO userSignature(UserSignatureForm userSignatureForm,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        User user = getCurrentUser();
        user.setUserSignature(userSignatureForm.getUserSignature());
        if(update(user)){
            return  ResultVOUtil.success("修改签名成功");
        }else{
            System.out.println(user.toString());
            log.info("修改签名失败");
        }
        return ResultVOUtil.error(ResultEnum.UPLOAD_SIGNATURES_FAIL);
    }


    @Override
    public Boolean update(User user) {
        return (userMapper.updateByPrimaryKey(user) == 1);
    }


    /**
     *
     * @param userUpdatePwForm
     * @return
     */
    @Override
    public ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        User user = getCurrentUser();
        log.info("学号: "+ user.getStuId());
        if(!user.getStuId().equals(userUpdatePwForm.getStuId())){
            log.info("非本人操作,修改密码失败!");
            return ResultVOUtil.error(ResultEnum.IS_NOT_PERSONAL_OPERATION);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userUpdatePwForm.getStuId());
        if(!new BCryptPasswordEncoder().matches(userUpdatePwForm.getOldPassword(),userDetails.getPassword())){
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        User updateUser = userMapper.getUserByStuId(userUpdatePwForm.getStuId());
        updateUser.setPassword(new BCryptPasswordEncoder().encode(userUpdatePwForm.getNewPassword()));
        if(update(updateUser)){
            return  ResultVOUtil.success("修改密码成功");
        }else{
            System.out.println(updateUser.toString());
            log.info("修改密码失败");
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO uploadPhoto(MultipartFile file) {
        User user = getCurrentUser();
        log.info("开始上传图片");
        PhotoUploadDTO photoUploadDTO = uploadUtil.fileUpload(file);
        user.setUserPicture(photoUploadDTO.getPhotoUrl());
        update(user);
        return ResultVOUtil.success("自拍上传成功噢！！！");
    }

    /**
     * 登录
     * @param loginForm
     * @param response
     * @param bindingResult
     * @return
     */
    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        User user = userMapper.getUserByStuId(loginForm.getStuId());
        if(user == null){
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getStuId());
        if(!(new BCryptPasswordEncoder().matches(loginForm.getPassword(),userDetails.getPassword()))){
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getStuId(),loginForm.getPassword(),userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(),realToken);

        Map map = new HashMap();
        map.put("username",user.getUsername());
        map.put("role",user.getRole());
        map.put("token",realToken);

        return ResultVOUtil.success(map);
    }


}
