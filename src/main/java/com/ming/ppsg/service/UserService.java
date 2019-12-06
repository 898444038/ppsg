package com.ming.ppsg.service;

import com.ming.ppsg.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public interface UserService {

    List<User> select();

    int insert(User user);

    int update(User user);

    int delete(Long id);
}
