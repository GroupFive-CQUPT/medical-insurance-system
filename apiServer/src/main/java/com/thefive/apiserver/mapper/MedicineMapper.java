package com.thefive.apiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thefive.apiserver.pojo.Medicine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicineMapper extends BaseMapper<Medicine> {
}
