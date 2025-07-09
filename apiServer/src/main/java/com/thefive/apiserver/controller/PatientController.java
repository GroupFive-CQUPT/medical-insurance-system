package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thefive.apiserver.pojo.Patient;
import com.thefive.apiserver.service.PatientService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@Api(tags="患者管理")
public class PatientController {
    @Autowired
    private PatientService patientService;

    //添加患者
    @PostMapping("/addPatient")
    @ApiOperation("添加患者")
    public ResultVo addPatient(@RequestBody Patient patient) throws Exception {
        //通过检测身份证号是否唯一判断患者是否存在
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.eq("id_card", patient.getIdCard());
        qw.eq("is_deleted", 0);
        Patient existPatient = patientService.getOne(qw);
        if (existPatient != null) {
            return ResultVo.fail("该身份证号已存在患者记录");
        }
        patient.setIsDeleted(0);
        patientService.save(patient);
        return ResultVo.ok("添加患者成功");
    }

    //修改患者信息
    @PostMapping("/updatePatient")
    @ApiOperation("修改患者信息")
    public ResultVo updatePatient(@RequestBody Patient patient) throws Exception {
        patientService.updateById(patient);
        return ResultVo.ok("修改患者信息成功");
    }

    //删除患者
    @GetMapping("/deletePatient")
    @ApiOperation("删除患者信息")
    public ResultVo deletePatient(@RequestParam Integer id) throws Exception {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setIsDeleted(1);
        boolean result = patientService.updateById(patient);
        if (result) {
            return ResultVo.ok(result, "删除患者成功");
        } else {
            return ResultVo.fail("删除患者失败", result);
        }
    }

    //查询患者列表（分页）
    @GetMapping("/listPatient")
    @ApiOperation("查询患者列表(分页)")
    public ResultVo <Page<Patient>> getPatientList(
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String insuranceType,
            @RequestParam(required = false) Integer gender
    )throws Exception{
        Page<Patient> patientPage = new Page<>(page, pageSize);
        QueryWrapper<Patient> qw = new QueryWrapper<>();

        // 搜索条件
        if (patientName != null && !patientName.trim().isEmpty()) {
            qw.like("patient_name", patientName);
        }
        if (insuranceType != null && !insuranceType.trim().isEmpty()) {
            qw.eq("insurance_type", insuranceType);
        }
        if (gender != null) {
            qw.eq("gender", gender);
        }

        qw.eq("is_deleted", 0); // 只查询未删除的患者
        qw.orderByAsc("id");
        patientPage = patientService.page(patientPage, qw);
        return ResultVo.ok(patientPage, "查询成功");
    }
    //根据患者姓名查询
    @GetMapping("/getPatient")
    @ApiOperation("根据患者姓名查询")
    public ResultVo<Page<Patient>> getPatient(
            @RequestParam String patientName,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {

        Page<Patient> patientPage = new Page<>(page, pageSize);
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.like("patient_name", patientName); // 使用模糊查询，因为可能有同名患者
        qw.eq("is_deleted", 0);
        qw.orderByAsc("id");

        patientPage = patientService.page(patientPage, qw);

        if (patientPage.getRecords().size() > 0) {
            return ResultVo.ok(patientPage, "查询成功，找到" + patientPage.getTotal() + "个匹配的患者");
        } else {
            return ResultVo.fail("未找到名为'" + patientName + "'的患者");
        }
    }

}

