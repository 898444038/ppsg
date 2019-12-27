package com.ming.ppsg.mapper;

import com.ming.ppsg.entity.ConfigCountry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Mapper
public interface ConfigCountryMapper {

    List<ConfigCountry> select();


}
