package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.thefive.apiserver.mapper.InsuranceRatioMapper;
import com.thefive.apiserver.pojo.InsuranceRatio;
import com.thefive.apiserver.service.InsuranceRatioService;
import org.springframework.stereotype.Service;

@Service
public class InsuranceRatioServiceImpl extends ServiceImpl<InsuranceRatioMapper, InsuranceRatio> implements InsuranceRatioService {
}
