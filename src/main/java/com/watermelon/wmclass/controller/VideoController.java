package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.domain.Video;
import com.watermelon.wmclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/11 15:32
 */
@RestController
@RequestMapping(value = "api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping(value = "page")
    public Object pageVideo() {
        return videoService.findAll();
    }

    @GetMapping(value = "find_by_id")
    public Object findById(int videoId) {
        return videoService.findById(videoId);
    }

    @DeleteMapping(value = "del_by_id")
    public Object deleteById(int videoId) {
        return videoService.delete(videoId);
    }

    @PutMapping( value = "update_by_id")
    public Object update(int videoId, String title) {

        Video video = new Video();
        video.setId(videoId);
        video.setTitle(title);

        return videoService.update(video);
    }

    @PostMapping(value = "save")
    public Object save(String title) {
        Video video = new Video();
        video.setTitle(title);

        return videoService.save(video);
    }
}
