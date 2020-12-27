package com.qiuyi.springdemo.dao;

import com.qiuyi.springdemo.model.Coffee;
import java.util.List;

public interface CoffeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coffee record);

    Coffee selectByPrimaryKey(Long id);

    List<Coffee> selectAll();

    int updateByPrimaryKey(Coffee record);
}