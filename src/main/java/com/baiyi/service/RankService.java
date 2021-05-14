package com.baiyi.service;

import com.baiyi.entity.Rank;
import com.baiyi.response.RankListResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评价 服务类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface RankService extends IService<Rank> {

    boolean insert(HttpServletRequest request);

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

    /**
     * 计算平均分
     * @param songListId 歌单ID
     * @return 平均分
     */
    int rankOfSongListId(Integer songListId);

    List<RankListResponse> getRankList();
}
