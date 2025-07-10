package com.thefive.apiserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thefive.apiserver.mapper.UserMapper;
import com.thefive.apiserver.pojo.User;
import com.thefive.apiserver.service.UserService;
import com.thefive.apiserver.utils.ResultVo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResultVo<User> login(String username, String password)throws Exception{
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        qw.eq("password",password);
        qw.eq("is_deleted",0);//只查询未删除的客户
        User user = getOne(qw);
        System.out.println(user);
        if (user!=null){
            //生成JWT Token
            HashMap<String,Object> map = new HashMap<>();
            map.put("userId",user.getId());
            map.put("username",user.getUsername());
            map.put("roleId",user.getRoleId());

            JwtBuilder builder = Jwts.builder();
            String token = builder.setSubject(username) // 主题，就是token中携带的数据
                    .setIssuedAt(new Date()) // 设置token的生成时间
                    .setId(user.getId().toString()) // 设置用户id为token id
                    .setClaims(map) // map中存放用户的角色权限信息
                    .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 设置过期时间7天
                    .signWith(SignatureAlgorithm.HS256, "mdis123456") // 设置加密方式和加密密码
                    .compact();
            return ResultVo.ok(user,token);
        }
        else {
            return ResultVo.fail("账号密码错误");
        }
    }


    @Override
    public ResultVo<User> changePassword(Integer userId,String oldPassword,String newPassword)throws Exception{
         //查询用户
        User user =getById(userId);
        if(user ==null || user.getIsDeleted()==1)
        {
            return ResultVo.fail("用户不存在");
        }
        //验证旧密码
        if(!user.getPassword().equals(oldPassword))
        {
            return ResultVo.fail("原密码错误");
        }
        //更新密码
        user.setPassword(newPassword);
        boolean result = updateById(user);
        if(result)
        {
            return ResultVo.ok(user,"密码修改成功");
        }
        else{
            return ResultVo.fail("密码修改失败");
        }
    }
}
