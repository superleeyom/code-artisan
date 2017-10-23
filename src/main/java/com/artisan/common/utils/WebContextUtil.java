package com.artisan.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Web上下文工具类
 * @author Leeyom Wang
 * @date 2017年10月19日 11:51
 */
public class WebContextUtil {

    /**
     * 获取HTTP请求
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}
