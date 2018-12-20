package com.watermelon.wmclass.mapper;

import com.watermelon.wmclass.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/20 16:12
 */
public interface UserMapper {

    /**
     * 根据主键id查找
     * @param userId
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") int userId);

    /**
     * 根据openid查找用户
     * @param openid
     * @return
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User findByOpenid(@Param("openid") String openid);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    @Insert("INSERT INTO user(openid, name, head_img, phone, sign, sex, city, create_time) VALUES(#{openid}, #{name}, #{headImg}, #{phone}, #{sign}, #{sex}, #{city}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(User user);
}
