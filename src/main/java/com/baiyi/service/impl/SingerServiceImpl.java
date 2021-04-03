package com.baiyi.service.impl;

import com.baiyi.entity.Singer;
import com.baiyi.mapper.SingerMapper;
import com.baiyi.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 歌手 服务实现类
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean insert(HttpServletRequest request) {
        Singer singer = conversion(request);
        singer.setPic(request.getParameter("pic").trim());
        return singerMapper.insert(singer) > 0;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        Singer singer = conversion(request);
        singer.setId(Integer.parseInt(request.getParameter("id")));
        return singerMapper.updateById(singer) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return singerMapper.deleteById(id) > 0;
    }

    @Override
    public Singer selectById(Integer id) {
        return singerMapper.selectById(id);
    }

    @Override
    public List<Singer> selectAll() {
        return singerMapper.selectList(null);
    }

    @Override
    public List<Singer> selectByName(String name) {
        return singerMapper.selectList(new QueryWrapper<Singer>().like("name", name));
    }

    @Override
    public List<Singer> selectBySex(Integer sex) {
        return singerMapper.selectList(new QueryWrapper<Singer>().like("sex", sex));
    }

    @Override
    public boolean updateById(Singer singer) {
        return singerMapper.updateById(singer) > 0;
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 歌手对象
     */
    public Singer conversion(HttpServletRequest request){
        Singer singer = new Singer();
        singer.setName(request.getParameter("name").trim());
        singer.setSex(new Byte(request.getParameter("sex").trim()));
        singer.setLocation(request.getParameter("location").trim());
        singer.setIntroduction(request.getParameter("introduction").trim());
        String birth = request.getParameter("birth").trim();
        // 把生日转换为 Date 格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        singer.setBirth(birthDate);
        return singer;
    }
}
