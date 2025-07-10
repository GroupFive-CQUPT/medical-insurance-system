package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thefive.apiserver.mapper.DiseaseCodeMapper;
import com.thefive.apiserver.pojo.DiseaseCode;
import com.thefive.apiserver.service.DiseaseCodeService;
import org.springframework.stereotype.Service;

@Service
public class DiseaseCodeServiceImpl extends ServiceImpl<DiseaseCodeMapper, DiseaseCode> implements DiseaseCodeService {
}
