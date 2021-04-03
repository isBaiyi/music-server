package com.baiyi.request;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}


