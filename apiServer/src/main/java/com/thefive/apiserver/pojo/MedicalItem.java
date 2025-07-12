package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("medical_items")
public class MedicalItem {
    @TableId(type= IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="诊疗项目类别")
    private String category;
    @ApiModelProperty(value="诊疗项目费用")
    private BigDecimal itemPrice;
    @ApiModelProperty(value="诊疗项目编号")
    private String projectCode;
    @ApiModelProperty(value="诊疗项目国家编码")
    private String nationalCode;
    @ApiModelProperty(value="诊疗项目名")
    private String itemName;
    @ApiModelProperty(value="诊疗项目简介")
    private String itemDescription;
    @ApiModelProperty(value="诊疗项目除外内容")
    private String externalContent;
    @ApiModelProperty(value="计量单位")
    private String unit;
    @ApiModelProperty(value="备注")
    private String remark;
    @ApiModelProperty(value="逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

}
