package com.baiyi.controller;


import com.baiyi.service.CollectService;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/insert")
    public Object insert(HttpServletRequest request) {
        String songId = request.getParameter("songId").trim();
        if (songId.equals("") || songId == null){
            return ResponseUtil.failRsp("收藏歌曲为空");
        }
        if (collectService.existSongId(request) > 0){
            return ResponseUtil.failRsp("2", "已收藏");
        }
        if (collectService.insert(request)) {
            return ResponseUtil.successRsp("收藏成功");
        }
        return ResponseUtil.failRsp("收藏失败");
    }

    @PostMapping("/delete")
    public Object delete(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId").trim());
        int songId = Integer.parseInt(request.getParameter("songId").trim());
        if (collectService.delete(userId, songId)) {
            return ResponseUtil.successRsp("删除成功");
        }
        return ResponseUtil.failRsp("删除失败");
    }

    @PostMapping("/selectAll")
    public Object selectAll(HttpServletRequest request){
        return collectService.selectAll();
    }

    @PostMapping("/selectByUserId")
    public Object selectByUserId(HttpServletRequest request) {
        return collectService.selectByUserId(Integer.parseInt(request.getParameter("userId")));
    }

}

