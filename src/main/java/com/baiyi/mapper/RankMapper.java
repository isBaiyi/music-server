package com.baiyi.mapper;

import com.baiyi.entity.Rank;
import com.baiyi.response.RankListResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 评价 Mapper 接口
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Repository
public interface RankMapper extends BaseMapper<Rank> {

    /**
     * 查询歌单总分
     * @param songListId 歌单ID
     * @return 分数
     */
    int selectScoreNum(Integer songListId);

    /**
     * 查询歌单参与评分总人数
     * @param songListId 歌单ID
     * @return 人数
     */
    int selectRankNum(Integer songListId);

    List<RankListResponse> getRankList();

}
