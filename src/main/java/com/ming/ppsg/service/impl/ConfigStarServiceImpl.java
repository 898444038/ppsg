package com.ming.ppsg.service.impl;

import com.ming.ppsg.entity.ConfigCountry;
import com.ming.ppsg.entity.ConfigStar;
import com.ming.ppsg.mapper.ConfigCountryMapper;
import com.ming.ppsg.mapper.ConfigStarMapper;
import com.ming.ppsg.service.ConfigCountryService;
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
@Log
@Transactional
public class ConfigStarServiceImpl implements ConfigStarService {

    @Resource
    private ConfigStarMapper configStarMapper;

    @Override
    public List<ConfigStar> select() {
        return configStarMapper.select();
    }
}
