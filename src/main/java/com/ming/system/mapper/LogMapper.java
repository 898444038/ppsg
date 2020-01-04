package com.ming.system.mapper;

import com.ming.system.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
@Mapper
public interface LogMapper {

    @Insert({ "INSERT INTO s_log(`user_id`, `username`, `ip`, `start_time`, `end_time`, `time`, `method`, `args`, `remark`, `result`, `type`, `mapping`) VALUES (#{userId},#{username},#{ip},#{startTime},#{endTime},#{time},#{method},#{args},#{remark},#{result},#{type},#{mapping})" })
    int insertLog(Log log);

}