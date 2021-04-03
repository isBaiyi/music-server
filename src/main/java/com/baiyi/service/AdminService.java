package com.baiyi.service;

import com.baiyi.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 管理员 服务类
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface AdminService extends IService<Admin> {
    /**
     * 验证密码是否正确
     *
     * @param username 前端传进来的账号
     * @param password 前端传进来的密码
     * @return 结果
     */
    boolean verifyPassword(String username, String password);
}
