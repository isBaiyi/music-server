package com.baiyi.controller;


import com.baiyi.service.ListSongService;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 歌单包含歌曲列表 前端控制器
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    @PostMapping("/insert")
    public Object insert(HttpServletRequest request) {
        boolean flag = listSongService.insert(request);
        if (flag) {
            return ResponseUtil.successRsp("添加成功");
        }
        return ResponseUtil.failRsp("添加失败");
    }

    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        boolean flag = listSongService.update(request);
        if (flag) {
            return ResponseUtil.successRsp("修改成功");
        }
        return ResponseUtil.failRsp("修改失败");
    }

    @PostMapping("/delete")
    public Object delete(HttpServletRequest request){
        return listSongService.delete(request);
    }

    /**
     * 根据歌单id查询歌单歌曲列表
     * @param request
     * @return
     */
    @PostMapping("/selectBySongListId")
    public Object selectBySongListId(HttpServletRequest request) {
        return listSongService.selectBySongListId(Integer.parseInt(request.getParameter("songListId").trim()));
    }

    /**
     * 查询歌单里所有的歌曲
     * @return
     */
    @PostMapping("/selectAll")
    public Object selectAll(){
        return listSongService.selectAll();
    }
}

