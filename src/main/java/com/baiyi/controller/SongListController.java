package com.baiyi.controller;


import com.alibaba.fastjson.JSONObject;
import com.baiyi.service.SongListService;
import com.baiyi.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 歌单 前端控制器
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 新增歌单
     * @param request 前端传进来的参数
     * @return 结果
     */
    @PostMapping("/insert")
    public Object insert(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        boolean flag = songListService.insert(request);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "保存失败");
        return jsonObject;
    }

    /**
     * 修改歌单信息
     * @param request 前端传进来的参数
     * @return 结果
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        boolean result = songListService.update(request);
        if (result) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 根据id查询歌单
     * @param request
     * @return
     */
    @PostMapping("/selectBySongListId")
    public Object selectBySongListId(HttpServletRequest request) {
        return songListService.selectById(Integer.parseInt(request.getParameter("id").trim()));
    }

    /**
     * 查询所有歌单
     * @param request
     * @return
     */
    @PostMapping("/selectAll")
    public Object selectAll(HttpServletRequest request) {
        return songListService.selectAll();
    }

    /**
     * 根据标题精确查询歌单列表
     * @param request
     * @return
     */
    @PostMapping("/selectByTitle")
    public Object selectByTitle(HttpServletRequest request){
        return songListService.selectByTitle(request);
    }

    /**
     * 根据标题模糊查询歌单列表
     * @param request
     * @return
     */
    @PostMapping("/selectLikeByTitle")
    public Object selectLikeByTitle(HttpServletRequest request){
        return songListService.selectLikeByTitle(request);
    }

    /**
     * 根据歌单风格进行模糊查询
     * @param request
     * @return
     */
    @PostMapping("/selectByStyle")
    public Object selectByStyle(HttpServletRequest request) {
        return songListService.selectByStyle(request);
    }
}

