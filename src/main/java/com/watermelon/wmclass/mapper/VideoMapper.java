package com.watermelon.wmclass.mapper;

import com.watermelon.wmclass.domain.Video;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: video 数据访问层
 * @Author; Watermelon
 * @Date: 2018/12/12 11:19
 */
public interface VideoMapper {

    @Select("select * from video")
    List<Video> findAll();
}
