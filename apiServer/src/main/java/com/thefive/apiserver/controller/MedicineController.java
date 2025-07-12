package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thefive.apiserver.pojo.Medicine;
import com.thefive.apiserver.service.MedicineService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
@Api(tags="药品管理")
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @ApiOperation("添加药品信息")
    @PostMapping("/addMedicine")
    public ResultVo addMedicine(@RequestBody Medicine medicine)throws Exception
    {
        medicine.setIsDeleted(0);
        boolean result= medicineService.save(medicine);
        if(medicine!=null){
            return ResultVo.ok(medicine,"添加成功");
        }else{
            return ResultVo.fail("添加失败");
        }
    }
    @ApiOperation("修改药品信息")
    @PostMapping("/updateMedicine")
    public ResultVo updateMedicine(@RequestBody Medicine medicine)throws Exception{
        boolean result=medicineService.updateById(medicine);
        if(result){
            return ResultVo.ok(medicine,"修改成功");
        }else {
            return ResultVo.fail("修改失败");
        }
    }

    @ApiOperation("删除药品信息")
    @GetMapping("/deleteMedicine")
    public ResultVo deleteMedicine(@RequestParam Integer medicineId)throws Exception{
     Medicine medicine=new Medicine();
     medicine.setId(medicineId);
     medicine.setIsDeleted(1);
     boolean result=medicineService.updateById(medicine);
     if(result){
         return ResultVo.ok(medicine,"删除药品成功");
     }else {
         return ResultVo.fail("删除药品失败");
     }
    }

    @ApiOperation("分页查询药品信息列表")
    @GetMapping("/listMedicine")
    public ResultVo <Page<Medicine>> getMedicineList(@RequestParam(defaultValue ="1") Integer page,
                                                     @RequestParam(defaultValue="10") Integer pageSize,
                                                     @RequestParam(required = false) String medicineName,
                                                     @RequestParam(required = false) Integer category)throws Exception
    {
      Page<Medicine> medicinePage=new Page<>(page,pageSize);
        QueryWrapper<Medicine> qw=new QueryWrapper<>();
        if(medicineName!=null&&!medicineName.trim().isEmpty())
        {
           qw.like("medicine_name",medicineName);
        }
        if(category!=null){
            qw.eq("category",category);
        }
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        medicinePage=medicineService.page(medicinePage,qw);
        if(medicinePage.getRecords().size()>0){
            return ResultVo.ok(medicinePage,"查询成功,找到"+medicinePage.getTotal()+"条数据");
        }else {
            return ResultVo.fail("未找到药品");
        }
    }

    @ApiOperation("按药品名查询药品信息(分页)")
    @GetMapping("/getByMedicineName")
    public ResultVo<Page<Medicine>> getByMedicineName(@RequestParam(defaultValue ="1") Integer page,
                                                      @RequestParam(defaultValue="10") Integer pageSize,
                                                      @RequestParam String medicineName)throws Exception{
        Page<Medicine> medicinePage=new Page<>(page,pageSize);
        QueryWrapper<Medicine> qw=new QueryWrapper();
        qw.like("medicine_name",medicineName);
        qw.eq("is_deleted",0);
        medicinePage=medicineService.page(medicinePage,qw);
        if(medicinePage.getRecords().size()>0){
            return ResultVo.ok(medicinePage,"查询成功,找到"+medicinePage.getTotal()+"条数据");
        }else
        {
            return ResultVo.fail("未找到名为"+medicineName+"的药品");
        }
    }

}
