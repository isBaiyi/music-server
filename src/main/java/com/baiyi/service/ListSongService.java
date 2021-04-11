package com.baiyi.service;

import com.baiyi.entity.ListSong;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 歌单包含歌曲列表 服务类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */

public interface ListSongService extends IService<ListSong> {

    /**
     * 新增
     * @param request
     * @return
     */
    boolean insert(HttpServletRequest request);

    /**
     * 修改
     * @param request
     * @return
     */
    boolean update(HttpServletRequest request);

    /**
     * 删除
     * @param request
     * @return
     */
    boolean delete(HttpServletRequest request);

    /**
     * 根据歌单id查询歌单里的歌曲列表
     * @param id
     * @return
     */
    List<ListSong> selectBySongListId(Integer id);

    /**
     * 查询歌单歌曲里所有数据
     * @return
     */
    List<ListSong> selectAll();
}
