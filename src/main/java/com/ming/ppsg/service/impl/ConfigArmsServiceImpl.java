package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.ConfigArms;
import com.ming.ppsg.entity.ConfigStar;
import com.ming.ppsg.mapper.ConfigArmsMapper;
import com.ming.ppsg.mapper.ConfigStarMapper;
import com.ming.ppsg.service.ConfigArmsService;
import com.ming.ppsg.service.ConfigStarService;
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
public class ConfigArmsServiceImpl implements ConfigArmsService {

    @Resource
    private ConfigArmsMapper configArmsMapper;

    @Override
    public List<ConfigArms> select() {
        return configArmsMapper.select();
    }
}
