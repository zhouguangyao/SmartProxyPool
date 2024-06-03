package com.spp.crawler;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spp.core.ProxyIpPool;
import com.spp.core.pojo.ProxyIp;
import com.spp.crawler.entity.ProxyIpDO;
import com.spp.crawler.service.ProxyIpService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代理ip池（Mysql）
 * @author zhougy
 * @date 2024/05/14
 */
@Component
public class ProxyIpMysqlPool implements ProxyIpPool {

    @Resource
    private ProxyIpService proxyIpService;


    @Override
    public void put(ProxyIp proxyIp) {
        ProxyIpDO proxyIpDO = new ProxyIpDO();
        BeanUtils.copyProperties(proxyIp, proxyIpDO);
        proxyIpService.save(proxyIpDO);
    }

    @Override
    public void remove(ProxyIp proxyIp) {
        // 查询
        QueryWrapper<ProxyIpDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ProxyIpDO::getCity, proxyIp.getCity());
        wrapper.lambda().eq(ProxyIpDO::getIp, proxyIp.getIp());
        wrapper.lambda().eq(ProxyIpDO::getPort, proxyIp.getPort());
        // 删除
        List<ProxyIpDO> list = proxyIpService.list(wrapper);
        if (list!= null && list.size() > 0){
            proxyIpService.removeById(list.get(0).getId());
        }
    }

    /**
     * 获取随机
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp get() {
        QueryWrapper<ProxyIpDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(ProxyIpDO::getExpireTime);
        wrapper.last("LIMIT 1");
        List<ProxyIpDO> list = proxyIpService.list(wrapper);
        if (list == null || list.isEmpty()){
            return null;
        }
        ProxyIpDO proxyIpDO = list.get(0);
        ProxyIp proxyIp = new ProxyIp();
        BeanUtils.copyProperties(proxyIpDO, proxyIp);
        return proxyIp;
    }

    /**
     * 获取指定城市的随机
     * @param city
     * @return {@link ProxyIp}
     */
    @Override
    public ProxyIp getByCity(String city) {
        QueryWrapper<ProxyIpDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ProxyIpDO::getCity, city);
        wrapper.lambda().orderByAsc(ProxyIpDO::getExpireTime);
        wrapper.last("LIMIT 1");
        List<ProxyIpDO> list = proxyIpService.list(wrapper);
        if (list == null || list.isEmpty()){
            return null;
        }
        ProxyIpDO proxyIpDO = list.get(0);
        ProxyIp proxyIp = new ProxyIp();
        BeanUtils.copyProperties(proxyIpDO, proxyIp);
        return proxyIp;
    }
}
