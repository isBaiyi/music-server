package com.baiyi.mapper;

import com.baiyi.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 歌曲 Mapper 接口
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Repository
public interface SongMapper extends BaseMapper<Song> {

    /**
     * 增加歌曲播放量
     * @param id 歌曲ID
     * @return
     */
    int increaseCount(Integer id);
}
