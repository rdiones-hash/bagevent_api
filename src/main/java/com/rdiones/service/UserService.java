package com.rdiones.service;

import com.rdiones.entity.User;
import com.rdiones.vo.Result;

import java.util.Map;

public interface UserService {

    Result Login(String cellphone,String password);

    Result saveUser(Map user);
}
