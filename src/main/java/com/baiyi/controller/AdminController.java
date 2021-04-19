package com.baiyi.controller;

import com.baiyi.entity.Admin;
import com.baiyi.service.AdminService;
import com.baiyi.utils.Consts;
import com.baiyi.utils.ResponseUtil;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员 前端控制器
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${jasypt.encryptor.password}")
    private String secretKey;

    @PostMapping(value = "/login/status")
    public Object loginStatus(HttpServletRequest request, HttpSession session) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Admin admin = adminService.verifyPassword(name, password);
        if (admin == null){
            return ResponseUtil.failRsp("用户不存在，请重新输入");
        }
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(secretKey);
        // 根据数据库查询到的密码进行解密与原先的密码就完成登录，否则登录失败
        if (encryptor.decrypt(admin.getPassword()).equals(password)){
            session.setAttribute(Consts.NAME, name);
            return ResponseUtil.successRsp("登录成功");
        }
        return ResponseUtil.failRsp("密码错误，请重新输入");
    }
}

