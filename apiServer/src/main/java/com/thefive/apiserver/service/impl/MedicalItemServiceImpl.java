package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.thefive.apiserver.mapper.MedicalItemMapper;
import com.thefive.apiserver.pojo.MedicalItem;
import com.thefive.apiserver.service.MedicalItemService;
import org.springframework.stereotype.Service;

@Service
public class MedicalItemServiceImpl extends ServiceImpl<MedicalItemMapper, MedicalItem> implements MedicalItemService {

}