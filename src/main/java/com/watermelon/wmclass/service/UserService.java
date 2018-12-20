package com.watermelon.wmclass.service;

import com.watermelon.wmclass.domain.User;

/**
 * @Description: 用户业务接口类
 * @Author; Watermelon
 * @Date: 2018/12/20 15:05
 */
public interface UserService {

    User saveWeChatUser(String code);
}
