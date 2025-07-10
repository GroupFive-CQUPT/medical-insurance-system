package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thefive.apiserver.pojo.MedicalItem;
import com.thefive.apiserver.service.MedicalItemService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalItem")
@Api(tags="诊疗项目管理")
public class MedicalItemController {
    @Autowired
    MedicalItemService medicalItemService;

    @ApiOperation("添加诊疗项目")
    @PostMapping("/addMedicalItem")
    public ResultVo addMedicalItem(@RequestBody MedicalItem medicalItem) throws Exception {
        QueryWrapper<MedicalItem> qw = new QueryWrapper<>();
        //通过项目编号判断是否已经存在该项目
        qw.eq("project_code", medicalItem.getProjectCode());
        qw.eq("is_deleted", 0);
        MedicalItem item = medicalItemService.getOne(qw);
        if (item != null) {
            return ResultVo.fail("该项目编号已存在");
        }
        medicalItem.setIsDeleted(0);
        boolean result = medicalItemService.save(medicalItem);
        if (result) {
            return ResultVo.ok(medicalItem, "添加成功");
        } else {
            return ResultVo.fail("添加失败");
        }
    }

    @ApiOperation("修改诊疗项目信息")
    @PostMapping("/updateMedicalItem")
    public ResultVo updateMedicalItem(@RequestBody MedicalItem medicalItem) throws Exception {
        boolean result = medicalItemService.updateById(medicalItem);
        if (result) {
            return ResultVo.ok(medicalItem, "修改成功");
        } else {
            return ResultVo.fail("修改失败");
        }
    }

    @ApiOperation("删除诊疗项目")
    @GetMapping("/deleteMedicalItem")
    public ResultVo deleteMedicalItem(@RequestParam Integer id) throws Exception {
        MedicalItem medicalItem = new MedicalItem();
        medicalItem.setId(id);
        medicalItem.setIsDeleted(1);
        boolean result = medicalItemService.updateById(medicalItem);
        if (result) {
            return ResultVo.ok(medicalItem, "删除成功");
        } else {
            return ResultVo.fail("删除失败");
        }
    }

    @ApiOperation("分页查询诊疗项目列表")
    @GetMapping("/listMedicalItem")
    public ResultVo<Page<MedicalItem>> getMedicalItemList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String itemName,
            @RequestParam(required = false) String category) throws Exception {
        Page<MedicalItem> medicalItemPage = new Page<>(page, pageSize);
        QueryWrapper<MedicalItem> qw = new QueryWrapper<>();
        if (itemName != null && !itemName.trim().isEmpty()) {
            qw.like("item_name", itemName);
        }
        if (category != null && !category.trim().isEmpty()) {
            qw.eq("category", category);
        }
        qw.eq("is_deleted", 0);
        qw.orderByAsc("id");
        medicalItemPage = medicalItemService.page(medicalItemPage, qw);
        if (medicalItemPage.getRecords().size() > 0) {
            return ResultVo.ok(medicalItemPage, "查询成功,找到" + medicalItemPage.getTotal() + "条数据");
        } else {
            return ResultVo.fail("查询失败,未找到相关数据");
        }
    }

    @ApiOperation("按诊疗项目名分页查询")
    @GetMapping("/getByItemName")
    public ResultVo<Page<MedicalItem>> getByItemName(@RequestParam String itemName
            , @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
        Page<MedicalItem> medicalItemPage = new Page<>(page, pageSize);
        QueryWrapper<MedicalItem> qw = new QueryWrapper<>();
        qw.like("item_name", itemName);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("id");
        medicalItemPage = medicalItemService.page(medicalItemPage, qw);
        if (medicalItemPage.getRecords().size() > 0) {
            return ResultVo.ok(medicalItemPage, "查询成功,找到" + medicalItemPage.getTotal() + "条数据");
        } else {
            return ResultVo.fail("查询失败,未找到相关数据");
        }
    }

    @ApiOperation("按诊疗项目类型分页查询")
    @GetMapping("/getByCategory")
    public ResultVo<Page<MedicalItem>> getByCategory(@RequestParam String category
            , @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
        Page<MedicalItem> medicalItemPage = new Page<>(page, pageSize);
        QueryWrapper<MedicalItem> qw = new QueryWrapper<>();
        qw.eq("category", category);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("id");
        medicalItemPage = medicalItemService.page(medicalItemPage, qw);
        if (medicalItemPage.getRecords().size() > 0) {
            return ResultVo.ok(medicalItemPage, "查询成功,找到" + medicalItemPage.getTotal() + "条数据");
        } else {
            return ResultVo.fail("查询失败，未找到相关数据");
        }
    }
}
