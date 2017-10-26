package com.artisan.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用的Service
 * @author leeyom
 */
@Service
public interface BaseService<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    int updateByPrimaryKey(T record);

}
