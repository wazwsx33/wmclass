package com.watermelon.wmclass.utils;

import com.watermelon.wmclass.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Description: JWT 工具类
 * @Author; Watermelon
 * @Date: 2018/12/13 15:52
 */
public class JwtUtils {

    private static final String SUBJECT = "wmclass";
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;  //过期时间为一周
    //密钥
    private static final String APPSECRET = "wm666";

    /**
     * 生成JWT
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {

        if (user == null || user.getId() == null || user.getName() == null
                || user.getHeadImg() == null)
            return null;

        return Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET)
                .compact();
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {

        try {

            return Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ignored) {

        }

        return null;
    }
}
