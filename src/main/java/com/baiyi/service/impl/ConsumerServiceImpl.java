package com.baiyi.service.impl;

import com.baiyi.entity.Consumer;
import com.baiyi.mapper.ConsumerMapper;
import com.baiyi.service.ConsumerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 前端用户 服务实现类
 *
 * @author 白衣
 * @since 2021-02-24
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Value("${jasypt.encryptor.password}")
    private String secretKey;

    @Override
    public boolean insert(HttpServletRequest request) {
        Consumer consumer = conversion(request);
        consumer.setAvatar(request.getParameter("avatar").trim());
        return consumerMapper.insert(consumer) > 0;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        Consumer consumer = conversion(request);
        consumer.setId(Integer.parseInt(request.getParameter("id").trim()));
        return consumerMapper.updateById(consumer) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return consumerMapper.deleteById(id) > 0;
    }

    @Override
    public Consumer selectById(Integer id) {
        return consumerMapper.selectById(id);
    }

    @Override
    public List<Consumer> selectAll() {
        return consumerMapper.selectList(null);
    }

    @Override
    public boolean updateById(Consumer consumer) {
        return consumerMapper.updateById(consumer) > 0;
    }

    /**
     * 把前端传进来的 http 请求转换为 对象
     * @param request http请求
     * @return 前端用户对象
     */
    public Consumer conversion(HttpServletRequest request){
        Consumer consumer = new Consumer();
        consumer.setUsername(request.getParameter("username").trim());
        consumer.setPassword(request.getParameter("password").trim());
        // todo 入库前密码加密
        consumer.setSex(new Byte(request.getParameter("sex").trim()));
        consumer.setPhoneNum(request.getParameter("phoneNum").trim());
        consumer.setEmail(request.getParameter("email").trim());
        String birth = request.getParameter("birth").trim();
        consumer.setIntroduction(request.getParameter("introduction").trim());
        consumer.setLocation(request.getParameter("location").trim());
        // 把生日转换为 Date 格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        consumer.setBirth(birthDate);
        return consumer;
    }
}
