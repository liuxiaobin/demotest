package com.uuid;

import cn.hutool.core.util.IdUtil;

/**
 * UUid测试Hootool
 *
 * @author liuxiaobin
 * @creat 2018-11-24 16:58
 */
public class UuidTest {
    private String Uuid;

    public    String getUuid() {
        Uuid = IdUtil.randomUUID();//带-的Uuid
        return Uuid;
    }

    public static void main(String[] args) {
        UuidTest uu = new UuidTest();
        System.out.println(uu.getUuid());
        UuidTest uu1 = new UuidTest();

        System.out.println(IdUtil.simpleUUID());//不带-的uuid
    }
}
