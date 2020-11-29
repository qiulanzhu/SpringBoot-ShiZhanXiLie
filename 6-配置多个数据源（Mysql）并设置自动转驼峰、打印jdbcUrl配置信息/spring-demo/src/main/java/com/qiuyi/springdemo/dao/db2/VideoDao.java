package com.qiuyi.springdemo.dao.db2;

import com.qiuyi.springdemo.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoDao {
    @Select("select * from video")
    List<Video> getAllVideos();

    List<Video> queryVideoAll();
}
