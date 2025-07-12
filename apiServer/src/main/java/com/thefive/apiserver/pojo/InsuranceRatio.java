package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("insurance_ratios")
public class InsuranceRatio {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "报销比例ID，主键自增")
    private Integer id;

    @ApiModelProperty(value = "医院等级：1-一级，2-二级，3-三级")
    private Integer hospitalLevel;

    @ApiModelProperty(value = "起付线金额，单位：元")
    private BigDecimal deductiblePrice;

    @ApiModelProperty(value = "分档线金额，单位：元")
    private BigDecimal tierPrice;

    @ApiModelProperty(value = "参保人员类别：1-职工，2-居民")
    private Integer insuranceType;

    @ApiModelProperty(value = "报销比例，如：0.80表示80%")
    private BigDecimal reimbursementRate;

    @ApiModelProperty(value = "逻辑删除：0-启用，1-删除")
    private Integer isDeleted;
}