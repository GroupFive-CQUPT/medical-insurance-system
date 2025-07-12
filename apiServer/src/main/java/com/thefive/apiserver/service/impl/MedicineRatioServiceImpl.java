package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thefive.apiserver.mapper.MedicineRatioMapper;
import com.thefive.apiserver.pojo.MedicineRatio;
import com.thefive.apiserver.service.MedicineRatioService;
import org.springframework.stereotype.Service;

@Service
public class MedicineRatioServiceImpl extends ServiceImpl<MedicineRatioMapper, MedicineRatio> implements MedicineRatioService {
}
