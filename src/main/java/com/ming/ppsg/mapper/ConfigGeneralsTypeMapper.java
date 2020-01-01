package com.ming.ppsg.mapper;

import com.ming.ppsg.entity.ConfigGeneralsType;
import com.ming.ppsg.entity.ConfigStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Mapper
public interface ConfigGeneralsTypeMapper {

    //@Select("SELECT id,generals_type_name as generalsTypeName,force_growth AS forceGrowth,intellect_growth AS intellectGrowth,troops_growth AS troopsGrowth FROM ppsg_config_generals_type order by id")
    List<ConfigGeneralsType> select();


}
