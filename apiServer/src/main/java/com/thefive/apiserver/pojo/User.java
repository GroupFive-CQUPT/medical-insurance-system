package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type= IdType.AUTO)
    private Integer id;//用户id列
    private String username;
    private String password;
    private Integer roleId;//1-管理员角色
    private Integer isDeleted; //0-启用 1-删除
}

