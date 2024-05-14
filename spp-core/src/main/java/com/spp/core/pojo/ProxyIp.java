package com.spp.core.pojo;


import java.io.Serializable;
import java.util.Date;


/**
 * @author zhougy
 * @date 2024/05/14
 */
public class ProxyIp implements Serializable {
    private static final long serialVersionUID = -1;

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

    public ProxyIp() {

    }
    public ProxyIp(String city, String ip, Integer port, Date expireTime) {
        this.city = city;
        this.ip = ip;
        this.port = port;
        this.expireTime = expireTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
