package com.baiyi.controller;


import com.baiyi.service.RankService;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评价 前端控制器
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    @PostMapping("/insert")
    public Object insert(HttpServletRequest request) {
        if (rankService.insert(request)) {
            return ResponseUtil.successRsp("评价成功");
        }
        return ResponseUtil.failRsp("评价失败");
    }

    /**
     * 查询评价总分
     * @param request 前台传进来的参数
     * @return 总分数
     */
    @PostMapping("/selectScoreNum")
    public Object selectScoreNum(HttpServletRequest request) {
        return rankService.selectScoreNum(Integer.parseInt(request.getParameter("songListId").trim()));
    }

    /**
     * 查询评价总人数
     * @param request 前台传进来的参数
     * @return 总人数
     */
    @PostMapping("/selectRankNum")
    public Object selectRankNum(HttpServletRequest request) {
        return rankService.selectRankNum(Integer.parseInt(request.getParameter("songListId").trim()));
    }

    /**
     * 查询评价平均分
     * @param request 前台传进来的参数
     * @return 平均分
     */
    @PostMapping("/rankOfSongListId")
    public Object rankOfSongListId(HttpServletRequest request) {
        return rankService.rankOfSongListId(Integer.parseInt(request.getParameter("songListId").trim()));
    }

    /**
     * 获取排行榜
     * @return
     */
    @PostMapping("/getRankList")
    public Object getRankList(){
        return rankService.getRankList();
    }

}

