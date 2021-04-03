package com.baiyi.service.impl;

import com.baiyi.entity.Comment;
import com.baiyi.mapper.CommentMapper;
import com.baiyi.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
