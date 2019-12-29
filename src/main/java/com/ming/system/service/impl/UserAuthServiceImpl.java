package com.ming.system.service.impl;

import com.ming.system.service.UserAuthService;
import com.ming.system.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Service
@Transactional
public class UserAuthServiceImpl implements UserAuthService {

    //@Autowired
    //private AuthenticationManager authenticationManager;

    //@Autowired
    //private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        //UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        //Authentication authentication = authenticationManager.authenticate(upToken);
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = null;//jwtTokenUtil.generateToken(userDetails);
        return token;
    }



}