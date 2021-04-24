package com.baiyi.service.impl;

import com.baiyi.entity.Rank;
import com.baiyi.mapper.RankMapper;
import com.baiyi.service.RankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评价 服务实现类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class RankServiceImpl extends ServiceImpl<RankMapper, Rank> implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public boolean insert(HttpServletRequest request) {
        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(request.getParameter("songListId").trim()));
        rank.setConsumerId(Integer.parseInt(request.getParameter("consumerId").trim()));
        rank.setScore(Integer.parseInt(request.getParameter("score").trim()));
        return rankMapper.insert(rank) > 0;
    }

    @Override
    public int selectScoreNum(Integer songListId) {
        return rankMapper.selectScoreNum(songListId);
    }

    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    @Override
    public int rankOfSongListId(Integer songListId) {
        // 评分人数
        int rankNum = rankMapper.selectRankNum(songListId);
        if (rankNum == 0){
            return 0;
        }
        return rankMapper.selectScoreNum(songListId) / rankNum;
    }
}
