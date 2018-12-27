package com.watermelon.wmclass.service;

import com.watermelon.wmclass.domain.VideoOrder;
import com.watermelon.wmclass.dto.VideoOrderDto;

/**
 * @Description: 订单接口
 * @Author; Watermelon
 * @Date: 2018/12/27 09:47
 */
public interface VideoOrderService {

    /**
     * 下单接口
     * @param videoOrderDto
     * @return
     */
    VideoOrder save(VideoOrderDto videoOrderDto) throws Exception;
}
