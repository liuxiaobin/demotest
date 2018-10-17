package com.innerclass;

/**
 * 内部接口
 *
 * @author liuxiaobin
 * @creat 2018-10-09 16:16
 */
public class TestInner2 {
    public interface Fly {
        void Fly();
    }

    public class Inner implements Fly {
        public void Fly() {
            System.out.println("I am SuperMan");
        }
    }

    public static void main(String[] args) {
        TestInner2 t2 = new TestInner2();
        TestInner2.Inner in = t2.new Inner();
        in.Fly();

    }
}
