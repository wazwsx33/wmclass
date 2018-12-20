package com.watermelon.wmclass.service.impl;

import com.watermelon.wmclass.config.WeChatConfig;
import com.watermelon.wmclass.domain.User;
import com.watermelon.wmclass.mapper.UserMapper;
import com.watermelon.wmclass.service.UserService;
import com.watermelon.wmclass.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/20 15:06
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWeChatUser(String code) {

        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(), weChatConfig.getOpenAppid(), weChatConfig.getOpenAppsecret(), code);

        //获取access_token
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if (baseMap == null || baseMap.isEmpty())
            return null;
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");

        User dbUser = userMapper.findByOpenid(openId);
        if (dbUser != null) { //更新用户，直接返回
            return dbUser;
        }

        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(), accessToken, openId);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (baseUserMap == null || baseUserMap.isEmpty())
            return null;

        String nickname = new String(((String) baseUserMap.get("nickname")).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);  //解决乱码

        Double sexTemp = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();  //性别类型转换
        String province = new String(((String) baseUserMap.get("province")).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String city = new String(((String) baseUserMap.get("city")).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String country = new String(((String) baseUserMap.get("country")).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String headimgurl = (String) baseUserMap.get("headimgurl");

        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = sb.toString();

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());

        userMapper.save(user);

        return user;
    }
}
