package com.spp.crawler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spp.crawler.entity.ProxyIpDO;
import com.spp.crawler.mapper.ProxyIpMapper;
import com.spp.crawler.service.ProxyIpService;
import org.springframework.stereotype.Service;

/**
 * @author zhougy
 * @date 2024/06/03
 */
@Service
public class ProxyIpServiceImpl extends ServiceImpl<ProxyIpMapper, ProxyIpDO> implements ProxyIpService {
}
