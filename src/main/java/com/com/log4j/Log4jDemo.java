package com.com.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志
 *
 * @author liuxiaobin
 * @creat 2018-09-11 17:27
 */
public class Log4jDemo {
   private Logger logger = LoggerFactory.getLogger(Log4jDemo.class);

    public void getSay(){
        logger.info("测试日志");
    }

    public static void main(String[] args) {
        Log4jDemo l = new Log4jDemo();
        l.getSay();
    }
}
