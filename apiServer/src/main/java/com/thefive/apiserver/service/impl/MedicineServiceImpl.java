package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thefive.apiserver.mapper.MedicineMapper;
import com.thefive.apiserver.pojo.Medicine;
import com.thefive.apiserver.service.MedicineService;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements MedicineService {
}
