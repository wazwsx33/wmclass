package com.watermelon.wmclass.controller.admin;

import com.watermelon.wmclass.domain.Video;
import com.watermelon.wmclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/12 15:17
 */

@RestController
@RequestMapping(value = "admin/v1/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;
    /**
     * 根据ID删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping(value = "del_by_id")
    public Object deleteById(@RequestParam(value = "video_id", required = true) int videoId) {
        return videoService.delete(videoId);
    }

    /**
     * 根据ID更新视频
     * @param video
     * @return
     */
    @PutMapping( value = "update_by_id")
    public Object update(@RequestBody Video video) {

        return videoService.update(video);
    }

    /**
     * 保存视频对象
     * @param video
     * @return
     */
    @PostMapping(value = "save")
    public Object save(@RequestBody Video video) {

        return videoService.save(video);
    }
}
