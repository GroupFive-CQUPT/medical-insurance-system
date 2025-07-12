package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.thefive.apiserver.pojo.MedicineRatio;
import com.thefive.apiserver.service.MedicineRatioService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicineRatio")
@Api(tags = "药品报销比例管理")
public class MedicineRatioController {
    @Autowired
    MedicineRatioService medicineRatioService;

    @ApiOperation("添加药品报销等级")
    @PostMapping("/addMedicineRatio")
    public ResultVo addMedicineRatio(@RequestBody MedicineRatio medicineRatio)throws Exception {
        QueryWrapper<MedicineRatio> qw = new QueryWrapper<>();
        //通过查询药品等级判断是否已存在该药品报销等级
        qw.eq("medicine_level", medicineRatio.getMedicineLevel());
        qw.eq("is_deleted", 0);
        MedicineRatio ratio = medicineRatioService.getOne(qw);
        if (ratio != null) {
            return ResultVo.fail("该药品报销等级已存在");
        }
        medicineRatio.setIsDeleted(0);
        boolean result = medicineRatioService.save(medicineRatio);
        if (result) {
            return ResultVo.ok(medicineRatio,"添加成功");
        }else{
            return ResultVo.fail("添加失败");
        }
    }
    @ApiOperation("修改药品报销等级")
    @PostMapping("/updateMedicineRatio")
    public ResultVo updateMedicineRatio(@RequestBody MedicineRatio medicineRatio)throws Exception {
      boolean result = medicineRatioService.updateById(medicineRatio);
      if (result) {
          return ResultVo.ok(medicineRatio,"修改药品等级成功");
      }else{
          return ResultVo.fail("修改药品等级失败");
       }
    }

    @ApiOperation("删除药品报销等级")
    @GetMapping("/deleteMedicineRatio")
    public ResultVo deleteMedicineRatio(@RequestParam Integer id)throws Exception {
        MedicineRatio ratio = new MedicineRatio();
        ratio.setId(id);
        ratio.setIsDeleted(1);
        boolean result = medicineRatioService.updateById(ratio);
        if (result) {
            return ResultVo.ok(ratio,"删除药品等级成功");
        }
        else{
            return ResultVo.fail("删除药品等级失败");
        }
    }

    @ApiOperation("分页查询药品报销等级列表")
    @GetMapping("/getMedicineRatioList")
    public ResultVo <Page<MedicineRatio>> getMedicineRatioList(@RequestParam(defaultValue = "1") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                                               @RequestParam(required = false)Integer medicineLevel )
            throws Exception {
        Page<MedicineRatio> ratioPage=new Page<>(page,pageSize);
        QueryWrapper<MedicineRatio> qw = new QueryWrapper<>();
        if(medicineLevel!=null){
            qw.eq("medicine_level",medicineLevel);
        }
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        ratioPage=medicineRatioService.page(ratioPage,qw);
        if(ratioPage.getRecords().size()>0){
            return ResultVo.ok(ratioPage,"查询成功,找到"+ratioPage.getTotal()+"条数据");
        }
        else{
            return ResultVo.fail("未找到相关数据");
        }
    }

    @ApiOperation("根据药品级别分页查找相关药品报销比例")
    @GetMapping("/getByMedicineLevel")
    public ResultVo <Page<MedicineRatio>> getByMedicineLevel(@RequestParam(defaultValue = "1") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam Integer medicineLevel )
            throws Exception {
                Page<MedicineRatio> ratioPage=new Page<>(page,pageSize);
                QueryWrapper<MedicineRatio> qw = new QueryWrapper<>();
                qw.eq("medicine_level",medicineLevel);
                qw.eq("is_deleted",0);
                qw.orderByAsc("id");
                ratioPage=medicineRatioService.page(ratioPage,qw);
                if(ratioPage.getRecords().size()>0){
                    return ResultVo.ok(ratioPage,"查询成功,找到"+ratioPage.getTotal()+"条数据");
                }
                else{
                    return ResultVo.fail("未找到相关数据");
                }
            }
}
