package com.baiyi.controller;


import com.alibaba.fastjson.JSONObject;
import com.baiyi.entity.Singer;
import com.baiyi.service.SingerService;
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
 * 歌手 前端控制器
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     * @param request http 请求
     * @return 结果
     */
    @PostMapping("/insert")
    public Object insert(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        boolean result = singerService.insert(request);

        if (result) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    /**
     * 修改歌手
     * @param request http 请求
     * @return 结果
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        boolean result = singerService.update(request);
        if (result) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 删除歌手
     * @param request http 请求
     * @return 结果
     */
    @PostMapping("/delete")
    public Object delete(HttpServletRequest request) {
        System.out.println("id = " + request.getParameter("id"));
        System.out.println(Integer.parseInt(request.getParameter("id")));

        return singerService.delete(Integer.parseInt(request.getParameter("id")));
    }

    /**
     * 根据主键ID进行查询
     * @param request http 请求
     * @return 歌手信息
     */
    @PostMapping("/selectById")
    public Singer selectById(HttpServletRequest request){
        return singerService.selectById(Integer.parseInt(request.getParameter("id")));
    }

    /**
     * 查询所有歌手
     * @return 歌手列表
     */
    @PostMapping("/selectAll")
    public List<Singer> selectAll(){
        return singerService.selectAll();
    }

    /**
     * 根据歌手名字模糊查询歌手列表
     * @param request http 请求
     * @return 歌手列表
     */
    @PostMapping("/selectByName")
    public List<Singer> selectByName(HttpServletRequest request){
        return singerService.selectByName(request.getParameter("name"));
    }

    /**
     * 根据歌手性别查询歌手列表
     * @param request http 请求
     * @return 歌手列表
     */
    @PostMapping("/selectBySex")
    public List<Singer> selectBySex(HttpServletRequest request){
        return singerService.selectBySex(Integer.parseInt(request.getParameter("sex")));
    }

    /**
     * 更新歌手图片
     * @param avatarFile 图片
     * @param id 主键ID
     * @return 结果
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id") int id){
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
                System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        // 如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的相对文件地址
        String storeAvatarPath = "/img/singerPic/" + fileName;
        try {
            // 上传文件
            avatarFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);
            boolean result = singerService.updateById(singer);
            if (result){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeAvatarPath);
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

