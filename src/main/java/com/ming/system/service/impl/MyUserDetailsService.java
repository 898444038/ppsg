package com.ming.system.service.impl;

import com.ming.system.entity.Permisson;
import com.ming.system.entity.Role;
import com.ming.system.entity.User;
import com.ming.system.mapper.PermissionMapper;
import com.ming.system.mapper.RoleMapper;
import com.ming.system.mapper.UserMapper;
import com.ming.system.mapper.UserRoleMapper;
import com.ming.system.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        User user = userMapper.loadUserByUsername(username);
        if (null != user) {
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(roles);
        }
        return user;
    }

    public String login(String username, String password) throws UsernameNotFoundException {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    public List<Permisson> getResource() {
        return permissionMapper.getResource();
    }

    public int checkUsername(String username) {
        return userMapper.checkUsername(username);
    }

    @Transactional
    public synchronized int register(User user) {
        User u = userMapper.loadUserByUsername(user.getUsername());
        if(u!=null){
            return 0;
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreateTime(new Date());
        Random rand = new Random();
        int MAX = 8;
        int MIN = 0;
        int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;
        user.setHeadPortrait("/static/formwork/demo/img/profile-pics/"+randNumber+".jpg");
        user.setSkin("/static/formwork/img/bg/1.jpg");
        user.setCreateTime(new Date());
        user.setDisabled(false);
        user.setDelFlag(false);
        int i = userMapper.register(user);
        if(i>0){
            return userRoleMapper.insertUserRole(user.getId(),1L);
        }
        return 0;
    }
}
