package com.artisan.authorization.manager;

import com.artisan.authorization.model.TokenModel;

/**
 * 对Token进行操作的接口
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    TokenModel createToken(long userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    void deleteToken(long userId);

    /**
     * 保证一个用户在一个时间段只有一个可用 Token
     * @param userId
     * @return
     */
    boolean hasToken(long userId);
}
