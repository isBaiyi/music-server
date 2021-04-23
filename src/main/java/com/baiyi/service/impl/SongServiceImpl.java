package com.baiyi.service.impl;

import com.baiyi.entity.Song;
import com.baiyi.mapper.SongMapper;
import com.baiyi.service.SongService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 歌曲 服务实现类
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public boolean insert(HttpServletRequest request, Song song) {
        song.setSingerId(Integer.parseInt(request.getParameter("singerId").trim()));
        song.setPic("/img/songPic/tubiao.jpg");
        song.setName(request.getParameter("name").trim());
        song.setIntroduction(request.getParameter("introduction").trim());
        song.setLyric(request.getParameter("lyric").trim());  // 歌词
        return songMapper.insert(song) > 0;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        Song song = conversion(request);
        song.setId(Integer.parseInt(request.getParameter("id")));
        return songMapper.updateById(song) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return songMapper.deleteById(id) > 0;
    }

    @Override
    public Song selectById(Integer id) {
        return songMapper.selectById(id);
    }

    @Override
    public List<Song> selectAll() {
        return songMapper.selectList(null);
    }

    @Override
    public List<Song> selectByName(String name) {
        return songMapper.selectList(new QueryWrapper<Song>().eq("name", name));
    }

    @Override
    public List<Song> selectByNameLike(String name) {
        return songMapper.selectList(new QueryWrapper<Song>().like("name", name));
    }

    @Override
    public List<Song> selectBySingerId(Integer singerId) {
        return songMapper.selectList(new QueryWrapper<Song>().like("singer_id", singerId));
    }

    @Override
    public boolean updateById(Song song) {
        return songMapper.updateById(song) > 0;
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 歌曲对象
     */
    public Song conversion(HttpServletRequest request){
        Song song = new Song();
        song.setSingerId(Integer.parseInt(request.getParameter("id").trim()));
        song.setName(request.getParameter("name").trim());
        song.setIntroduction(request.getParameter("introduction").trim());
        song.setLyric(request.getParameter("lyric").trim());  // 歌词
        return song;
    }
}
