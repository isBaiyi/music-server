package com.baiyi.service;

import com.baiyi.entity.Song;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 歌曲 服务类
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface SongService extends IService<Song> {

    boolean insert(HttpServletRequest request, Song song);

    boolean update(HttpServletRequest request);

    boolean delete(Integer id);

    Song selectById(Integer id);

    List<Song> selectAll();

    /**
     * 根据歌曲名字查询列表
     * @param name 名字
     * @return 歌曲列表
     */
    List<Song> selectByName(String name);

    /**
     * 根据歌曲名字模糊列表
     * @param name 名字
     * @return 歌曲列表
     */
    List<Song> selectByNameLike(String name);

    /**
     * 根据歌手id查询歌曲列表
     * @param singerId 歌手id
     * @return 歌曲列表
     */
    List<Song> selectBySingerId(Integer singerId);

}
