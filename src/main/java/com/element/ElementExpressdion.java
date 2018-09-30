package com.element;

/**
 * 三元表达式
 *
 * @author liuxiaobin
 * @creat 2018-09-29 9:47
 */
public class ElementExpressdion {

    public static void main(String[] args) {
        int a = 20;
        int b = 23;
        int max = (a > b) ? a : b;
        System.out.println(max);
        int i = 60;
        int j = i & 15;
        System.out.println(j);
        int x = 60;
        //  > >     :     右移运算符，num >> 1,相当于num除以2
        //x = x >> 4;
        String k1 = (j <= 9) ? (String.valueOf(j)) : (char) (j - 10) + "";
        System.out.println(k1);
        i = i >> 4;
        int z = i;
        z = i & 15;
        String k2 = (z <= 9) ? z + "" : (char) (z - 10) + "";
        System.out.println(k1 + k2);
    }
}
