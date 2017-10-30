package com.artisan.service.impl;

import com.artisan.dao.UserMapper;
import com.artisan.pojo.db.User;
import com.artisan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IUserService接口实现类
 * @author Leeyom Wang
 * @date 2017年10月26日 15:15
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据用户名和密码获取用户信息
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User getUser(String userName, String password) {
        return userMapper.getUser(userName,password);
    }
}
