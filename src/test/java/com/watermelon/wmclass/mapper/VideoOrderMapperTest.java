package com.watermelon.wmclass.mapper;

import com.watermelon.wmclass.domain.VideoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author; Watermelon
 * @Date: 2018/12/22 10:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoOrderMapperTest {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Test
    public void insert() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setDel(0);
        videoOrder.setTotalFee(111);
        videoOrder.setHeadImg("sdfadfas");
        videoOrder.setVideoTitle("springBoot高级视频教程");
        videoOrderMapper.insert(videoOrder);

        assertNotNull(videoOrder.getId());
    }

    @Test
    public void findById() {
        VideoOrder videoOrder = videoOrderMapper.findById(1);
        assertNotNull(videoOrder);
    }

    @Test
    public void findByOutTradeNo() {
    }

    @Test
    public void del() {
    }

    @Test
    public void findMyOrderList() {
    }

    @Test
    public void updateVideoOrderByOutTradeNo() {
    }
}