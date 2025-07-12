package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thefive.apiserver.pojo.InsuranceRatio;
import com.thefive.apiserver.service.InsuranceRatioService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insuranceRatio")
@Api(tags = "医院报销比例管理")
public class InsuranceRatioController {
    @Autowired
    InsuranceRatioService insuranceRatioService;

    @ApiOperation("添加医院报销等级")
    @PostMapping("/addInsuranceRatio")
    public ResultVo addInsuranceRatio(@RequestBody InsuranceRatio insuranceRatio)throws Exception {
        QueryWrapper<InsuranceRatio> qw = new QueryWrapper<>();
        //待纠正
        //通过查询医院等级判断是否已存在该医院报销等级
        qw.eq("hospital_level", insuranceRatio.getHospitalLevel());
        qw.eq("is_deleted", 0);
        InsuranceRatio ratio = insuranceRatioService.getOne(qw);
        if (ratio != null) {
            return ResultVo.fail("该医院报销等级已存在");
        }
        insuranceRatio.setIsDeleted(0);
        boolean result = insuranceRatioService.save(insuranceRatio);
        if (result) {
            return ResultVo.ok(insuranceRatio,"添加成功");
        }else{
            return ResultVo.fail("添加失败");
        }
    }
    @ApiOperation("修改医院报销等级")
    @PostMapping("/updateInsuranceRatio")
    public ResultVo updateInsuranceRatio(@RequestBody InsuranceRatio insuranceRatio)throws Exception {
        boolean result = insuranceRatioService.updateById(insuranceRatio);
        if (result) {
            return ResultVo.ok(insuranceRatio,"修改医院报销等级成功");
        }else{
            return ResultVo.fail("修改医院报销等级失败");
        }
    }

    @ApiOperation("删除医院报销等级")
    @GetMapping("/deleteInsuranceRatio")
    public ResultVo deleteInsuranceRatio(@RequestParam Integer id)throws Exception {
        InsuranceRatio ratio = new InsuranceRatio();
        ratio.setId(id);
        ratio.setIsDeleted(1);
        boolean result = insuranceRatioService.updateById(ratio);
        if (result) {
            return ResultVo.ok(ratio,"删除医院等级成功");
        }
        else{
            return ResultVo.fail("删除医院等级失败");
        }
    }

    @ApiOperation("分页查询医院报销等级列表")
    @GetMapping("/getInsuranceRatioList")
    public ResultVo <Page<InsuranceRatio>> getInsuranceRatioList(@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                                 @RequestParam(required = false)Integer hospitalLevel )
            throws Exception {
        Page<InsuranceRatio> ratioPage=new Page<>(page,pageSize);
        QueryWrapper<InsuranceRatio> qw = new QueryWrapper<>();
        if(hospitalLevel!=null){
            qw.eq("hospital_level",hospitalLevel);
        }
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        ratioPage=insuranceRatioService.page(ratioPage,qw);
        if(ratioPage.getRecords().size()>0){
            return ResultVo.ok(ratioPage,"查询成功,找到"+ratioPage.getTotal()+"条数据");
        }
        else{
            return ResultVo.fail("未找到相关数据");
        }
    }

    @ApiOperation("根据药品级别分页查找相关药品报销比例")
    @GetMapping("/getByHospitalLevel")
    public ResultVo <Page<InsuranceRatio>> getByHospitalLevel(@RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam Integer hospitalLevel )
            throws Exception {
        Page<InsuranceRatio> ratioPage=new Page<>(page,pageSize);
        QueryWrapper<InsuranceRatio> qw = new QueryWrapper<>();
        qw.eq("hospital_level",hospitalLevel);
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        ratioPage=insuranceRatioService.page(ratioPage,qw);
        if(ratioPage.getRecords().size()>0){
            return ResultVo.ok(ratioPage,"查询成功,找到"+ratioPage.getTotal()+"条数据");
        }
        else{
            return ResultVo.fail("未找到相关数据");
        }
    }
}
