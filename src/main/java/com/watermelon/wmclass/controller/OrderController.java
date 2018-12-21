package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单接口
 * @Author; Watermelon
 * @Date: 2018/12/21 10:46
 */
@RestController
@RequestMapping(value = "user/api/v1/order")
public class OrderController {

    @GetMapping(value = "add")
    public JsonData saveOrder() {
        return JsonData.buildSuccess("下单成功");
    }
}
