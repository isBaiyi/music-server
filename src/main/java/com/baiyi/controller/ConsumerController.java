package com.baiyi.controller;


import com.alibaba.fastjson.JSONObject;
import com.baiyi.entity.Consumer;
import com.baiyi.service.ConsumerService;
import com.baiyi.utils.FileUtil;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 前端用户 前端控制器
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/insert")
    public Object insert(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        if (request.getParameter("username") == null || "".equals(request.getParameter("username"))) {
            return ResponseUtil.failRsp("用户名不能为空");
        }
        if (request.getParameter("password") == null || "".equals(request.getParameter("password"))) {
            return ResponseUtil.failRsp("密码不能为空");
        }
        boolean result = consumerService.insert(request);
        if (result) {
            return ResponseUtil.successRsp("添加成功");
        }
        return ResponseUtil.failRsp("添加失败");
    }

    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        boolean result = consumerService.update(request);
        if (result) {
            return ResponseUtil.successRsp("修改成功");
        }
        return ResponseUtil.failRsp("修改失败");
    }

    @PostMapping("/delete")
    public Object delete(HttpServletRequest request) {
        FileUtil.deleteConsumerImg(consumerService.selectById(Integer.parseInt(request.getParameter("id"))).getAvatar());
        return consumerService.delete(Integer.parseInt(request.getParameter("id")));
    }

    @PostMapping("/selectById")
    public Consumer selectById(HttpServletRequest request){
        return consumerService.selectById(Integer.parseInt(request.getParameter("id")));
    }

    @PostMapping("/selectAll")
    public List<Consumer> selectAll(){
        return consumerService.selectAll();
    }

    /**
     * 更新前端用户图片
     * @param avatarFile 图片
     * @param id 主键ID
     * @return 结果
     */
    @PostMapping("/updateConsumerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()){
            return ResponseUtil.failRsp("文件上传失败");
        }
        // 文件名 = 当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") +
                System.getProperty("file.separator") + "avatarImages";
        // 如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的相对文件地址
        String storeAvatarPath = "/avatarImages/" + fileName;
        try {
            // 先删除磁盘中之前的用户图片
            FileUtil.deleteConsumerImg(consumerService.selectById(id).getAvatar());
            // 上传文件
            avatarFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvatar(storeAvatarPath);
            boolean result = consumerService.updateById(consumer);
            if (result){
                return ResponseUtil.successRsp("上传成功", "avatar", storeAvatarPath);
            }
            return ResponseUtil.failRsp("上传失败");
        } catch (IOException e) {
            return ResponseUtil.failRsp("上传失败" + e.getMessage());
        }
    }
}

