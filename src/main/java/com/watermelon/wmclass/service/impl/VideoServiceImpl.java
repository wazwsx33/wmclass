package com.watermelon.wmclass.service.impl;

import com.watermelon.wmclass.domain.Video;
import com.watermelon.wmclass.mapper.VideoMapper;
import com.watermelon.wmclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/11 16:15
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(int id) {
        return videoMapper.delete(id);
    }

    @Override
    public int save(Video video) {
        int rows = videoMapper.save(video);
        System.out.println("保存对象的ID:" + video.getId());
        return rows;
    }
}
