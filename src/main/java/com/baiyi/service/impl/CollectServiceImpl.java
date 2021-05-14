package com.baiyi.service.impl;

import com.baiyi.entity.Collect;
import com.baiyi.mapper.CollectMapper;
import com.baiyi.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 收藏 服务实现类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean insert(HttpServletRequest request) {
        return collectMapper.insert(conversion(request)) > 0;
    }

    @Override
    public boolean delete(Integer userId, Integer songId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("song_id", songId);
        return collectMapper.delete(wrapper) > 0;
    }

    @Override
    public List<Collect> selectAll() {
        return collectMapper.selectList(null);
    }

    @Override
    public List<Collect> selectByUserId(Integer userId) {
        return collectMapper.selectList(new QueryWrapper<Collect>().eq("user_id", userId));
    }

    @Override
    public int existSongId(HttpServletRequest request) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", Integer.parseInt(request.getParameter("userId").trim()));
        wrapper.eq("song_id", Integer.parseInt(request.getParameter("songId").trim()));
        return collectMapper.selectCount(wrapper);
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 收藏对象
     */
    public Collect conversion(HttpServletRequest request){
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(request.getParameter("userId").trim()));
        int type = Integer.parseInt(request.getParameter("type").trim());
        collect.setType(type);
        if (type == 0){
            collect.setSongId(Integer.parseInt(request.getParameter("songId").trim()));
        } else {
            collect.setSongListId(Integer.parseInt(request.getParameter("songListId").trim()));
        }
        return collect;
    }
}
