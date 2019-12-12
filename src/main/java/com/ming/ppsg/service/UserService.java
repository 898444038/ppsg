package com.ming.ppsg.service;

import com.ming.ppsg.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public interface UserService {

    List<User> selectUser();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);
}
