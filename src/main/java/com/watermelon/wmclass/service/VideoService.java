package com.watermelon.wmclass.service;

import com.watermelon.wmclass.domain.Video;

import java.util.List;

/**
 * @Description: 视频 业务类接口
 * @Author; Watermelon
 * @Date: 2018/12/11 16:14
 */
public interface VideoService {

    List<Video> findAll();

    Video findById(int id);

    int update(Video video);

    int delete(int id);

    int save(Video video);
}
