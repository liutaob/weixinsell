/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试日志，并完成日志的归档
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {


    @Test
    public void test1() {
        log.debug("debug..");
        log.info("info..");
        log.error("error..");
        String name = "liut";
        String password = "123456";
        log.info("name: " + name + ",password:" + password);
        log.info("name: {}, password: {}", name, password);
        log.warn("warn..");
    }
}
