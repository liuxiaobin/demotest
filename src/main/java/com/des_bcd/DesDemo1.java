package com.des_bcd;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.spec.KeySpec;

/**
 * @Author bxLiu
 * @Date 2018/9/11
 * @ClassName DesDemo1
 */
public class DesDemo1 {

    //  private static final Logger Log = LoggerFactory.getLogger(HcyktCryptUtils.class);
    // private static final String Algorithm_3DES = "DESede"; // 定义 加密算法,可用 3DES
    private static final String Algorithm_3DES = "DES"; // 定义 加密算法,可用DES
    // DES,DESede,Blowfish
    private static final String hexString = "0123456789ABCDEF";

    private static final String key = "FFFFFFFFFFFFFFFF";
    // PBE加密
    static final String ALGORITHM = "PBEWithMD5AndDES";
    static Cipher cipher;

    /**
     * 3DES加密
     *
     * @param key 加密密钥，长度为24字节
     * @param src 字节数组(根据给定的字节数组构造一个密钥。 )
     * @return
     */
    public static String encrypt_3des(String key, byte[] src) {
        try {
            // 根据给定的字节数组和算法构造一个密钥
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm_3DES);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm_3DES);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] c2 = c1.doFinal(src);
            return encode(c2);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param key 密钥
     * @param src 需要解密的数据,即数据库中取出的加密后的密码字段
     * @return
     */
    public static String decrypt_3des(String key, String src) {
        try {
            byte[] sc = decode(src);
            // byte[] sc =Base64.encode(src.getBytes());
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm_3DES);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm_3DES);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return (new String(c1.doFinal(sc)));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * MD5算法
     *
     * @param data 要md5的数据
     * @return MD5后的数据(16进制大写数据形式)
     */
    public static String MD5(String data) {
        MessageDigest messageDigest = null;
        byte[] md5Byte;
        String md5 = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data.getBytes());
            md5Byte = messageDigest.digest();
            md5 = encode(md5Byte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
//            Log.error("MD5运算错误");
//            Log.error("MD5数据：" + data);
            System.out.println("MD5运算错误");
            System.out.println("MD5数据：" + data);
        }
        return md5.toUpperCase();
    }

    /**
     * 字节数组转为16进制
     *
     * @param bytes
     * @return
     */
    public static String encode(byte[] bytes) {
        // 根据默认编码获取字节数组

        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /**
     * @param bytes
     * @return 将16进制数字解码成字节数组, 适用于所有字符（包括中文）
     */
    public static byte[] decode(String bytes) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return baos.toByteArray();
    }


    /**
     * PBOC_DES加密
     *
     * @param HexString 字符串（16位16进制字符串）
     * @param keyStr    密钥16个1
     * @throws Exception
     */
    public static byte[] SEncrypt_DES(byte[] HexString, byte[] keyStr) throws Exception {
        try {
            byte[] theCph = new byte[8];
            try {
                byte[] theKey = null;
                byte[] theMsg = null;
                theMsg = HexString;
                theKey = keyStr;
                KeySpec ks = new DESKeySpec(theKey);
                SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
                SecretKey ky = kf.generateSecret(ks);
                Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
                cf.init(Cipher.ENCRYPT_MODE, ky);
                theCph = cf.doFinal(theMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return theCph;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * PBOC-DES解密
     *
     * @param hexStr 16位十六进制字符串
     * @param keyStr 密钥16个1
     * @throws Exception 返回是byte[]
     */
    public static byte[] SDecrypt_DES(byte[] hexStr, byte[] keyStr) throws Exception {
        try {
            String algorithm = "DES/ECB/NoPadding";
            byte[] theCph = new byte[8];
            byte[] theKey = null;
            byte[] theMsg = null;
            theMsg = hexStr;
            theKey = keyStr;
            KeySpec ks = new DESKeySpec(theKey);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher cf = Cipher.getInstance(algorithm);
            cf.init(Cipher.DECRYPT_MODE, ky);
            theCph = cf.doFinal(theMsg);
            return theCph;
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     * PBOCDES加密
     *
     * @param shuju:加密的数据的byte[]
     *
     * @param key:密钥 16位十六进制
     *
     * @param IV:初始向量，默认为0000000000000000
     */
    public static String Get_PBOC_DES(byte[] shuju, String key, String IV) {
        String returntype = "";
        try {
            // ----------------------------------------
            byte[] keyss = new byte[8];
            byte[] IVS = new byte[8];
            keyss = decode(key);
            IVS = decode(IV);
            // ----------------------------------------
            byte[] keys = keyss;
            // 数据内容字节数组
            String slshuju = encode(shuju);
            int TLen = 0;
            int DBz = 0;
            if (slshuju.length() % 16 != 0 || slshuju.length() % 16 == 0) {
                TLen = (((int) (slshuju.length() / 16)) + 1) * 16;
                DBz = (slshuju.length() / 16) + 1;
                slshuju = slshuju + "8";
                TLen = TLen - slshuju.length();
                for (int i = 0; i < TLen; i++) {
                    slshuju = slshuju + "0";
                }
            }
            byte[] Zshuju = new byte[slshuju.length() / 2];
            Zshuju = decode(slshuju);

            byte[] D1 = new byte[8];
            byte[] D2 = new byte[8];
            byte[] I2 = new byte[8];
            byte[] I3 = new byte[8];
            byte[] bytTemp = new byte[8];
            byte[] bytTempX = new byte[8];
            // 初始向量
            byte[] I0 = IVS;
            if (DBz >= 1) {
                for (int i = 0; i < 8; i++) {
                    D1[i] = Zshuju[i];
                }
                for (int i = 0; i < 8; i++) {
                    bytTemp[i] = (byte) (I0[i] ^ D1[i]);
                }
                I2 = bytTemp;
                bytTempX = SEncrypt_DES(I2, keys);
            }
            if (DBz >= 2) {
                for (int j = 2; j <= DBz; j++) {
                    for (int i = (j - 1) * 8; i < j * 8; i++) {
                        D2[i - (j - 1) * 8] = Zshuju[i];
                    }
                    for (int i = 0; i < 8; i++) {
                        bytTemp[i] = (byte) (bytTempX[i] ^ D2[i]);
                    }
                    I3 = bytTemp;
                    bytTempX = SEncrypt_DES(I3, keys);
                }
            }
            returntype = encode(bytTempX);

        } catch (Exception e) {
            returntype = "";
        }
        return returntype;
    }

    /*
     * 根据字符串生成密钥字节数组
     *
     * @param keyStr 密钥字符串
     *
     * @return
     *
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组
        /*
         * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if (key.length > temp.length) {
            // 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            // 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    /**
     * des
     * 20170315
     * DES加密
     *
     * @param HexString 字符串（16位16进制字符串）
     * @param keyStr    密钥16个1
     * @throws Exception
     */
    public static String ENCRYPTMethod(String HexString, String keyStr) throws Exception {
        try {
            byte[] theKey = null;
            byte[] theMsg = null;
            theMsg = hexToBytes(HexString);
            theKey = hexToBytes(keyStr);
            KeySpec ks = new DESKeySpec(theKey);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
            cf.init(Cipher.ENCRYPT_MODE, ky);
            byte[] theCph = cf.doFinal(theMsg);
            System.out.println("*************DES加密****************");
            System.out.println("密钥    : " + bytesToHex(theKey));
            System.out.println("字符串: " + bytesToHex(theMsg));
            System.out.println("加密后: " + bytesToHex(theCph));
            return bytesToHex(theCph);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES解密
     *
     * @param hexStr 16位十六进制字符串
     * @param keyStr 密钥16个1
     * @throws Exception
     */
    public static String DECRYPTMethod(String hexStr, String keyStr) {
        //无填充
        String algorithm = "DES/ECB/NoPadding";
        try {
            byte[] theKey = null;
            byte[] theMsg = null;
            theMsg = hexToBytes(hexStr);
            theKey = hexToBytes(keyStr);
            KeySpec ks = new DESKeySpec(theKey);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher cf = Cipher.getInstance(algorithm);
            cf.init(Cipher.DECRYPT_MODE, ky);
            byte[] theCph = cf.doFinal(theMsg);
            System.out.println("*************DES解密****************");
            System.out.println("密钥    : " + bytesToHex(theKey));
            System.out.println("字符串: " + bytesToHex(theMsg));
            System.out.println("解密后: " + bytesToHex(theCph));
            return bytesToHex(theCph);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 16进制转字节
     * @param str
     * @return
     */
    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    /**
     * 字节转16进制
     * @param data
     * @return
     */
    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16)
                    str = str + "0" + Integer.toHexString(data[i] & 0xFF);
                else
                    str = str + Integer.toHexString(data[i] & 0xFF);
            }
            return str.toUpperCase();
        }
    }

    /**
     * 字符串转换为16进制字符串
     *
     * @param s
     * @return
     */
    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    /**
     * @param str 转成16进制的串
     * @return 位数不够16的倍数 后面添加80 +00+...直到够16的倍数
     */
    public static String addDesStr(String str) {
        String stradd = stringToHexString(str);
        int a = stradd.length();
        int b = a % 16;
        int c = 16 - b;
        if (b == 0 || c == 16) {
            return stradd + "8000000000000000";
        } else {
            stradd = stradd + 8;
            if (stradd.length() != 0 || stradd.length() != 16) {
                for (int i = 0; i < c - 1; i++) {
                    stradd = stradd + 0;
                }
            }
        }
        return stradd;
    }


    /**
     * @param str des加密数据
     * @return 位数是整数的16倍，判断最后8的位置来截取16进制的串
     */
    public static String deleteDesStr(String str) {
        if (str.indexOf("80") != -1) {
            String deleteStr = str.substring(0, str.lastIndexOf("80"));
            deleteStr = DesDemo1.hexStringToString(deleteStr);
            return deleteStr;
        } else {
            String deleteStr = DesDemo1.hexStringToString(str);
            return deleteStr;
        }
    }


    /**
     * @param str 中文转成16进制的串
     * @return 位数不够16的倍数 后面添加80 +00+...直到够16的倍数
     * 说明先转成byte 再转成16进制
     */
    public static String addDesHexString(String str) {
        String stradd = str;
        int a = stradd.length();
        int b = a % 16;
        int c = 16 - b;
        if (b == 0 || c == 16) {
            return stradd + "8000000000000000";
        } else {
            stradd = stradd + 8;
            if (stradd.length() != 0 || stradd.length() != 16) {
                for (int i = 0; i < c - 1; i++) {
                    stradd = stradd + 0;
                }
            }
        }
        return stradd;
    }

    /**
     * @param HexString des加密数据
     * @return 位数是整数的16倍，判断最后8的位置来截取16进制的串
     * 说明先转成16byte进行截取 截取完以后再进行16进制转new String
     */
    public static String deleteHexString(String HexString) {
        if (HexString.indexOf("80") != -1) {
            String deleteStr = HexString.substring(0, HexString.lastIndexOf("80"));
            return hexStringToString(deleteStr);
        } else {
            return hexStringToString(HexString);
        }
    }

    public static void main(String[] args) {
        String key = "FFFFFFFFFFFFFFFF";
        String str = "6217004140005733229";
        int a = 4000;
        String stradd = encode(str.getBytes());//468756FC1DB0A97E4589CE9C4BA0E292
        try {
            ////System.out.println(Integer.toHexString(a));
            //System.out.println(new String(HcyktCryptUtils.decode(str),"GBK"));
            String b = DesDemo1.ENCRYPTMethod(addDesHexString(str), key);
            System.out.println("a=" + b);
//			//System.out.println("加密:"+a);
//			String b=CryptUtils.DECRYPTMethod("B6786427B4CEE7163B424430260BD497036A65AE8BF7BEF69DBDC88891B88309C17C1B69E0BD2C30", key);
//			//String a=CryptUtils.deleteDesStr(b);
//			b=CryptUtils.hexStringToString(b);
//			System.out.println("解密:"+b);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

