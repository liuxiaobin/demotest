package com.innerclass;

/**
 * 内部类
 * @author liuxiaobin
 * @creat 2018-10-09 16:05
 */
public class TestInner {
    //全局变量
    private int number=100;
    public class Inner{
        private int number=200;
        public  void paint(){
            //局部变量
            int number = 500;
            //局部覆盖原则
            System.out.println(number);
            //通过this引用内部类的成员属性
            System.out.println(this.number);
            //通过外部类名this访问外部类的成员属性
            System.out.println(TestInner.this.number);
        }
    }

    public static void main(String[] args) {
        //内部类创建类对象需要两步
        TestInner inner = new TestInner();
        TestInner.Inner in = inner.new Inner();
        in.paint();
    }
}
