package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.ConfigCountry;
import com.ming.ppsg.entity.ConfigWarDevice;
import com.ming.ppsg.mapper.ConfigCountryMapper;
import com.ming.ppsg.mapper.ConfigWarDeviceMapper;
import com.ming.ppsg.service.ConfigWarDeviceService;
import com.ming.system.annotation.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Service
@Transactional
public class ConfigWarDeviceServiceImpl implements ConfigWarDeviceService {

    @Resource
    private ConfigWarDeviceMapper configWarDeviceMapper;

    @Override
    public List<ConfigWarDevice> select() {
        return configWarDeviceMapper.select();
    }
}
