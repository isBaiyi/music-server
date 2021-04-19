package com.baiyi.controller;


import com.baiyi.entity.Song;
import com.baiyi.service.SongService;
import com.baiyi.utils.FileUtil;
import com.baiyi.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌曲 前端控制器
 *
 * @author 白衣
 * @since 2021-02-24
 */
@CrossOrigin
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     *
     * @param request 前端携带的参数
     * @param mpFile  MP3 文件
     * @return 是否添加成功
     */
    @PostMapping("/insert")
    public Object insert(HttpServletRequest request, @RequestParam("file") MultipartFile mpFile) {
        //上传歌曲文件
        if (mpFile.isEmpty()) {
            return ResponseUtil.failRsp("歌曲上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(request, song);
            if (flag) {
                return ResponseUtil.successRsp("保存成功", "avatar", storeUrlPath);
            }
            return ResponseUtil.failRsp("保存失败");
        } catch (IOException e) {
            return ResponseUtil.failRsp("保存失败" + e.getMessage());
        }
    }

    /**
     * 根据歌手id 查询歌曲
     * @param request 歌手id
     * @return 歌曲列表
     */
    @PostMapping("/selectBySingerId")
    public Object selectBySingerId(HttpServletRequest request){
        System.out.println(request.getParameter("id"));
        return songService.selectBySingerId(Integer.parseInt(request.getParameter("id")));
    }

    /**
     * 修改歌曲
     * @param request 前端传进来的参数
     * @return 结果
     */
    @PostMapping("/updateSong")
    public Object updateSong(HttpServletRequest request){
        boolean result = songService.update(request);
        if (result) {
            return ResponseUtil.successRsp("修改成功");
        }
        return ResponseUtil.failRsp("修改失败");
    }

    /**
     * 删除歌手
     * @param request 前端的请求
     * @return 结果
     */
    @PostMapping("/deleteSong")
    public Object deleteSong(HttpServletRequest request){
        FileUtil.deleteImg(songService.selectById(Integer.parseInt(request.getParameter("id"))).getPic());

        return songService.delete(Integer.parseInt(request.getParameter("id")));
    }

    /**
     * 更新歌曲图片
     * @param avatarFile 图片
     * @param id 主键ID
     * @return 结果
     */
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id") int id){
        if (avatarFile.isEmpty()){
            return ResponseUtil.failRsp("文件上传失败");
        }
        // 文件名 = 当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") +
                System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        // 如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的相对文件地址
        String storeAvatarPath = "/img/songPic/" + fileName;
        try {
            // 删除删除磁盘中的图片
            FileUtil.deleteImg(songService.selectById(id).getPic());
            // 上传文件
            avatarFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatarPath);
            boolean result = songService.updateById(song);
            if (result){
                return ResponseUtil.successRsp("上传成功", "pic", storeAvatarPath);
            }
            return ResponseUtil.failRsp("上传失败");
        } catch (IOException e) {
            return ResponseUtil.failRsp("上传失败" + e.getMessage());
        }
    }

    /**
     * 更新歌曲文件
     * @param avatarFile 图片
     * @param id 主键ID
     * @return 结果
     */
    @PostMapping("/updateSongUrl")
    public Object updateSongUrl(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id") int id){
        if (avatarFile.isEmpty()){
            return ResponseUtil.failRsp("文件上传失败");
        }
        // 文件名 = 当前时间到毫秒 + 原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir") + "song";
        // 如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的相对文件地址
        String storeAvatarPath = "song" + fileName;
        try {
            // 删除磁盘中之前的歌曲文件
            FileUtil.deleteSong(songService.selectById(id).getUrl());
            // 上传文件
            avatarFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatarPath);
            boolean result = songService.updateById(song);
            if (result){
                return ResponseUtil.successRsp("上传成功");
            }
            return ResponseUtil.failRsp("上传失败");
        } catch (IOException e) {
            return ResponseUtil.failRsp("文件上传失败" + e.getMessage());
        }
    }

    @PostMapping("selectById")
    public Object selectById(HttpServletRequest request){
        return songService.selectById(Integer.parseInt(request.getParameter("id").trim()));
    }

    @PostMapping("/selectByName")
    public Object selectByName(HttpServletRequest request) {
        System.out.println("songName = " + request.getParameter("songName").trim());
        return songService.selectByName(request.getParameter("songName").trim());
    }

    @PostMapping("/selectAll")
    public Object selectAll(){
        return songService.selectAll();
    }
}

