package com.thefive.apiserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("patients")
public class Patient {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer hospitalNo;//住院号
    private String patientName;
    private String idCard;//身份证号
    private Integer age;
    private Date  birthDate;
    private Integer gender;//0-女 1-男
    private String insuranceType;//医保类型 医保/自费
    private String workStatus;//工作状态 在职/其他
    private Date admissionTime;//入院时间
    private String address;//家庭住址
    private Integer isDeleted;//0-启用 1-删除
}
