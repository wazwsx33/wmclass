package com.watermelon.wmclass.controller;

import com.watermelon.wmclass.config.WeChatConfig;
import com.watermelon.wmclass.domain.JsonData;
import com.watermelon.wmclass.domain.User;
import com.watermelon.wmclass.domain.VideoOrder;
import com.watermelon.wmclass.service.UserService;
import com.watermelon.wmclass.service.VideoOrderService;
import com.watermelon.wmclass.utils.JwtUtils;
import com.watermelon.wmclass.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

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
    @Autowired
    private VideoOrderService videoOrderService;

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

    /**
     * 微信支付回调，微信支付模式二，第十步
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "order/callback")
    //回调时使用的是post方式
    public void orderCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getInputStream();

        //BufferedReader是包装设计模式，性能更高
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }
        bufferedReader.close();
        inputStream.close();

        Map<String ,String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
        System.out.println(callbackMap.toString());

        SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

        //判断签名是否正确
        if (WXPayUtil.isCorrectSign(sortedMap, weChatConfig.getKey())){
            //判断微信的状态码
            if ("SUCCESS".equals(sortedMap.get("result_code"))){

                String outTradeNo = sortedMap.get("out_trade_no");

                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);

                if (dbVideoOrder != null && dbVideoOrder.getState() == 0){  //判断逻辑看业务场景
                    VideoOrder videoOrder = new VideoOrder();
                    videoOrder.setOpenid(sortedMap.get("openid"));  //用户在商户下的唯一标识
                    videoOrder.setOutTradeNo(outTradeNo);
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setState(1);
                    int rows = videoOrderService.updateVideoOrderByOutTradeNo(videoOrder);
                    if (rows == 1) {  //需要通知微信，订单处理成功
                        response.setContentType("text/xml");
                        response.getWriter().println("success");
                        return ;
                    }
                }
            }

        }
        //都处理失败
        response.setContentType("text/xml");
        response.getWriter().println("fail");

    }
}
