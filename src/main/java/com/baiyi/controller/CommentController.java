package com.baiyi.controller;


import com.baiyi.service.CommentService;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/insert")
    public Object insert(HttpServletRequest request) {
        if (commentService.insert(request)) {
            return ResponseUtil.successRsp("评论成功！");
        } else {
            return ResponseUtil.failRsp("评论失败！");
        }
    }

    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        if (commentService.update(request)) {
            return ResponseUtil.successRsp("修改成功");
        } else {
            return ResponseUtil.failRsp("修改失败");
        }
    }

    @GetMapping("/delete")
    public Object delete(HttpServletRequest request) {
        if (commentService.delete(Integer.parseInt(request.getParameter("id").trim()))) {
            return ResponseUtil.successRsp("删除成功");
        } else {
            return ResponseUtil.failRsp("删除失败");
        }
    }

    @GetMapping("/selectById")
    public Object selectById(HttpServletRequest request) {
        return commentService.selectById(Integer.parseInt(request.getParameter("id").trim()));
    }

    @PostMapping("/selectAll")
    public Object selectAll() {
        return commentService.selectAll();
    }

    @PostMapping("/selectBySongId")
    public Object selectBySongId(HttpServletRequest request) {
        return commentService.selectBySongId(Integer.parseInt(request.getParameter("songId").trim()));
    }

    @GetMapping("/selectBySongListId")
    public Object selectBySongListId(HttpServletRequest request) {
        return commentService.selectBySongListId(Integer.parseInt(request.getParameter("songListId").trim()));
    }

    @PostMapping("/like")
    public Object like(HttpServletRequest request){
        if (commentService.like(request)) {
            return ResponseUtil.successRsp("点赞成功");
        } else {
            return ResponseUtil.failRsp("点赞失败");
        }
    }

}

