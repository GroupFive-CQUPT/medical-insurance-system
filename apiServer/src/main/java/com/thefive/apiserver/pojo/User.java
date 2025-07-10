package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
public class User {
@TableId(type= IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;//用户id列
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="密码")
    private String password;
    @ApiModelProperty(value="角色id")
    private Integer roleId;//1-管理员角色
    @ApiModelProperty(value="逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted; //0-启用 1-删除
}
