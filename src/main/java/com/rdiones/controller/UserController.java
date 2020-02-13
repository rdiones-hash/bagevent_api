package com.rdiones.controller;

import com.rdiones.entity.User;
import com.rdiones.service.UserService;
import com.rdiones.vo.Result;
import com.rdiones.vo.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/user/info",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result info(){
        return ResultUtil.successWithMessage("success");
    }
    @RequestMapping(value="/user/register",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result saveUser(@RequestBody Map posMap){
        return userService.saveUser(posMap);
    }
    @RequestMapping(value="/user/login",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result login(@RequestBody Map posMap){
        return userService.Login(String.valueOf(posMap.get("phone")),String.valueOf(posMap.get("password")));
    }
}
