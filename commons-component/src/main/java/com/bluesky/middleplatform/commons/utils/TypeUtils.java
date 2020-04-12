package com.bluesky.middleplatform.commons.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TypeUtils {

    public static final int HOUR_UNIT = 1;
    public static final int DAY_UNIT = 2;
    public static final int WEEK_UNIT = 3;
    public static final int MONTH_UNIT = 4;
    public static final int BY_TASK = 1;
    public static final int BY_RESOURCE = 2;
    public static final int BY_RESOURCE_TYPE = 3;
    public static final int BY_PROJECT = 4;
    public static final int BY_NON_PROJECT_TYPE = 5;
    public static final int BY_EXPENSE_TYPE = 6;
    public static final int BY_BENEFIT_TYPE = 7;
    public static final int BY_PHASE = 8;
    public static final int BY_REPORT_BY = 9;
    public static final int BY_OUTLINE = 10;
    public static final int BY_TYPE = 11;
    public static final int BY_PRODUCT = 12;
    public static final int NONE = 1;
    public static final int GREEN = 2;
    public static final int YELLOW = 3;
    public static final int RED = 4;
    public static final int COMPLETED = -1;
    private static long current = System.currentTimeMillis();

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_HOUR_FORMAT = "yyyy-MM-dd hha";

    private static final String FORMAT_PATTERN_DEFAULT = "#0.00";

    public static final String DEFAULT_DECIMAL_FORMAT = "##############0.00";

    public static final String DEFAULT_WORKTIME_FORMAT = "##############0.0";

    public static String DEFAULT_PERCENT_FORMAT = "##############0";

    /**
     * 私有构造器
     */
    private TypeUtils() {

    }

    public static synchronized long getUniqueID() {
        return current++;
    }

    public static String nullToString(String inString) {
        return (inString == null) || ("null".equalsIgnoreCase(inString.trim())) ? ""
                : inString.trim();
    }

    public static String nullToString(String inString, String defaultString) {
        return (inString == null) || ("null".equalsIgnoreCase(inString.trim())) ? defaultString
                : inString.trim();
    }

    public static String nullToString(Object inObject) {
        return (inObject == null)
                || ("null".equalsIgnoreCase(inObject.toString().trim())) ? ""
                : inObject.toString();
    }

    public static String joinString(String[] ss, String split) {
        StringBuffer sb = new StringBuffer();
        if ((ss != null) && (ss.length > 0)) {
            for (String s : ss) {
                if (sb.length() > 0) {
                    sb.append(split);
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public static String getUTFString(Object inObject) {
        try {
            return (inObject == null)
                    || ("null".equalsIgnoreCase(inObject.toString().trim())) ? ""
                    : new String(inObject.toString().getBytes("ISO8859-1"));
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 将数组转换成List
     *
     * @param array
     * @return
     */
    public static List<Object> arrayToList(Object[] array) {
        List<Object> result = new ArrayList<Object>();
        try {
            if (array != null) {
                for (Object object : array) {
                    result.add(object);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将数组转换成List
     *
     * @param array
     * @return
     */
    public static List<Object> arrayToList(int[] array) {
        List<Object> result = new ArrayList<Object>();
        try {
            if (array != null) {
                for (Object object : array) {
                    result.add(object);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将char[] 转换成 String
     *
     * @param charAry
     * @return
     */
    public static String charArrayToString(Object charAry) {
        String result = "";
        try {
            if (charAry != null) {
                char[] array = (char[]) charAry;
                for (char charString : array) {
                    result += charString;
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean stringToBoolean(Object inObject) {
        boolean result = false;
        try {
            if (inObject != null && !"".equals(inObject)) {
                result = ("true".equals(inObject)) ? true : false;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 将对象转换成Int
     *
     * @param inObject
     * @return
     */
    public static int nullToInt(Object inObject) {
        int iRet = 0;
        if (inObject != null) {
            try {
                Double temp = new Double(inObject.toString());
                iRet = temp.intValue();
            } catch (Exception e) {
                iRet = 0;
            }
        }
        return iRet;
    }

    /**
     * 将对象转换成long
     *
     * @param inObject
     * @return
     */
    public static long nullToLong(Object inObject) {
        long iRet = 0;
        if (inObject != null) {
            try {
                Double temp = new Double(inObject.toString());
                iRet = temp.longValue();
            } catch (Exception e) {
                iRet = 0;
            }
        }
        return iRet;
    }

    public static String getDownloadFileName(String s) throws Exception {
        String sRet = s.replace(' ', '\r');
        sRet = URLEncoder.encode(sRet, "UTF-8");
        return sRet.replaceAll("%0D", " ");
    }

    public static String strReplace(String destStr, String oldStr, String newStr) {
        if (destStr == null)
            return "";
        String tmpStr = destStr;
        int foundPos = tmpStr.indexOf(oldStr);
        while (foundPos >= 0) {
            tmpStr = tmpStr.substring(0, foundPos)
                    + newStr
                    + tmpStr.substring(foundPos + oldStr.length(),
                    tmpStr.length());

            foundPos = tmpStr.indexOf(oldStr, foundPos + newStr.length());
        }
        return tmpStr;
    }

    public static void printBytes(byte[] array, String name) {
        for (int k = 0; k < array.length; k++)
            System.out.println(name + "[" + k + "] = " + "0x"
                    + byteToHex(array[k]));
    }

    public static String byteToHex(byte b) {
        char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        char[] array = {hexDigit[(b >> 4 & 0xF)], hexDigit[(b & 0xF)]};
        return new String(array);
    }

    public static String charToHex(char c) {
        byte hi = (byte) (c >>> '\b');
        byte lo = (byte) (c & 0xFF);
        return byteToHex(hi) + byteToHex(lo);
    }

    public static String htmlEncoder4Print(Object obj) {
        String sRet = htmlEncoder(obj);
        sRet = strReplace(sRet, "\r\n", "<br>");
        sRet = strReplace(sRet, "\r", "<br>");
        sRet = strReplace(sRet, "\n", "<br>");
        return sRet;
    }

    public static String htmlEncoder(Object obj) {
        if (obj == null)
            return "";
        String value = obj.toString();

        char[] content = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '\'':
                    result.append("&#039;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return result.toString();
    }

    public static String urlEncoder(String str)
            throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }

    public static String urlDecoder(String str)
            throws UnsupportedEncodingException {
        return URLDecoder.decode(str, "UTF-8");
    }

    public static String xmlEncoder(String str) {
        if ((str == null) || (str.equals(""))) {
            return "";
        }
        String res_str = strReplace(str, "&", "&amp;");
        res_str = strReplace(res_str, "<", "&lt;");
        res_str = strReplace(res_str, ">", "&gt;");
        res_str = strReplace(res_str, "\"", "&quot;");
        res_str = strReplace(res_str, "'", "&acute;");
        return res_str;
    }

    public static String xmlDecoder(String str) {
        if ((str == null) || (str.equals(""))) {
            return "";
        }
        String res_str = strReplace(str, "&amp;", "&");
        res_str = strReplace(res_str, "&lt;", "<");
        res_str = strReplace(res_str, "&gt;", ">");
        res_str = strReplace(res_str, "&quot;", "\"");
        res_str = strReplace(res_str, "&acute;", "'");
        return res_str;
    }

    public static String xmlEncoderForIE(String str) {
        if ((str == null) || (str.equals(""))) {
            return "";
        }
        String res_str = strReplace(str, "&", "&amp;");
        res_str = strReplace(res_str, "<", "&lt;");
        res_str = strReplace(res_str, ">", "&gt;");
        res_str = strReplace(res_str, "\"", "&quot;");

        return res_str;
    }

    public static String sqlEncoder(String str) {
        if ((str == null) || (str.equals(""))) {
            return "";
        }
        String res_str = strReplace(str, "'", "''");
        res_str = strReplace(res_str, "&", "'||'&'||'");
        return res_str;
    }

    public static String arrayToString(String[] values, String delim) {
        String sRet = "";
        for (int i = 0; i < values.length; i++) {
            sRet = sRet + delim + values[i];
        }
        if (values.length > 0)
            sRet = sRet.substring(delim.length());
        return sRet;
    }

    public static Calendar setCalendar(Calendar c, int year, int month, int day) {
        c.set(1, year);
        c.set(2, month);
        c.set(5, day);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c;
    }

    public static Calendar setCalendar(Calendar c, Date date) {
        c.setTime(date);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c;
    }

    public static String date2String(Date dateValue, String dateFormat) {
        String sResult = "";
        if (dateValue != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            sResult = formatter.format(dateValue);
        }
        return sResult;
    }

    public static String date2String(Date dateValue, int dateFormat) {
        String f = DEFAULT_DATE_FORMAT;
        if (dateFormat == 0) {
            f = DEFAULT_TIME_FORMAT;
        }
        return date2String(dateValue, f);
    }

    public static String date2String(Date dateValue) {
        return date2String(dateValue, DEFAULT_TIME_FORMAT);
    }

    public static Date string2Date(String sDate) throws ParseException {
        return string2Date(sDate, DEFAULT_DATE_FORMAT);
    }

    public static Date string2Date(String sDate, String dateFormat)
            throws ParseException {
        Date tmp = null;
        if ((sDate != null) && (!sDate.equals(""))) {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            formatter.setLenient(true);
            tmp = formatter.parse(sDate);
        }
        return tmp;
    }

    public static Date string2Date(String sDate, int dateFormat)
            throws ParseException {
        String f = DEFAULT_DATE_FORMAT;
        if (dateFormat == 0) {
            f = DEFAULT_TIME_FORMAT;
        }
        return string2Date(sDate, f);
    }

    public static int getIntFromString(String s) {
        int iRet = 0;
        if ((s != null) && (!"".equals(s)) && (!"null".equals(s))) {
            iRet = Integer.parseInt(s);
        }
        return iRet;
    }

    public static long getLongFromString(String s) {
        long iRet = 0L;
        if ((s != null) && (!"".equals(s))) {
            iRet = Long.parseLong(s);
        }
        return iRet;
    }

    public static Integer getInteger(String s) {
        if ((s == null) || ("".equals(s))) {
            return null;
        }
        return new Integer(s);
    }

    public static String formatNumber(String input, String pattern) {
        if ((input == null) || (input.trim().equals("")))
            return "";
        DecimalFormat format = new DecimalFormat();
        format.applyLocalizedPattern(pattern);
        double d = Double.parseDouble(input);
        return format.format(d);
    }

    public static String formatNumber(Object input, String pattern) {
        if (input == null)
            return "";
        return formatNumber(input.toString(), pattern);
    }

    public static String formatNumber(String input) {
        return formatNumber(input, FORMAT_PATTERN_DEFAULT);
    }

    public static String formatNumber(Object input) {
        if (input == null)
            return "";
        return formatNumber(input.toString());
    }

    public static String formatNumber(double d, int scale) {
        DecimalFormat df = new DecimalFormat(FORMAT_PATTERN_DEFAULT);
        df.setMaximumFractionDigits(scale);
        return df.format(d);
    }

    public static String formatNumber(double d) {
        DecimalFormat df = new DecimalFormat(FORMAT_PATTERN_DEFAULT);
        df.setMaximumFractionDigits(2);
        return df.format(d);
    }

    public static String double2String(double d) {
        DecimalFormat decimalFormatter = new DecimalFormat(
                DEFAULT_DECIMAL_FORMAT);
        return decimalFormatter.format(d).toString();
    }

    public static String percent2String(double d) {
        DecimalFormat percentFormatter = new DecimalFormat(
                DEFAULT_WORKTIME_FORMAT);
        return percentFormatter.format(d).toString();
    }

    public static String worktime2String(double d) {
        DecimalFormat workTimeFormatter = new DecimalFormat(
                DEFAULT_WORKTIME_FORMAT);
        return workTimeFormatter.format(d).toString();
    }

    public static double string2Worktime(String sDouble) {
        try {
            if ((sDouble != null) && (!"".equals(sDouble))) {
                DecimalFormat workTimeFormatter = new DecimalFormat(
                        DEFAULT_WORKTIME_FORMAT);
                return workTimeFormatter.parse(sDouble).doubleValue();
            }
        } catch (Exception e) {
        }
        return 0.0D;
    }

    public static double string2Double(String sDouble) {
        try {
            if ((sDouble != null) && (!"".equals(sDouble))) {
                DecimalFormat decimalFormatter = new DecimalFormat(
                        DEFAULT_DECIMAL_FORMAT);
                return decimalFormatter.parse(sDouble).doubleValue();
            }
        } catch (Exception e) {
        }
        return 0.0D;
    }

    public static String getLimitText(int limit, Object s) throws Exception {
        String result = "";
        String value = nullToString(s);
        if ((limit == 0) || (value.length() <= limit))
            result = value;
        else {
            result = value.substring(0, limit) + "...";
        }
        return result;
    }

    public static String javaString2JavascriptString(String s) {
        if (s == null) {
            return null;
        }
        s = s.replace("\\", "\\\\");
        s = s.replace("'", "\\'");
        s = s.replace("\"", "\\\"");
        return s;
    }

    public static String getCurrency(String currencyType, String value)
            throws Exception {
        String result = "";
        if ((value != null) && (!value.equals(""))) {
            DecimalFormat decimalFormatter = new DecimalFormat(
                    DEFAULT_DECIMAL_FORMAT);
            if (currencyType.equals("1"))
                result = "￥"
                        + decimalFormatter.format(string2Double(value))
                        .toString();
            else if (currencyType.equals("2")) {
                result = "$"
                        + decimalFormatter.format(string2Double(value))
                        .toString();
            }
        }
        return result;
    }

    public static String getCurrency(String currencyType, double value)
            throws Exception {
        String result = "";
        DecimalFormat decimalFormatter = new DecimalFormat(
                DEFAULT_DECIMAL_FORMAT);
        if (currencyType.equals("1"))
            result = "￥" + decimalFormatter.format(value);
        else if (currencyType.equals("2")) {
            result = "$" + decimalFormatter.format(value);
        }
        return result;
    }

    public static String getCurrencyFlag(String currencyType) throws Exception {
        String result = "";
        if (currencyType.equals("1"))
            result = "￥";
        else if (currencyType.equals("2")) {
            result = "$";
        }
        return result;
    }

    public static double round(double value, int afterPoint) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal bd1 = bd.setScale(afterPoint, 4);
        return bd1.doubleValue();
    }

    public static String roundToString(double value, int afterPoint) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal bd1 = bd.setScale(afterPoint, 4);
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(afterPoint);
        formatter.setMaximumFractionDigits(afterPoint);
        String rtnValue = formatter.format(bd1.doubleValue());
        return rtnValue;
    }

    public static String noShowYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(date);
    }

    private static String getMonthKey(int month) {
        String key = null;
        switch (month) {
            case 0:
                key = "JAN";
                break;
            case 1:
                key = "FEB";
                break;
            case 2:
                key = "MAR";
                break;
            case 3:
                key = "APR";
                break;
            case 4:
                key = "MAY";
                break;
            case 5:
                key = "JUN";
                break;
            case 6:
                key = "JUL";
                break;
            case 7:
                key = "AUG";
                break;
            case 8:
                key = "SEP";
                break;
            case 9:
                key = "OCT";
                break;
            case 10:
                key = "NOV";
                break;
            case 11:
                key = "DEC";
        }

        return key;
    }

    public static String getInMonthStringByQuarter(int quarter) {
        switch (quarter) {
            case 1:
                return " (0,1,2)";
            case 2:
                return " (3,4,5)";
            case 3:
                return " (6,7,8)";
            case 4:
                return " (9,10,11)";
        }
        return null;
    }

}
