package com.baiyi.service;

import com.baiyi.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 歌单 服务类
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface SongListService extends IService<SongList> {

    boolean insert(HttpServletRequest request);

    boolean update(HttpServletRequest request);

    boolean delete(Integer id);

    SongList selectById(Integer id);

    List<SongList> selectAll();

    List<SongList> selectByTitle(HttpServletRequest request);

    List<SongList> selectLikeByTitle(HttpServletRequest request);

    List<SongList> selectByStyle(HttpServletRequest request);
}
