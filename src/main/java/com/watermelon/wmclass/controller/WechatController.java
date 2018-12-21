package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.config.WeChatConfig;
import com.watermelon.wmclass.domain.JsonData;
import com.watermelon.wmclass.domain.User;
import com.watermelon.wmclass.service.UserService;
import com.watermelon.wmclass.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private UserService userService;

    /**
     * 拼装微信扫一扫登录url
     * @param accessPage
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl();  //获取开放平台重定向地址

        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK");  //进行编码
        String qrcodeUrl = String.format(WeChatConfig.getOpenQrcodeUrl(), weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * 微信扫码登录，回调地址
     * @param code
     * @param state
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "user/callback")
    public void wechatUserCallback(@RequestParam(value = "code", required = true) String code,
                                   String state, HttpServletResponse response) throws IOException {
        User user = userService.saveWeChatUser(code);
        if (user != null) {
            //生成jwt
            String token = JwtUtils.geneJsonWebToken(user);
            //state 当前用户的页面地址，需要拼接http://，这样才不会站内跳转
            String url = state + "?token=" + token + "&head_img=" + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8");
            response.sendRedirect(url);
        }
    }
}
