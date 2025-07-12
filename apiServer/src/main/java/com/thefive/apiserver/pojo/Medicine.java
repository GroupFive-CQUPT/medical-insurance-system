package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("medicine")
public class Medicine {
    @TableId(type= IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="药品类别")
    private Integer category;//1-甲类 2-乙类 3-丙类
    @ApiModelProperty(value="药品名")
    private String medicineName;//药品名
    @ApiModelProperty(value="商品名")
    private String productName;//商品名
    @ApiModelProperty(value="规格")
    private String specification;//规格
    @ApiModelProperty(value="计量单位")
    private String unit;//单位
    @ApiModelProperty(value="药品价格")
    private BigDecimal medicinePrice;//药品价格
    @ApiModelProperty(value="生产厂家")
    private String manufacturer;//生产厂家
    @ApiModelProperty(value="备注")
    private String remark;//备注
    @ApiModelProperty(value="逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;//0-启用 1-删除
}
