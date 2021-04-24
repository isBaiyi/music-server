package com.baiyi.service.impl;

import com.baiyi.entity.Comment;
import com.baiyi.mapper.CommentMapper;
import com.baiyi.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean insert(HttpServletRequest request) {
        Comment comment = conversion(request);
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        Comment comment = conversion(request);
        comment.setId(Integer.parseInt(request.getParameter("id").trim()));
        return commentMapper.updateById(comment) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return commentMapper.deleteById(id) > 0;
    }

    @Override
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectList(null);
    }

    @Override
    public List<Comment> selectBySongId(Integer songId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id", songId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<Comment> selectBySongListId(Integer songListId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id", songListId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public boolean like(HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(request.getParameter("id")));
        comment.setUp(Integer.parseInt(request.getParameter("up")));
        return commentMapper.updateById(comment) > 0;
    }

    @Override
    public boolean selectByUserId(Integer userId, Integer songListId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("song_list_id", songListId);
        return commentMapper.selectList(wrapper).size() > 0;
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 前端评论对象
     */
    public Comment conversion(HttpServletRequest request){
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(request.getParameter("userId").trim()));
        int type = Integer.parseInt(request.getParameter("type").trim());
        comment.setType(type);
        if (type == 0){
            comment.setSongId(Integer.parseInt(request.getParameter("songId").trim()));
        } else {
            comment.setSongListId(Integer.parseInt(request.getParameter("songListId").trim()));
        }
        comment.setContent(request.getParameter("content").trim());
//        comment.setUp(Integer.parseInt(request.getParameter("up").trim()));
        return comment;
    }
}
