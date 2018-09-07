package com.des_bcd;

/**
 * 测试新疆交法加密测试
 *
 * @author liuxiaobin
 * @creat 2018-09-07 10:34
 */
public class DesBcdDemo {
    public static void main(String[] args) {
       // byte[] b = str2Bcd("11");
         String b="ADAB";
        System.out.println(HextoBcd(b));
    }

    /**
     * @功能: BCD码转为10进制串(阿拉伯数据)
     * @参数: BCD码
     * @结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
                .toString().substring(1) : temp.toString();
    }

    /**
     * @功能: 10进制串转为BCD码
     * @参数: 10进制串
     * @结果: BCD码
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }
    /**
     * 10进制转bcd
     * @param str 10进制数字 String.valueOf(int number);将10进制数字转成字符串传入此参数
     * @return bcd码
     */
    public static String DecimaltoBcd(String str){
        String b_num="";
        for (int i = 0; i < str.length(); i++) {

            String b = Integer.toBinaryString(Integer.parseInt(str.valueOf(str.charAt(i))));

            int b_len=4-b.length();

            for(int j=0;j<b_len;j++){
                b="0"+b;
            }
            b_num+=b;
        }

        return b_num;
    }
    /**
     * 16进制转bcd
     * 将16进制转成10进制，再将10进制转成bcd
     * @param hex 16进制数字String.valueOf(int number);这里忽略16进制的前缀0x，只转后面的数字为字符串类型，将16进制数字转成字符串传入此参数
     * @return bcd码
     */
    public static String HextoBcd(String hex){

        int decimal = Integer.parseInt(hex,16);

        String bcd = DecimaltoBcd(String.valueOf(decimal));

        return bcd;
    }
}
