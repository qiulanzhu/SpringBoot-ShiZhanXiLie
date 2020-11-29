use db_second;
DROP TABLE IF EXISTS video;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='视频表';
