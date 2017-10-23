package com.artisan.common.aspect;

import com.artisan.authorization.manager.TokenManager;
import com.artisan.common.annotation.IgnoreSecurity;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        LOGGER.debug("methodSignature : " + methodSignature);
        Method method = methodSignature.getMethod();
        LOGGER.debug("Method : " + method.getName() + " : " + method.isAnnotationPresent(IgnoreSecurity.class));

        //如果是获取Swagger API文档，直接调用目标方法，生产环境下需要注释掉
        /*String methodName = method.getName();
        if ("getDocumentation".equals(methodName)) {
            return pjp.proceed();
        }

        // 若目标方法忽略了安全性检查,则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }

        // 从 request header 中获取当前 token
        String authentication = WebContextUtil.getRequest().getHeader(Constants.DEFAULT_TOKEN_NAME);
        TokenModel tokenModel = tokenManager.getToken(authentication);
        // 检查 token 有效性
        if (!tokenManager.checkToken(tokenModel)) {
            String message = "token " + authentication + " is invalid！！！";
            LOGGER.debug("message : " + message);
            throw new TokenException(message);
        }*/
        // 调用目标方法
        return pjp.proceed();
    }
}
