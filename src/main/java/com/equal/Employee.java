package com.equal;

/**
 * 测试demo
 *
 * @author liuxiaobin
 * @creat 2018-11-07 16:12
 */
public class Employee {
    String name;
    String sex;

    public Employee(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
