package com.ming.system.service.impl;

import com.ming.system.entity.Log;
import com.ming.system.mapper.LogMapper;
import com.ming.system.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;


    @Override
    public int insertLog(Log log) {
        return logMapper.insertLog(log);
    }
}
