package com.watermelon.wmclass.provider;

import com.watermelon.wmclass.domain.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Description: video 构建动态sql语句
 * @Author; Watermelon
 * @Date: 2018/12/12 15:23
 */
public class VideoProvider {

    /**
     * 更新video动态语句
     * @param video
     * @return
     */
    public String updateVideo(final Video video) {
        return new SQL(){
            {
                UPDATE("video");

                //条件写法
                if(video.getTitle() != null) {
                    SET("title=#{title}");
                }
                if(video.getSummary() != null) {
                    SET("summary=#{summary}");
                }
                if(video.getCoverImg() != null) {
                    SET("cover_img=#{coverImg}");
                }
                if(video.getViewNum() != null) {
                    SET("view_num=#{viewNum}");
                }
                if(video.getPrice() != null) {
                    SET("price=#{price}");
                }
                if(video.getOnline() != null) {
                    SET("online=#{online}");
                }
                if(video.getPoint() != null) {
                    SET("point=#{point}");
                }

                WHERE("id=#{id}");
            }
        }.toString();
    }
}
