package com.artisan.service;

import com.artisan.pojo.db.User;

/**
 * User的service接口层
 * @author Leeyom Wang
 * @date 2017年10月26日 15:14
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据用户名和密码获取用户信息
     * @param userName
     * @param password
     * @return
     */
    User getUser(String userName, String password);
}
