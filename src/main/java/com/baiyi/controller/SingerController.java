package com.baiyi.controller;


import com.baiyi.entity.Singer;
import com.baiyi.service.SingerService;
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
        boolean result = singerService.insert(request);

        if (result) {
            return ResponseUtil.successRsp("添加成功");
        }
        return ResponseUtil.failRsp("添加失败");
    }

    /**
     * 修改歌手
     * @param request http 请求
     * @return 结果
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        boolean result = singerService.update(request);
        if (result) {
            return ResponseUtil.successRsp("修改成功");
        }
        return ResponseUtil.failRsp("修改失败");
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
        // 删除磁盘中的图片，释放资源
        FileUtil.deleteImg(singerService.selectById(Integer.parseInt(request.getParameter("id"))).getPic());
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
            // 先删除原先磁盘中的歌曲图标
            FileUtil.deleteImg(singerService.selectById(id).getPic());
            // 上传文件
            avatarFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);
            boolean result = singerService.updateById(singer);
            if (result){
                return ResponseUtil.successRsp("上传成功", "pic", storeAvatarPath);
            }
            return ResponseUtil.failRsp("上传失败");
        } catch (IOException e) {
            return ResponseUtil.failRsp("上传失败" + e.getMessage());
        }
    }
}

