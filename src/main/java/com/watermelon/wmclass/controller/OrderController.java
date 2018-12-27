package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.domain.JsonData;
import com.watermelon.wmclass.dto.VideoOrderDto;
import com.watermelon.wmclass.service.VideoOrderService;
import com.watermelon.wmclass.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 订单接口
 * @Author; Watermelon
 * @Date: 2018/12/21 10:46
 */
@RestController
/*@RequestMapping(value = "user/api/v1/order")*/
@RequestMapping(value = "api/v1/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    @GetMapping(value = "add")
    public JsonData saveOrder(@RequestParam(value = "video_id", required = true) int videoId, HttpServletRequest request) throws Exception {

        String ip = IpUtils.getIpAddr(request);
        //int userId = request.getAttribute("user_id");
        int userId = 4;
        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);

        videoOrderService.save(videoOrderDto);

        return JsonData.buildSuccess("下单成功");
    }
}
