package com.education.service;

import com.education.dao.AdminMapper;
import com.education.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    /**
     * 登录方法
     * @param admin
     * @return
     */
    public List<Admin> login(Admin admin) {
        return adminMapper.login(admin);
    }

}
