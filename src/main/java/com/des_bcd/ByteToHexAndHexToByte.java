package com.des_bcd;

import java.io.UnsupportedEncodingException;

/**
 * 字节数组转16进制和16进制转字节数组、字符串转16进制
 *
 * @author liuxiaobin
 * @creat 2018-09-11 15:39
 */
public class ByteToHexAndHexToByte {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 方法一：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for (byte b : bytes) { // 使用除与取余进行转换
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }

    /**
     * 方法二：
     * byte[] to hex string
     *
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            System.out.println("ch:" + ch);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码)
     *
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(HEX_CHAR[bit]);
            bit = bs[i] & 0x0f;
            sb.append(HEX_CHAR[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static String toChineseHex(String s)

    {

        String ss = s;

        byte[] bt = ss.getBytes();

        String s1 = "";

        for (int i = 0; i < bt.length; i++)

        {

            String tempStr = Integer.toHexString(bt[i]);

            if (tempStr.length() > 2)

                tempStr = tempStr.substring(tempStr.length() - 2);

            s1 = s1 + tempStr + " ";

        }

        return s1.toUpperCase();

    }


    /**
     * 测试test
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = "严".getBytes("utf-8");
        System.out.println("方法一：" + byteToHex(bytes));
        System.out.println("方法二：" + str2HexStr("严"));
        System.out.println(toChineseHex("严"));
        System.out.println(strTo16("严"));
    }
}
