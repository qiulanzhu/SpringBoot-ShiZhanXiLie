package com.qiuyi.springdemo.dao.db1;

import com.qiuyi.springdemo.model.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NewsDao {
    @Select("select * from news")
    List<News> getAllNews();

    List<News> queryNewsAll();
}
