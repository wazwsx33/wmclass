package com.watermelon.wmclass.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: 微信配置类
 * @Author; Watermelon
 * @Date: 2018/12/11 17:11
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeChatConfig {

    /**
     * 公众号appId
     */
    @Value("${wxpay.appid}")
    private String appId;

    /**
     * 公众号密钥
     */
    @Value("${wxpay.appsecret}")
    private String appsecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
