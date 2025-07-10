package com.thefive.apiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thefive.apiserver.pojo.ServiceItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceItemMapper  extends BaseMapper<ServiceItem> {

}
