package com.thefive.apiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thefive.apiserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 继承BaseMapper后自动拥有基础的CRUD方法
}
