package com.rdiones.service.impl;

import com.rdiones.Repository.UserLoginLogRepository;
import com.rdiones.Repository.UserRepository;
import com.rdiones.constant.AdminConstant;
import com.rdiones.entity.User;
import com.rdiones.entity.UserLoginLog;
import com.rdiones.service.UserService;
import com.rdiones.shiro.EncryptUtils;
import com.rdiones.vo.Result;
import com.rdiones.vo.ResultUtil;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginLogRepository userLoginLogRepository;

    @Override
    public Result Login(String cellphone, String password) {
        User user = userRepository.getUserByCellphone(cellphone);
        if (null == user) {
            return ResultUtil.error("不存在该用户");
        }
        System.out.println(EncryptUtils.encrypt(user.getCellphone(), password, user.getSalt()));
        if (!EncryptUtils.encrypt(user.getCellphone(), password, user.getSalt()).equals(user.getPassword())) {
            return ResultUtil.error("密码错误");
        }
        if (user.getState() == 1) {
            Map domain = new HashMap(16);
            domain.put(AdminConstant.PK_ID, user.getUserId());
            domain.put(AdminConstant.PHONE, user.getCellphone());
            domain.put(AdminConstant.TIME, System.currentTimeMillis());
            //登录log
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setLoginIp("0.0.0.0");
            userLoginLog.setLoginTime(new Date());
            userLoginLog.setUserId(user.getUserId());
            userLoginLogRepository.save(userLoginLog);
            //登录用户信息返回
            Map resultMap = new HashMap(16);
            return ResultUtil.success(resultMap);
        } else {
            return ResultUtil.error("该用户已封禁");
        }
    }

    @Override
    public Result saveUser(Map user) {
        User newUser = new User();
        User oldUser = userRepository.getUserByCellphone(String.valueOf(user.get("user")));
        if (null != oldUser){
            return ResultUtil.error("该账号已注册");
        }
        newUser.setUserName(String.valueOf(user.get("user")));
        newUser.setCellphone(String.valueOf(user.get("user")));
        newUser.setEmail("");
        newUser.setSalt(EncryptUtils.createSalt());
        newUser.setPassword(EncryptUtils.encrypt(String.valueOf(user.get("user")), String.valueOf(user.get("pwd")), newUser.getSalt()));
        newUser.setState(1);
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());
        userRepository.save(newUser);

        return ResultUtil.successWithMessage("注册成功");

    }
}
