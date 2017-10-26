package com.artisan.common.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承通用的Mapper，方便后期自己的扩展
 * @author Leeyom Wang
 * @date 2017年10月26日 11:25
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
