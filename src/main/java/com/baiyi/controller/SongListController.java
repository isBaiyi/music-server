package com.baiyi.controller;


import com.baiyi.entity.SongList;
import com.baiyi.service.SongListService;
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

/**
 * 歌单 前端控制器
 *
 * @author 白衣
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 新增歌单
     * @param request 前端传进来的参数
     * @return 结果
     */
    @PostMapping("/insert")
    public Object insert(HttpServletRequest request){
        boolean flag = songListService.insert(request);
        if (flag) {
            return ResponseUtil.successRsp("保存成功");
        }
        return ResponseUtil.failRsp("保存失败");
    }

    /**
     * 修改歌单信息
     * @param request 前端传进来的参数
     * @return 结果
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        boolean result = songListService.update(request);
        if (result) {
            return ResponseUtil.failRsp("修改成功");
        }
        return ResponseUtil.failRsp("修改失败");
    }

    /**
     * 删除歌单
     * @param request http 请求
     * @return 结果
     */
    @PostMapping("/delete")
    public Object delete(HttpServletRequest request) {
        FileUtil.deleteImg(songListService.selectById(Integer.parseInt(request.getParameter("id"))).getPic());
        return songListService.delete(Integer.parseInt(request.getParameter("id")));
    }
    /**
     * 根据id查询歌单
     * @param request
     * @return
     */
    @PostMapping("/selectBySongListId")
    public Object selectBySongListId(HttpServletRequest request) {
        return songListService.selectById(Integer.parseInt(request.getParameter("id").trim()));
    }

    /**
     * 查询所有歌单
     * @param request
     * @return
     */
    @PostMapping("/selectAll")
    public Object selectAll(HttpServletRequest request) {
        return songListService.selectAll();
    }

    /**
     * 根据标题精确查询歌单列表
     * @param request
     * @return
     */
    @PostMapping("/selectByTitle")
    public Object selectByTitle(HttpServletRequest request){
        return songListService.selectByTitle(request);
    }

    /**
     * 根据标题模糊查询歌单列表
     * @param request
     * @return
     */
    @PostMapping("/selectLikeByTitle")
    public Object selectLikeByTitle(HttpServletRequest request){
        return songListService.selectLikeByTitle(request);
    }

    /**
     * 根据歌单风格进行模糊查询
     * @param request
     * @return
     */
    @PostMapping("/selectByStyle")
    public Object selectByStyle(HttpServletRequest request) {
        return songListService.selectByStyle(request);
    }

    /**
     * 更新歌单图片
     * @param avatarFile 图片
     * @param id 主键ID
     * @return 结果
     */
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") int id){
        if (avatarFile.isEmpty()){
            return ResponseUtil.failRsp("文件上传失败");
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
            FileUtil.deleteImg(songListService.selectById(id).getPic());
            // 上传文件
            avatarFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatarPath);
            boolean result = songListService.updateById(songList);
            if (result){
                return ResponseUtil.successRsp("上传成功", "pic", storeAvatarPath);
            }
            return ResponseUtil.failRsp("上传失败");
        } catch (IOException e) {
            return ResponseUtil.failRsp("上传失败" + e.getMessage());
        }
    }
}

