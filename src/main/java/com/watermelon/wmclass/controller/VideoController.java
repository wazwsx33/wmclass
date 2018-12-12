package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: video 接口
 * @Author; Watermelon
 * @Date: 2018/12/11 15:32
 */
@RestController
@RequestMapping(value = "api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 分页接口
     *
     * @param page 当前第几页，默认是第一页
     * @param size 每页显示几条，默认10条
     * @return
     */
    @GetMapping(value = "page")
    public Object pageVideo(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {

        return videoService.findAll();
    }

    /**
     * 根据ID查找视频
     *
     * @param videoId
     * @return
     */
    @GetMapping(value = "find_by_id")
    public Object findById(@RequestParam(value = "video_id", required = true) int videoId) {
        return videoService.findById(videoId);
    }
}
