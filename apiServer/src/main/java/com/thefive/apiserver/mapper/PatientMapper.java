package com.thefive.apiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thefive.apiserver.pojo.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
    // 继承BaseMapper后自动拥有基础的CRUD方法
}
