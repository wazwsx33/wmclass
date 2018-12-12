package com.watermelon.wmclass.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Description: mybatis分页插件配置
 * @Author; Watermelon
 * @Date: 2018/12/12 15:52
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();

        //设置为true时，会将RowBounds第一个参数offset当成PageNum页码使用
        p.setProperty("offsetAsPageNum", "true");

        //设置为true时，使用RowBounds分页会进行count查询
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);

        return pageHelper;
    }
}
