use spp;
CREATE TABLE `proxy_ip` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `city` varchar(255) NULL COMMENT '城市',
    `ip` varchar(255) NOT NULL COMMENT 'ip',
    `port` int NOT NULL COMMENT '端口',
    `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB COMMENT='代理ip表';