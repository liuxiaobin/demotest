package com.equal;

/**
 * 测试
 *
 * @author liuxiaobin
 * @creat 2018-11-07 16:14
 */
public class EqualsToDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee("张三", "男");
        Employee e2 = new Employee("张三", "男");
        System.out.println(e1.equals(e2));
    }

}
