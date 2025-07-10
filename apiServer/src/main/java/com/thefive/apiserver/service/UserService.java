package com.thefive.apiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thefive.apiserver.pojo.User;
import com.thefive.apiserver.utils.ResultVo;


public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果：data为用户信息，message为JWT token
     */
    public ResultVo<User> login(String username, String password) throws Exception;

    /**
     * 修改密码
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果：data为更新后的用户信息
     */
    public ResultVo<User> changePassword(Integer userId, String oldPassword, String newPassword) throws Exception;
}