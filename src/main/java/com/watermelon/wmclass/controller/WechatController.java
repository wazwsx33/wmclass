package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.config.WeChatConfig;
import com.watermelon.wmclass.domain.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/18 15:59
 */

@Controller
@RequestMapping(value = "api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;
    /**
     * 拼装微信扫一扫登录url
     * @return
     */
    @GetMapping(value = "login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl();  //获取开放平台重定向地址

        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK");  //进行编码
        String qrcodeUrl = String.format(WeChatConfig.getOpenQrcodeUrl(), weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }
}
