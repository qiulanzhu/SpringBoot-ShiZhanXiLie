package com.qiuyi.springdemo.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class Video {
    private Long id;
    private String title;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
