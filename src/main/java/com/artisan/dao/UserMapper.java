package com.artisan.dao;

import com.artisan.common.utils.MyMapper;
import com.artisan.pojo.db.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MyMapper<User> {

    User getUser(@Param("userName") String userName, @Param("password") String password);
}