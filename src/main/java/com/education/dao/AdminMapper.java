package com.education.dao;

import com.education.pojo.Admin;

import java.util.List;

public interface AdminMapper {

    List<Admin> login(Admin admin);

    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}