package com.thefive.apiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thefive.apiserver.pojo.MedicalItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicalItemMapper extends BaseMapper<MedicalItem> {
}
