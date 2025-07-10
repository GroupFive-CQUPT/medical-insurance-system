package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.thefive.apiserver.pojo.DiseaseCode;
import com.thefive.apiserver.service.DiseaseCodeService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diseaseCode")
@Api(tags="疾病编码管理")
public class DiseaseCodeController {
    @Autowired
    private DiseaseCodeService diseaseCodeService;

    @ApiOperation("增加疾病编码")
    @PostMapping("/addDiseaseCode")
    public ResultVo addDiseaseCode(@RequestBody DiseaseCode diseaseCode) throws Exception {
        QueryWrapper qw = new QueryWrapper();
        //通过疾病编码检查是否存在当前类型疾病
        qw.eq("disease_code", diseaseCode.getDiseaseCode());
        qw.eq("is_deleted", 0);
        DiseaseCode code = diseaseCodeService.getOne(qw);
        if (code != null) {
            return ResultVo.fail("该疾病编码已存在");
        }
        diseaseCode.setIsDeleted(0);
        boolean result = diseaseCodeService.save(diseaseCode);
        if (result) {
            return ResultVo.ok(diseaseCode, "添加疾病编码成功");
        } else {
            return ResultVo.fail("添加疾病编码失败");
        }
    }

    @ApiOperation("修改疾病编码")
    @PostMapping("/updateDiseaseCode")
    public ResultVo updateDiseaseCode(@RequestBody DiseaseCode diseaseCode) throws Exception {
        boolean result = diseaseCodeService.updateById(diseaseCode);
        if (result) {
            return ResultVo.ok(diseaseCode, "修改疾病编码成功");
        } else {
            return ResultVo.fail("修改疾病编码失败");
        }
    }

    @ApiOperation("删除疾病编码")
    @GetMapping("/deleteDiseaseCode")
    public ResultVo deleteDiseaseCode(@RequestParam Integer id) throws Exception {
        DiseaseCode code = new DiseaseCode();
        code.setId(id);
        code.setIsDeleted(1);
        boolean result = diseaseCodeService.updateById(code);
        if (result) {
            return ResultVo.ok(code, "删除疾病编码成功");
        } else {
            return ResultVo.fail("删除疾病编码失败");
        }
    }

    @ApiOperation("分页查询疾病编码")
    @GetMapping("/listDiseaseCode")
    public ResultVo<Page<DiseaseCode>> listDiseaseCode(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                       @RequestParam(required = false) String diseaseType,
                                                       @RequestParam(required = false) String diseaseName) throws Exception {
      Page<DiseaseCode> codePage=new Page<>(page,pageSize);
      QueryWrapper qw = new QueryWrapper();
      if(diseaseType!=null&&!diseaseType.trim().isEmpty())
      {
          qw.eq("disease_type",diseaseType);
      }
      if (diseaseType!=null&&!diseaseType.trim().isEmpty())
      {
          qw.like("disease_name",diseaseName);
      }
      qw.eq("is_deleted",0);
      qw.orderByAsc("id");
      codePage=diseaseCodeService.page(codePage,qw);
      if(codePage.getRecords().size()>0){
          return ResultVo.ok(codePage,"查询疾病编码成功,找到"+codePage.getTotal()+"条数据");
      }else{
          return ResultVo.fail("查询疾病编码失败");
      }
    }

    @ApiOperation("按疾病类型分页查询")
    @GetMapping("/getByDiseaseType")
    public ResultVo<Page<DiseaseCode>> getByDiseaseType(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                       @RequestParam String diseaseType) throws Exception {
        Page<DiseaseCode> codePage=new Page<>(page,pageSize);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("disease_type",diseaseType);
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        codePage=diseaseCodeService.page(codePage,qw);
        if(codePage.getRecords().size()>0){
            return ResultVo.ok(codePage,"查询疾病编码成功,找到"+codePage.getTotal()+"条数据");
        }else{
            return ResultVo.fail("查询疾病编码失败");
        }
    }

    @ApiOperation("按疾病名称分页查询")
    @GetMapping("/getByDiseaseName")
    public ResultVo<Page<DiseaseCode>> getByDiseaseName(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                       @RequestParam String diseaseName) throws Exception {
        Page<DiseaseCode> codePage = new Page<>(page, pageSize);
        QueryWrapper qw = new QueryWrapper();
        qw.like("disease_name", diseaseName);
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        codePage=diseaseCodeService.page(codePage,qw);
        if(codePage.getRecords().size()>0){
            return ResultVo.ok(codePage,"查询疾病编码成功,找到"+codePage.getTotal()+"条数据");
        }else{
            return ResultVo.fail("查询疾病编码失败");
        }
    }

}
