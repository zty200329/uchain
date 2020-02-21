package com.uchain.service.impl;


import com.uchain.dao.GroupMapper;
import com.uchain.dao.UserMapper;
import com.uchain.dto.PhotoUploadDTO;
import com.uchain.entity.Group;
import com.uchain.entity.User;
import com.uchain.enums.GroupEnum;
import com.uchain.enums.ResultEnum;
import com.uchain.form.*;
import com.uchain.security.JwtProperties;
import com.uchain.security.JwtUserDetailServiceImpl;
import com.uchain.service.UserService;
import com.uchain.util.JwtTokenUtil;
import com.uchain.util.RedisUtil;
import com.uchain.util.ResultVOUtil;
import com.uchain.util.UploadUtil;
import com.uchain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description UserService实现类
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GroupMapper groupMapper;

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

    @Autowired
    private UploadUtil uploadUtil;

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
        return userMapper.selectByPrimaryKey(stuId);
    }

    /**
     * Security自带的
     *
     * @param loginForm
     * @param response
     * @return
     */
    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        User user = userMapper.selectByPrimaryKey(loginForm.getStuId());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getStuId());
        if (!(new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword()))) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getStuId(), loginForm.getPassword(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);

        Map map = new HashMap();
        map.put("username", user.getUserName());
        map.put("role", user.getRole());
        map.put("token", realToken);

        return ResultVOUtil.success(map);
    }

    @Override
    public ResultVO showAll() {
        List<User> users;
        users = userMapper.selectAll();
        int i = 1;
        List<AllUserVO> allUserVOS = new ArrayList<>();
        for (User user : users) {
            AllUserVO allUserVO = new AllUserVO();
            BeanUtils.copyProperties(user, allUserVO);
            allUserVO.setGroupType(GroupEnum.getGroup(user.getGroupId()));
            allUserVO.setKey(i);
            i++;
            allUserVOS.add(allUserVO);
        }
        return ResultVOUtil.success(allUserVOS);
    }


    @Override
    public ResultVO addUser(UserRegisterForm registerForm) {

        boolean isHave = (userMapper.selectByPrimaryKey(registerForm.getStuId()) != null);
        if (isHave) {
            return ResultVOUtil.error(ResultEnum.USER_ALREADY_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(registerForm, user);
        user.setGroupId(GroupEnum.getValue(registerForm.getGroupType()));
        user.setRole(0);
        user.setPassword(passwordEncoder.encode(jwtProperties.getDefaultPassword()));
        log.info("用户信息" + user);
        int result = userMapper.insert(user);
        if (result != 1) {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm) {

        User user = getCurrentUser();
        log.info("学号: " + user.getStuId());
        if (!user.getStuId().equals(userUpdatePwForm.getStuId())) {
            log.info("非本人操作,修改密码失败!");
            return ResultVOUtil.error(ResultEnum.IS_NOT_PERSONAL_OPERATION);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userUpdatePwForm.getStuId());
        if (!new BCryptPasswordEncoder().matches(userUpdatePwForm.getOldPassword(), userDetails.getPassword())) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        User updateUser = userMapper.selectByPrimaryKey(userUpdatePwForm.getStuId());
        updateUser.setPassword(new BCryptPasswordEncoder().encode(userUpdatePwForm.getNewPassword()));
        if (update(updateUser)) {
            return ResultVOUtil.success("修改密码成功");
        } else {
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
        user.setHeadPictureUrl(photoUploadDTO.getPhotoUrl());
        update(user);
        return ResultVOUtil.success("自拍上传成功噢！！！");
    }

    @Override
    public ResultVO deleteUser(String stuId) {
        if (userMapper.deleteByPrimaryKey(stuId) != 1) {
            return ResultVOUtil.error(ResultEnum.USER_DELETE_FAILED);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUser(UserUpdateForm userUpdateForm) {

        if (userMapper.selectByPrimaryKey(userUpdateForm.getStuId()) == null) {
            log.info("此用户不存在！");
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        User user = userMapper.selectByPrimaryKey(userUpdateForm.getStuId());
        user.setUserName(userUpdateForm.getUserName());
        user.setGroupId(GroupEnum.getValue(userUpdateForm.getGroupType()));
        update(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUserSignature(UserSignatureForm userSignatureForm) {
        User user = getCurrentUser();
        user.setUserSignature(userSignatureForm.getUserSignature());
        update(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUserDescription(UserDescriptionForm userDescriptionForm) {
        User user = getCurrentUser();
        user.setUserDesc(userDescriptionForm.getUserDesc());
        update(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO selfInformation() {
        User user = getCurrentUser();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setGroupType(GroupEnum.getGroup(user.getGroupId()));
        return ResultVOUtil.success(userVO);
    }

    @Override
    public ResultVO showAllByGroup() {
        List<GroupUserVO> groupUserVOList = new ArrayList<>();
        List<Group> groupList = groupMapper.selectAll();
        for (Group group : groupList) {
            GroupUserVO groupUserVO = new GroupUserVO();
            BeanUtils.copyProperties(group, groupUserVO);
            List<User> userList = new ArrayList<>();
            userList = userMapper.selectByGroupId(group.getGroupId());
            List<AllUserVO> allUserVOS = new ArrayList<>();
            for (User user : userList) {
                AllUserVO allUserVO = new AllUserVO();
                BeanUtils.copyProperties(user, allUserVO);
                allUserVO.setGroupType(GroupEnum.getGroup(user.getGroupId()));
                allUserVOS.add(allUserVO);
            }
            groupUserVO.setAllUserVOS(allUserVOS);
            groupUserVOList.add(groupUserVO);
        }
        return ResultVOUtil.success(groupUserVOList);
    }

    @Override
    public ResultVO updateRole(String stuId) {
        User user = userMapper.selectByPrimaryKey(stuId);
        if(user.getRole().equals(0)){
            user.setRole(1);
        }else{
            user.setRole(0);
        }
        userMapper.updateByPrimaryKey(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO showUsers() {
        List<User> users;
        users = userMapper.selectAll();
        int i = 1;
        List<ShowUserVO> showUserVOS = new ArrayList<>();
        for (User user : users) {
            ShowUserVO showUserVO = new ShowUserVO();
            BeanUtils.copyProperties(user, showUserVO);
            showUserVO.setGroupType(GroupEnum.getGroup(user.getGroupId()));
            showUserVO.setKey(i);
            i++;
            showUserVOS.add(showUserVO);
        }
        return ResultVOUtil.success(showUserVOS);
    }

    @Override
    public Boolean update(User user) {
        return (userMapper.updateByPrimaryKey(user) == 1);
    }

}
