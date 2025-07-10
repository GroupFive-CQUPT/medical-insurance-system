package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("patients")
public class Patient {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="住院号")
    private Integer hospitalNo;//住院号
    @ApiModelProperty(value="病人姓名")
    private String patientName;
    @ApiModelProperty(value="身份证号")
    private String idCard;//身份证号
    @ApiModelProperty(value="年龄")
    private Integer age;
    @ApiModelProperty(value="出生日期")
    private Date  birthDate;
    @ApiModelProperty(value="性别")
    private Integer gender;//0-女 1-男
    @ApiModelProperty(value="医保类型")
    private String insuranceType;//医保类型 医保/自费
    @ApiModelProperty(value="工作状态")
    private String workStatus;//工作状态 在职/其他
    @ApiModelProperty(value="入院时间")
    private Date admissionTime;//入院时间
    @ApiModelProperty(value="家庭住址")
    private String address;//家庭住址
    @ApiModelProperty(value="逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;//0-启用 1-删除
}
