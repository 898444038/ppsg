package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.User;
import com.ming.ppsg.mapper.UserMapper;
import com.ming.ppsg.service.UserService;
import com.ming.system.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Service
@Log
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUser() {
        return userMapper.select();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.delete(id);
    }
}
