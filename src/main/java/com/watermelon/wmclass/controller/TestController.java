package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.config.WeChatConfig;
import com.watermelon.wmclass.domain.JsonData;
import com.watermelon.wmclass.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/11 17:16
 */
@RestController
public class TestController {

    @GetMapping(value = "test")
    public String test() {
        return "hello 123";
    }

    @Autowired
    private WeChatConfig weChatConfig;

    @GetMapping(value = "test_config")
    public JsonData testConfig() {
        return JsonData.buildSuccess(weChatConfig.getAppId());
    }

    @Autowired
    private VideoMapper videoMapper;

    @GetMapping(value = "test_db")
    public Object testDB() {
        return videoMapper.findAll();
    }
}
