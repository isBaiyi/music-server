package com.baiyi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 定位歌手头像、歌曲图片、歌曲、歌单图片地址
 *
 * @author 白衣
 * @date 2021/2/26
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 歌手头像地址定位
        registry.addResourceHandler("/img/singerPic/**")
                // 通过 java 的方法进行获取到img的目录 这里兼容了 Linux 系统
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic" +
                        System.getProperty("file.separator"));
        // 歌曲图片地址定位
        registry.addResourceHandler("/img/songPic/**")
                // 通过 java 的方法进行获取到img的目录 这里兼容了 Linux 系统
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic" +
                        System.getProperty("file.separator"));
        // 歌单图片地址定位
        registry.addResourceHandler("/img/songListPic/**")
                // 通过 java 的方法进行获取到img的目录 这里兼容了 Linux 系统
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic" +
                        System.getProperty("file.separator"));
        // 歌曲地址定位
        registry.addResourceHandler("/song/**")
                // 通过 java 的方法进行获取到img的目录 这里兼容了 Linux 系统
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") + "song" +
                        System.getProperty("file.separator"));
    }
}
