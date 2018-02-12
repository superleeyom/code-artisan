package com.artisan.common.aspect;

import com.artisan.authorization.manager.TokenManager;
import com.artisan.authorization.model.TokenModel;
import com.artisan.common.annotation.IgnoreSecurity;
import com.artisan.common.constant.Constants;
import com.artisan.common.exception.TokenException;
import com.artisan.common.utils.Base64Util;
import com.artisan.common.utils.WebContextUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;


/**
 * token有效性判断切面
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
@Component
@Aspect
public class SecurityAspect {

    private static final Logger LOGGER = Logger.getLogger(SecurityAspect.class);

    @Autowired
    TokenManager tokenManager;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        // *******************************放行swagger相关的请求url，开发阶段打开，生产环境注释掉*******************************

        HttpServletRequest request = WebContextUtil.getRequest();
        URL requestUrl = new URL(request.getRequestURL().toString());
        if (requestUrl.getPath().contains("configuration")) {
            return pjp.proceed();
        }
        if (requestUrl.getPath().contains("swagger")) {
            return pjp.proceed();
        }
        if (requestUrl.getPath().contains("api")) {
            return pjp.proceed();
        }
        // ************************************************************************************************************

        // 若目标方法忽略了安全性检查,则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }

        // 从 request header 中获取当前 token
        String authentication = request.getHeader(Constants.DEFAULT_TOKEN_NAME);
        TokenModel tokenModel = tokenManager.getToken(Base64Util.decodeData(authentication));


        // 检查 token 有效性(检查是否登录)
        if (!tokenManager.checkToken(tokenModel)) {
            String message = "token " + Base64Util.decodeData(authentication) + " is invalid！！！";
            LOGGER.debug("message : " + message);
            throw new TokenException(message);
        }

        // 调用目标方法
        return pjp.proceed();
    }
}
