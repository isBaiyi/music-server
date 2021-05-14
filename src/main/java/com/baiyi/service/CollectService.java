package com.baiyi.service;

import com.baiyi.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 收藏 服务类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface CollectService extends IService<Collect> {

    boolean insert(HttpServletRequest request);

    boolean delete(Integer userId, Integer songId);

    List<Collect> selectAll();

    /**
     * 查询某个用户到收藏列表
     * @param userId 用户ID
     * @return 用户收藏列表
     */
    List<Collect> selectByUserId(Integer userId);

    /**
     * 查询某个用户是否收藏了该歌曲
     * @param songId 歌曲ID
     * @return
     */
    int existSongId(HttpServletRequest request);
}
