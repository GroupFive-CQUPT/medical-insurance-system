package com.thefive.apiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thefive.apiserver.pojo.User;
import com.thefive.apiserver.service.UserService;
import com.thefive.apiserver.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;
          //用户登录
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultVo<User> login(@RequestParam String username, @RequestParam String password)throws Exception{
        return userService.login(username,password);
    }
    //修改密码
    @PostMapping("/changePassword")
    @ApiOperation("修改密码")
    public ResultVo<User> changePassword (@RequestParam String oldPassword, @RequestParam String newPassword,
                                         HttpServletRequest request)throws Exception{
        Integer userId=(Integer)request.getAttribute("userId");
        return userService.changePassword(userId,oldPassword,newPassword);
    }

    //添加用户
    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public ResultVo addUser(@RequestBody  User user) throws Exception {
        // 检查用户名是否已存在
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername());
        qw.eq("is_deleted", 0);
        User existUser = userService.getOne(qw);

        if (existUser != null) {
            return ResultVo.fail("用户名已存在");
        }
        user.setIsDeleted(0);
        userService.save(user);
        return ResultVo.ok("添加成功");
    }
   //修改用户
   @PostMapping("/updateUser")
   @ApiOperation("修改用户")
   public ResultVo updateUser(@RequestBody  User user) throws Exception {
       userService.updateById(user);
       return ResultVo.ok("修改成功");
   }
   //删除用户 根据is_deleted字段的值实现 不直接删除记录
    @GetMapping("/deleteUser")
    @ApiOperation("删除用户")
    public ResultVo <Boolean> delete(@RequestParam Integer id) throws Exception {
        User user = new User();
        user.setId(id);
        user.setIsDeleted(1);//设置为1代表删除
        boolean result = userService.updateById(user);
        if (result) {
            return ResultVo.ok(true, "删除成功");
        } else {
            return ResultVo.fail("删除失败", false);
        }
    }

    //查询用户列表(分页)
   @GetMapping("/listUser")
   @ApiOperation("查询用户列表(分页)")
   public ResultVo<Page<User>> getUserList(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(required = false) String username,
                                           @RequestParam(required = false) Integer roleId)throws Exception{
       Page<User> userPage=new Page<>(page,pageSize);
       QueryWrapper<User> qw = new QueryWrapper<>();

       if(username!=null&& !username.trim().isEmpty()){
           qw.like("username",username);
       }
       if (roleId!=null){
           qw.eq("role_id",roleId);
       }
       qw.eq("is_deleted",0);//只查询存在的用户
       qw.orderByAsc("id");//id正序排序
       userPage = userService.page(userPage,qw);
       return ResultVo.ok(userPage,"查询成功");
   }
    //查询单个用户供搜索框使用 根据id或者用户名查询
    @GetMapping("/getUser")
    @ApiOperation("根据ID或用户名查询用户")
    public ResultVo<User> getUser(@RequestParam(required = false) Integer id,
                                  @RequestParam(required = false) String username) throws Exception {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0);

        if (id != null) {
            // 根据ID查询
            qw.eq("id", id);
        } else if (username != null && !username.trim().isEmpty()) {
            // 根据用户名查询
            qw.eq("username", username);
        } else {
            return ResultVo.fail("请提供用户ID或用户名");
        }

        User user = userService.getOne(qw);
        if (user != null) {
            return ResultVo.ok(user, "查询成功");
        } else {
            return ResultVo.fail("用户不存在");
        }
    }
}


