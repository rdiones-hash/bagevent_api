package com.rdiones.service.impl;

import com.rdiones.Repository.UserLoginLogRepository;
import com.rdiones.service.UserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserLoginLogServiceImpl implements UserLoginLogService {
    @Autowired
    private UserLoginLogRepository userLoginLogRepository;
}
