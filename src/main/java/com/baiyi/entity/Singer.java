package com.baiyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌手
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0女1男2组合3不明）
     */
    private Byte sex;

    /**
     * 头像
     */
    private String pic;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 地区
     */
    private String location;

    /**
     * 简介
     */
    private String introduction;

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
