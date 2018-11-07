package com.equal;

/**
 * equals和等于的区别
 * == : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象。(基本数据类型 == 比较的是值，引用数据类型 == 比较的是内存地址)
 * equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况：
 * <p>
 * 情况 1：类没有覆盖 equals()方法。则通过 equals() 比较该类的两个对象时，等价于通过“==”比较这两个对象。
 * 情况 2：类覆盖了 equals()方法。一般，我们都覆盖 equals() 方法来两个对象的内容相等；若它们的内容相等，则返回 true (即，认为这两个对象相等)。
 * <p>
 * 说明：
 * <p>
 * String 中的 equals 方法是被重写过的，因为 object 的 equals 方法是比较的对象的内存地址，而 String 的 equals 方法比较的是对象的值。
 * 当创建 String 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 String 对象。
 *
 * @author liuxiaobin
 * @creat 2018-11-07 15:14
 */
public class EqualsTo {
    public static void main(String[] args) {
        String a = new String("ab");
        String b = new String("ab");
        String aa = "ab";
        String bb = "ab";
        if (a == b) {
            System.out.println("a==b");
        } else if (a.equals(b)) {
            System.out.println("a.equals(b)");
        } else if  (aa==bb){
            System.out.println("aa==bb");
        }else {
            System.out.println("aa.equals(bb)");
        }

    }
}
