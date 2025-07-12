package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thefive.apiserver.mapper.ServiceItemMapper;
import com.thefive.apiserver.pojo.ServiceItem;
import com.thefive.apiserver.service.ServiceItemService;
import org.springframework.stereotype.Service;

@Service
public class ServiceItemServiceImpl extends ServiceImpl<ServiceItemMapper, ServiceItem> implements ServiceItemService {
}
