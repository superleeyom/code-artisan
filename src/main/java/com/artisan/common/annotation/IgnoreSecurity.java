package com.artisan.common.annotation;

import java.lang.annotation.*;


/**
 * 自定义注解，标识是否忽略REST安全性检查
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {

}
