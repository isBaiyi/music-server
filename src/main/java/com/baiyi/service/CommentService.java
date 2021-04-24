package com.baiyi.service;

import com.baiyi.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
public interface CommentService extends IService<Comment> {

    boolean insert(HttpServletRequest request);

    boolean update(HttpServletRequest request);

    boolean delete(Integer id);

    Comment selectById(Integer id);

    List<Comment> selectAll();

    List<Comment> selectBySongId(Integer songId);

    List<Comment> selectBySongListId(Integer songListId);

    boolean like(HttpServletRequest request);

    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @return 评论
     */
    boolean selectByUserId(Integer userId, Integer songListId);
}
