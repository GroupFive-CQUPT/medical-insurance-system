package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thefive.apiserver.pojo.ServiceItem;
import com.thefive.apiserver.service.ServiceItemService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags="医疗服务项目管理")
@RequestMapping("/serviceItem")
public class ServiceItemController {
    @Autowired
    private ServiceItemService serviceItemService;

    @ApiOperation("增加医疗服务项目")
    @PostMapping("/addServiceItem")
    public ResultVo addServiceItem(@RequestBody ServiceItem serviceItem) throws Exception {
        QueryWrapper<ServiceItem> qw = new QueryWrapper<>();
        //通过服务编码判断是否已经存在项目
        qw.eq("service_code", serviceItem.getServiceCode());
        qw.eq("is_deleted", 0);
        ServiceItem item = serviceItemService.getOne(qw);
        if (item != null) {
            return ResultVo.fail("该服务项目已经存在");
        }
        serviceItem.setIsDeleted(0);
        boolean result = serviceItemService.save(serviceItem);
        if (result) {
            return ResultVo.ok(serviceItem, "添加医疗服务项目成功");
        } else {
            return ResultVo.fail("添加医疗服务项目失败");
        }
    }

    @ApiOperation("修改医疗服务项目信息")
    @PostMapping("/updateServiceItem")
    public ResultVo updateServiceItem(@RequestBody ServiceItem serviceItem) throws Exception {
        boolean result = serviceItemService.updateById(serviceItem);
        if(result){
            return ResultVo.ok(serviceItem,"修改医疗服务项目信息成功");
        }else{
            return ResultVo.fail("修改医疗服务项目信息失败");
        }
    }

    @ApiOperation("删除医疗服务项目")
    @GetMapping("/deleteServiceItem")
    public ResultVo deleteServiceItem(@RequestParam Integer id) throws Exception {
        ServiceItem serviceItem=new ServiceItem();
        serviceItem.setId(id);
        serviceItem.setIsDeleted(1);
        boolean result = serviceItemService.updateById(serviceItem);
        if(result){
            return ResultVo.ok(serviceItem,"删除医疗服务项目成功");
        }else{
            return ResultVo.fail("删除医疗服务项目失败");
        }
    }
    @ApiOperation("分页查询医疗服务项目列表")
    @GetMapping("/listServiceItem")
    public ResultVo <Page<ServiceItem>> getServiceItemList(
            @RequestParam(defaultValue = "1")Integer page,
            @RequestParam(defaultValue="10")Integer pageSize,
            @RequestParam(required = false) String serviceName,
            @RequestParam(required=false)String serviceCategory)throws Exception{
             Page<ServiceItem> itemPage=new Page<>(page,pageSize);
             QueryWrapper<ServiceItem> qw = new QueryWrapper<>();
             if(serviceName!=null&&!serviceName.trim().isEmpty())
             {
                 qw.like("service_name",serviceName);
             }
             if(serviceCategory!=null&&!serviceCategory.trim().isEmpty())
             {
                 qw.eq("service_category",serviceCategory);
             }
             qw.eq("is_deleted",0);
             qw.orderByAsc("id");
             itemPage=serviceItemService.page(itemPage,qw);
             if(itemPage.getRecords().size()>0)
             {
               return ResultVo.ok(itemPage,"查找成功,找到"+itemPage.getTotal()+"条数据");
             }
             else{
                 return ResultVo.fail("查找失败，未找到相关数据");
             }
    }

    @ApiOperation("按服务名称分页查询")
    @GetMapping("/getByServiceName")
    public ResultVo <Page<ServiceItem>> getByServiceName(@RequestParam String serviceName,
                                                         @RequestParam(defaultValue = "1")Integer page,
                                                         @RequestParam(defaultValue = "10")Integer pageSize)throws Exception{
        Page<ServiceItem> itemPage=new Page<>(page,pageSize);
        QueryWrapper<ServiceItem> qw = new QueryWrapper<>();
        qw.like("service_name",serviceName);
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        itemPage=serviceItemService.page(itemPage,qw);
        if (itemPage.getRecords().size()>0)
        {
            return ResultVo.ok(itemPage,"查找成功，找到"+itemPage.getTotal()+"条数据");
        }
        else{
            return ResultVo.fail("查找失败，未找到相关数据");
        }
    }

    @ApiOperation("按服务类型分页查询")
    @GetMapping("/getByServiceCategory")
    public ResultVo <Page<ServiceItem>> getByServiceCategory(@RequestParam String serviceCategory,
                                                             @RequestParam(defaultValue = "1")Integer page,
                                                             @RequestParam(defaultValue = "10")Integer pageSize)throws Exception{
        Page<ServiceItem> itemPage=new Page<>(page,pageSize);
        QueryWrapper<ServiceItem> qw = new QueryWrapper<>();
        qw.eq("service_category",serviceCategory);
        qw.eq("is_deleted",0);
        qw.orderByAsc("id");
        itemPage=serviceItemService.page(itemPage,qw);
        if (itemPage.getRecords().size()>0)
        {
            return ResultVo.ok(itemPage,"查找成功，找到"+itemPage.getTotal()+"条数据");
        }
        else{
            return ResultVo.fail("查找失败，未找到相关数据");
        }
    }
}
