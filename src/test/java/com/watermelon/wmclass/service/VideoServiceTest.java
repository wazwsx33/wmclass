package com.watermelon.wmclass.service;

import com.watermelon.wmclass.domain.Video;
import com.watermelon.wmclass.mapper.VideoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/22 10:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void findAll() {
        List<Video> list = videoService.findAll();
        assertNotNull(list);
        for (Video video : list) {
            System.out.println(video.getTitle());
        }
    }

    @Test
    public void findById() {
        Video video = videoService.findById(1);
        assertNotNull(video);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}