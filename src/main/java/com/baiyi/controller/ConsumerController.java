package com.baiyi.controller;


import com.alibaba.fastjson.JSONObject;
import com.baiyi.entity.Consumer;
import com.baiyi.entity.Singer;
import com.baiyi.service.ConsumerService;
import com.baiyi.utils.Consts;
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
        if (request.getParameter("username") == null || request.getParameter("username").equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }
        boolean result = consumerService.insert(request);
        if (result) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        boolean result = consumerService.update(request);
        if (result) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    @PostMapping("/delete")
    public Object delete(HttpServletRequest request) {
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
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
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
            // 上传文件
            avatarFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvatar(storeAvatarPath);
            boolean result = consumerService.updateById(consumer);
            if (result){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("avatar", storeAvatarPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
        }
        return jsonObject;
    }
}

