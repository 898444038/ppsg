package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.ConfigGeneralsType;
import com.ming.ppsg.entity.ConfigStar;
import com.ming.ppsg.mapper.ConfigGeneralsTypeMapper;
import com.ming.ppsg.mapper.ConfigStarMapper;
import com.ming.ppsg.service.ConfigGeneralsTypeService;
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
public class ConfigGeneralsTypeServiceImpl implements ConfigGeneralsTypeService {

    @Resource
    private ConfigGeneralsTypeMapper configGeneralsTypeMapper;

    @Override
    public List<ConfigGeneralsType> select() {
        return configGeneralsTypeMapper.select();
    }
}
