package com.watermelon.wmclass;

import com.watermelon.wmclass.domain.User;
import com.watermelon.wmclass.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/13 16:08
 */
public class CommonTest {

    @Test
    public void testGeneJwt() {
        User user = new User();
        user.setId(999);
        user.setHeadImg("www.wmclass.net");
        user.setName("wm");

        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    public void testCheck() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3bWNsYXNzIiwiaWQiOjk5OSwibmFtZSI6IndtIiwiaW1nIjoid3d3LndtY2xhc3MubmV0IiwiaWF0IjoxNTQ0Njg4NzIxLCJleHAiOjE1NDUyOTM1MjF9.TxWf88SYCV2-blHfyMsGYA2btbtzqbQAOMlNvau67Lo";

        Claims claims = JwtUtils.checkJWT(token);

        if (claims != null) {
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            int id = (int) claims.get("id");
            System.out.println(name);
            System.out.println(img);
            System.out.println(id);

        } else {
            System.out.println("非法token");
        }
    }
}
