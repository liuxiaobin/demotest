package com.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * 测试
 *
 * @author liuxiaobin
 * @creat 2018-10-09 16:58
 */
@Setter
@Getter
@Log
public class LombokTest {
    private String name;
    private String p;

    public static void main(String[] args) {
        LombokTest lt = new LombokTest();
        log.info("i");
    }
}
