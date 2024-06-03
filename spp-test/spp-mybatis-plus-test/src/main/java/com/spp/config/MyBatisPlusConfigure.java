package com.spp.config;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zhougy
 * @date 2023/8/7 15:20
 **/
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.spp.**.mapper"})
public class MyBatisPlusConfigure {

}
