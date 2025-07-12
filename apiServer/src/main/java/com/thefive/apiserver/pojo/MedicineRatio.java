package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("medicine_ratios")
public class MedicineRatio {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "药品报销比例ID，主键自增")
    private Integer id;

    @ApiModelProperty(value = "药品等级：1-甲类，2-乙类，3-丙类")
    private Integer medicineLevel;

    @ApiModelProperty(value = "报销比例，如：0.90表示90%")
    private BigDecimal medicineRules;

    @ApiModelProperty(value = "逻辑删除：0-启用，1-删除")
    private Integer isDeleted;
}