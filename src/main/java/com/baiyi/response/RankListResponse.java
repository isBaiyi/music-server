package com.baiyi.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class RankListResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String singerName;
    private Integer singerId;
    private String songName;
    private Integer songId;
    private String songUrl;
    private String songPic;
    private String songLyric;
    private Long SongPlayCount;
}
