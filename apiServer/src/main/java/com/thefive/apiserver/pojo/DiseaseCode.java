package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("disease_code")
public class DiseaseCode {
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty(value="疾病编码")
    private String diseaseCode;
    @ApiModelProperty(value="国际ICD编码")
    private String icdCode;
    @ApiModelProperty(value="疾病类型")
    private String diseaseType;
    @ApiModelProperty(value="疾病名称")
    private String diseaseName;
    @ApiModelProperty(value="逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;
}
