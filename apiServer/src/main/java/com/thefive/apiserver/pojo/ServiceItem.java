package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("service_items")
public class ServiceItem {
    @TableId(type= IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="服务类别")
    private String serviceCategory;
    @ApiModelProperty(value="服务编码")
    private String serviceCode;
    @ApiModelProperty(value="服务国家编码")
    private String serviceNational;
    @ApiModelProperty(value="服务名称")
    private String serviceName;
    @ApiModelProperty(value="服务说明")
    private String serviceDescription;
    @ApiModelProperty(value="除外内容")
    private String externalContent;
    @ApiModelProperty(value="计价单位")
    private String serviceUnit;
    @ApiModelProperty(value="价格，单位：元")
    private BigDecimal servicePrice;
    @ApiModelProperty(value="备注信息")
    private String remark;
    @ApiModelProperty(value="逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;
}