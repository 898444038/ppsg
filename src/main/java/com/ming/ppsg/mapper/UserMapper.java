package com.ming.ppsg.mapper;

import com.ming.ppsg.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
@Mapper
public interface UserMapper {

    List<User> select();

    int insert(User user);

    int update(User user);

    int delete(Long id);
}
