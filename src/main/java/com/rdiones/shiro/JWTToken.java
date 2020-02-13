package com.rdiones.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Company: Rdiones
 * @Author: Rdiones
 * @Description:
 * @Date: 2020/2/12 上午11:17
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return getPrincipal();
    }
}
