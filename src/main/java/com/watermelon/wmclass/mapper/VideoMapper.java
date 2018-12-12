package com.watermelon.wmclass.mapper;

import com.watermelon.wmclass.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: video 数据访问层
 * @Author; Watermelon
 * @Date: 2018/12/12 11:19
 */
public interface VideoMapper {

    @Select("SELECT * FROM video")
    List<Video> findAll();

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video findById(int id);

    @Update("UPDATE video SET title = #{title} WHERE id = #{id}")
    int update(Video video);

    @Delete("DELETE FROM video WHERE id = #{id}")
    int delete(int id);

    @Insert("INSERT INTO video(title, summary, cover_img, view_num, price, create_time, online, point) VALUES (#{title}, #{summary}, #{coverImg}, #{viewNum}, #{price}, #{createTime}, #{online}, #{point})")
    //通过该注解将数据库生成的ID赋值给实体类，以便使用
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Video video);
}
