package com.bluesky.middleplatform.commons.utils;

public class CommonUtils {

    private CommonUtils() {

    }

    /**
     * 字符串转换成 int
     *
     * @param s
     * @return
     */
    public static int getIntFromString(String s) {
        int iRet = 0;
        if (s != null && !"".equals(s)) {
            iRet = Integer.parseInt(s);
        }
        return iRet;
    }

    /**
     * 将逗号分隔的字符串转换成 int[]
     *
     * @param str 逗号分隔的字符串
     * @return
     */
    public static int[] getStringsToIntAry(String str) {
        int[] values = null;
        if (!"".equals(str)) {
            String[] temps = str.split(",");
            values = new int[temps.length];
            int counts = 0;
            for (String temp : temps) {
                values[counts++] = CommonUtils.getIntFromString(temp);
            }
        } else {
            values = new int[0];
        }
        return values;
    }

}
