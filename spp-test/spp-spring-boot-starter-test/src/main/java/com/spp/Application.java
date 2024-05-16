package com.spp;


import com.spp.core.Lockable;
import com.spp.core.ProxyIpCrawlerExecutor;
import com.spp.core.ProxyIpPool;
import com.spp.utils.SpringUtil;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author zhougy
 * @date 2024/05/14
 */
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}

