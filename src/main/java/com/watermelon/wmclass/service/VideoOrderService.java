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
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 根据流水号查找订单
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 根据流水号更新订单
     * @param videoOrder
     * @return
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}
