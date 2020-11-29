package com.qiuyi.springdemo;

import com.qiuyi.springdemo.dao.db1.NewsDao;
import com.qiuyi.springdemo.dao.db2.VideoDao;
import com.qiuyi.springdemo.model.News;
import com.qiuyi.springdemo.model.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import java.util.List;

@Slf4j
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class
})
public class SpringDemoApplication implements CommandLineRunner {

    @Autowired
    private NewsDao newsDao;
    @Autowired
    private VideoDao videoDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<News> newsList = newsDao.queryNewsAll();
        List<Video> videoList = videoDao.queryVideoAll();
        log.info(newsList.toString());
        log.info(videoList.toString());

        newsList = newsDao.queryNewsAll();
        videoList = videoDao.queryVideoAll();
        log.info(newsList.toString());
        log.info(videoList.toString());
    }
}
