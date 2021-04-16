package com.baiyi.service.impl;

import com.baiyi.entity.Admin;
import com.baiyi.mapper.AdminMapper;
import com.baiyi.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 管理员 服务实现类
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Value("${jasypt.encryptor.password}")
    private String secretKey;

    @Override
    public boolean verifyPassword(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(secretKey);
        // 根据数据库查询到的密码进行解密与原先的密码就完成登录，否则登录失败
        return encryptor.decrypt(admin.getPassword()).equals(password);
    }
}
