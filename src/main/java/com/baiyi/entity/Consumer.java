package com.baiyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 前端用户
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别（1男0女）
     */
    private Byte sex;

    /**
     * 电话
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 签名
     */
    private String introduction;

    /**
     * 地区
     */
    private String location;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0:删除 1:有效
     */
    @TableLogic
    private Integer voidFlag;
}
