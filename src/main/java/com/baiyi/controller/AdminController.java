package com.baiyi.controller;

import com.baiyi.service.AdminService;
import com.baiyi.utils.Consts;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/login/status")
    public Object loginStatus(HttpServletRequest request, HttpSession session) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean result = adminService.verifyPassword(name, password);
        if (result) {
            session.setAttribute(Consts.NAME, name);
            return ResponseUtil.successRsp("登录成功");

        }
        return ResponseUtil.failRsp("用户名或密码错误");
    }
}

