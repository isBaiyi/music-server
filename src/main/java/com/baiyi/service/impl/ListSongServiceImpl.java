package com.baiyi.service.impl;

import com.baiyi.entity.ListSong;
import com.baiyi.entity.Song;
import com.baiyi.mapper.ListSongMapper;
import com.baiyi.service.ListSongService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 歌单包含歌曲列表 服务实现类
 * </p>
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public boolean insert(HttpServletRequest request) {
        ListSong listSong = conversion(request);
        return listSongMapper.insert(listSong) > 0;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        ListSong listSong = conversion(request);
        return listSongMapper.update(listSong, new QueryWrapper<ListSong>().eq("id", request.getParameter("id").trim())) > 0;
    }

    @Override
    public boolean delete(HttpServletRequest request) {
        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id", Integer.parseInt(request.getParameter("songListId").trim()));
        wrapper.eq("song_id", Integer.parseInt(request.getParameter("songId").trim()));
        return listSongMapper.delete(wrapper) > 0;
    }

    @Override
    public List<ListSong> selectBySongListId(Integer id) {
        return listSongMapper.selectList(new QueryWrapper<ListSong>().eq("song_list_id", id));
    }

    @Override
    public List<ListSong> selectAll() {
        return listSongMapper.selectList(null);
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 歌单歌曲信息
     */
    public ListSong conversion(HttpServletRequest request){
        ListSong listSong = new ListSong();
        listSong.setSongListId(Integer.parseInt(request.getParameter("songListId").trim()));
        listSong.setSongId(Integer.parseInt(request.getParameter("songId").trim()));
        return listSong;
    }
}
