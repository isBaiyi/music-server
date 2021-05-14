package com.baiyi.service;

import com.baiyi.entity.Consumer;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 前端用户 服务类
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface ConsumerService extends IService<Consumer> {

    boolean insert(HttpServletRequest request);

    boolean update(HttpServletRequest request);

    boolean delete(Integer id);

    Consumer selectById(Integer id);

    List<Consumer> selectAll();

    boolean updateById(Consumer consumer);

    Consumer verifyPassword(String username, String password);

    Consumer selectByPrimaryKey(Integer id);
}
