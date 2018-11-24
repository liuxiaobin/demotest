package com.operator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxiaobin
 * @creat 2018-11-20 16:47
 */
public class Test {
    public Map<String, Object> getSjjlMap(String sjjl) {
        System.out.println("实际缴费记录：【" + sjjl + "】");
        Map<String, Object> result = new HashMap<String, Object>();
        String[] list = sjjl.split(";");
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && !("").equals(list[i])) {
                String[] qfmx = list[i].split("\\|");
                result.put(qfmx[0] + "|" + qfmx[2], qfmx[4]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Test n = new Test();
        String sjjl = ";2018|2018-2019期间|sf02|住宿费|900.0|0";
        System.out.println(n.getSjje(sjjl, "2018|sf02"));
    }

    public String getSjje(String sjjl, String key) {
        String str = "0.00";
        Map<String, Object> result = getSjjlMap(sjjl);
        String sjje = (String) result.get(key);
        System.out.println("收费项目：【" + key + "】" + "实缴金额：【" + sjje + "】");
        if ("null".equals(sjje) || sjje == null) {
            str = "0.00";
        } else {
            str = sjje;
        }
        return str;
    }
}
