package com.baiyi.utils;

import java.io.File;

/**
 * @author liaozc
 * @date 2021/4/15 14:39
 **/
public class FileUtil {
    /**
     * 删除磁盘中原先的图片文件
     *
     * @param imgName 图片名称
     */
    public static void deleteImg(String imgName) {
        String[] split = imgName.split("/");
        // 获取当前项目的路径 + 图片的名称
        String filename = System.getProperty("user.dir") + File.separator + split[0] + File.separator + split[1] + File.separator + split[2];
        new File(filename).delete();
    }

    /**
     * 删除磁盘中的歌曲
     *
     * @param songName 歌曲名称
     */
    public static void deleteSong(String songName) {
        String[] split = songName.split("/");
        String filename = System.getProperty("user.dir") + split[0] + File.separator + split[1];
        new File(filename).delete();
    }

    /**
     * 删除磁盘中用户的图片
     *
     * @param imgName 图片名称
     */
    public static void deleteConsumerImg(String imgName) {
        String[] split = imgName.split("/");
        // 获取当前项目的路径 + 图片的名称
        String filename = System.getProperty("user.dir") + File.separator + split[0] + File.separator + split[1];
        new File(filename).delete();
    }


    public static void main(String[] args) {
        // 删除图片
//        String fileName = "img/songPic/1615885318322tubiao11.jpg";
//        String[] split = fileName.split("/");
//        System.out.println(System.getProperty("user.dir") + File.separator + split[0] + File.separator + split[1] + File.separator + split[2]);
//        System.out.println(new File(fileName).delete());

        String fileName = "song/16156216361771599231114624十年11.mp3";
        String[] split = fileName.split("/");
        String filename = System.getProperty("user.dir") + File.separator + split[0] + File.separator + split[1];
        System.out.println(filename);
        System.out.println(new File(filename).delete());
    }
}
