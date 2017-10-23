package com.artisan.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * spring bean 工厂
 * @author Leeyom Wang
 * @date 2017年10月19日 11:53
 */
public class SpringFactory implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.beanFactory = factory;
    }


    /**
     * 根据beanName名字取得bean
     * @param beanName 需要注入的bean的名字
     * @param <T>      类型
     * @return 需要注入实例
     */
    public static <T> T getBean(String beanName) {
        if (null != beanFactory) {
            return (T) beanFactory.getBean(beanName);
        }
        return null;
    }

}
