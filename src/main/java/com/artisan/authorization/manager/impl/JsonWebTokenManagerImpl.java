package com.artisan.authorization.manager.impl;

import com.artisan.authorization.manager.JsonWebTokenManager;
import com.artisan.common.constant.Constants;
import com.artisan.common.utils.StringUtils;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class JsonWebTokenManagerImpl implements JsonWebTokenManager {


    @Override
    public String createToken(long userId) {

        // JWT的有效期为两个小时
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.HOUR_OF_DAY, 2);

        // 签发 token
        String token = Jwts.builder()
                // JWT 的签发者
                .setIssuer("leeyom.com")
                // 主题
                .setSubject("Test JWT")
                // JWT 的接收方
                .setAudience("client.leeyom.com")
                // 过期时间
                .setExpiration(ca.getTime())
                // JWT 的签发时间
                .setIssuedAt(new Date())
                // 载荷中自定义数据
                .claim("userId", userId + "")
                // JWT的唯一标识
                .setId(UUID.randomUUID().toString())
                // 签名
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY)
                .compact();
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        try {
            // 解析 JWT 字符串中的数据，并进行最基础的验证（可选），比如接收方是否是我，userId 不能为 null 等等
            Claims claims = Jwts.parser()
                    .setSigningKey(Constants.SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            String userId = claims.get("userId", String.class);
            return StringUtils.notNull(userId);

        } catch (SignatureException | ExpiredJwtException e) {
            // 如果密钥不正确，将会解析失败，抛出 SignatureException 异常，说明该 JWT 字符串是伪造的
            // 如果‘过期时间字段’已经早于当前时间，将会抛出 ExpiredJwtException 异常，说明本次请求已经失效
            return false;
        }
    }

}
