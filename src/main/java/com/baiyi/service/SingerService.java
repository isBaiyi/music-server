package com.baiyi.service;

import com.baiyi.entity.Singer;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 歌手 服务类
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface SingerService extends IService<Singer> {

    boolean insert(HttpServletRequest request);

    boolean update(HttpServletRequest request);

    boolean delete(Integer id);

    Singer selectById(Integer id);

    List<Singer> selectAll();

    /**
     * 根据歌手名字模糊查询列表
     * @param name 名字
     * @return 歌手列表
     */
    List<Singer> selectByName(String name);

    /**
     * 根据性别查询歌手列表
     * @param sex 性别
     * @return 歌手列表
     */
    List<Singer> selectBySex(Integer sex);

    boolean updateById(Singer singer);
}
