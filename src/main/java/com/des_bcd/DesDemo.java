package com.des_bcd;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * des算法
 *
 * @author liuxiaobin
 * @creat 2018-09-07 10:41
 */
public class DesDemo {
    private static String password = "CCBXJAIX";//密钥

    //测试
    public static void main(String args[]) {
        //待加密内容
        String str = "12:47:23";

        String result = DesDemo.encrypt(str);

        BASE64Encoder base64en = new BASE64Encoder();
       // String strs = new String(base64en.encode(result.getBytes()));
        System.out.println("加密后：" + result);
        //直接将如上内容解密
        try {
            String decryResult = DesDemo.decryptor(result);
            System.out.println("解密后：" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @param data
     * @return
     * @throws Exception
     * @Method: encrypt
     * @Description: 加密数据
     * @date 2016年7月26日
     */
    public static String encrypt(String data) {  //对string进行BASE64Encoder转换
        byte[] bt = encryptByKey(data.getBytes(), password);
       // BASE64Encoder base64en = new BASE64Encoder();
       // String strs = new String(base64en.encode(bt));
        String strs = new String(bt);
        return strs;
    }

    /**
     * @param data
     * @return
     * @throws Exception
     * @Method: encrypt
     * @Description: 解密数据
     * @date 2016年7月26日
     */
    public static String decryptor(String data) throws Exception {  //对string进行BASE64Encoder转换
        sun.misc.BASE64Decoder base64en = new sun.misc.BASE64Decoder();
        byte[] bt = decrypt(base64en.decodeBuffer(data), password);
        String strs = new String(bt);
        return strs;
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param key        String
     * @return byte[]
     */
    private static byte[] encryptByKey(byte[] datasource, String key) {
        try {
             SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //设置偏移量
            //IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src byte[]
     * @param key String
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src, String key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 初始化向量
        //IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
        // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
}
