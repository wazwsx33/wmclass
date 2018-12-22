package com.watermelon.wmclass.mapper;

import com.watermelon.wmclass.domain.VideoOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: 订单dao层
 * @Author; Watermelon
 * @Date: 2018/12/22 10:12
 */
public interface VideoOrderMapper {

    /**
     * 保存订单，返回包含主键
     *
     * @param videoOrder
     * @return
     */
    @Insert("INSERT INTO video_order(openid, out_trade_no, state, create_time, notify_time, total_fee, nickname, head_img, video_id, video_title, video_img, user_id, ip, del) VALUES(#{openid}, #{outTradeNo}, #{state}, #{createTime}, #{notifyTime}, #{totalFee}, #{nickname}, #{headImg}, #{videoId}, #{videoTitle}, #{videoImg}, #{userId}, #{ip}, #{del})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(VideoOrder videoOrder);

    /**
     * 根据主键查找订单
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM video_order WHERE id=#{order_id} and del=0")
    VideoOrder findById(@Param("order_id") int id);

    /**
     * 根据订单流水号获取订单对象
     *
     * @param outTradeNo
     * @return
     */
    @Select("SELECT * FROM video_order WHERE out_trade_no=#{out_trade_no} and del=0")
    VideoOrder findByOutTradeNo(@Param("out_trade_no") String outTradeNo);

    /**
     * 逻辑删除订单
     *
     * @param id
     * @param userId
     * @return
     */
    @Update("UPDATE video_order set del=0 WHERE id=#{id} and user_id=#{userId}")
    int del(@Param("id") int id, @Param("userId") int userId);

    /**
     * 查找用户全部订单
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM video_order WHERE user_id=#{userId}")
    List<VideoOrder> findMyOrderList(int userId);

    /**
     * 根据订单流水号更新
     *
     * @param videoOrder
     * @return
     */
    @Update("UPDATE video_order SET state=#{state}, notify_time=#{notifyTime}, openid=#{openid} WHERE out_trade_no=#{outTradeNo} and state=0 and del=0")
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}
