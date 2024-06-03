package com.spp.crawler.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理ip
 *
 * @author zhougy
 * @date 2024/06/03
 */
@Data
@TableName("proxy_ip")
public class ProxyIpDO implements Serializable {
    private static final long serialVersionUID = -1;

    /**
     * id
     */
    private Long id;

    /**
     * city
     */
    private String city;

    /**
     * ip
     */
    private String ip;

    /**
     * port
     */
    private Integer port;

    /**
     * expireTime
     */
    private Date expireTime;

}

