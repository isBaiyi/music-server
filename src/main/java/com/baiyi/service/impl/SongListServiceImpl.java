package com.baiyi.service.impl;

import com.baiyi.entity.Song;
import com.baiyi.entity.SongList;
import com.baiyi.mapper.SongListMapper;
import com.baiyi.service.SongListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 歌单 服务实现类
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean insert(HttpServletRequest request) {
        SongList songList = conversion(request);
        return songListMapper.insert(songList) > 0;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        SongList songList = conversion(request);
        return songListMapper.update(songList, new QueryWrapper<SongList>().eq("id", request.getParameter("id".trim()))) > 0;
    }

    @Override
    public SongList selectById(Integer id) {
        return songListMapper.selectById(id);
    }

    @Override
    public List<SongList> selectAll() {
        return songListMapper.selectList(null);
    }

    @Override
    public List<SongList> selectByTitle(HttpServletRequest request) {
        return songListMapper.selectList(new QueryWrapper<SongList>().eq("title", request.getParameter("title").trim()));
    }

    @Override
    public List<SongList> selectLikeByTitle (HttpServletRequest request) {
        return songListMapper.selectList(new QueryWrapper<SongList>().like("title", request.getParameter("title").trim()));
    }

    @Override
    public List<SongList> selectByStyle(HttpServletRequest request) {
        return songListMapper.selectList(new QueryWrapper<SongList>().like("style", request.getParameter("style").trim()));
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 歌曲对象
     */
    public SongList conversion(HttpServletRequest request){
        SongList songList = new SongList();
        songList.setTitle(request.getParameter("title").trim());
        songList.setPic(request.getParameter("pic").trim());
        songList.setIntroduction(request.getParameter("introduction").trim());
        songList.setStyle(request.getParameter("style").trim());
        return songList;
    }
}
