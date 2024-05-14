
package com.spp.core.pojo;


import com.spp.core.annotation.ProxyIpCrawler;
import java.io.Serializable;
import java.lang.reflect.Method;


/**
 * @author zhougy
 * @date 2024/05/14
 */
public class ProxyIpCrawlerContext implements Serializable {
    private static final long serialVersionUID = -1;

    private Object bean;
    private Method method;

    private ProxyIpCrawler annotation;

    public ProxyIpCrawlerContext(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
        this.annotation = method.getAnnotation(ProxyIpCrawler.class);
    }

    public Object getBean() {
        return bean;
    }


    public Method getMethod() {
        return method;
    }

    public ProxyIpCrawler getAnnotation() {
        return annotation;
    }

}
