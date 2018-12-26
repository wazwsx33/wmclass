package com.watermelon.wmclass.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Description: 常用工具类封装，md5，uuid等
 * @Author; Watermelon
 * @Date: 2018/12/11 16:16
 */
public class CommonUtils {

    /**
     * 生成uuid，即用来标识一笔订单，也用作 nonce_str
     * @return
     */
    public static String generateUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * md5常用工具类
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] array = md5.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }

            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
