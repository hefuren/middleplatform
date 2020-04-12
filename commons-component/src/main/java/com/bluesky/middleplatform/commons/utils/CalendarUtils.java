package com.bluesky.middleplatform.commons.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtils {
    public static final int BY_MONTH = 1;
    public static final int BY_WEEK = 2;
    public static final int BY_DAY = 3;
    public static final int BY_YEAR = 4;
    public static final int BY_QUARTER = 5;
    public static final int ONLY_ONE = 6;
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long ONE_DAY = 86400000L;
    public static final long ONE_WEEK = 604800000L;

    private CalendarUtils() {

    }

    public static List<Date> getDateList(int cycleType, int year, int quarter,
                                         int month) {
        List dateList = new ArrayList();
        if (cycleType == 1) {
            for (int i = 0; i <= 11; i++) {
                Date date = getMonthStartDay(year, i);
                dateList.add(date);
            }
        } else if (cycleType == 2) {
            Calendar calendar = Calendar.getInstance();
            Date firstSun = getFirstSunByQuarter(year, quarter);
            calendar.setTime(firstSun);
            int lastMonth = getLastMonthByQuarter(quarter);
            while ((calendar.get(2) <= lastMonth) && (calendar.get(1) == year)) {
                dateList.add(calendar.getTime());
                calendar.add(5, 7);
            }
        } else if (cycleType == 3) {
            Calendar calendar = Calendar.getInstance();
            Date startDay = getMonthStartDay(year, month);
            calendar.setTime(startDay);
            while (calendar.get(2) == month) {
                Date date = calendar.getTime();
                dateList.add(date);
                calendar.add(5, 1);
            }
        }
        return dateList;
    }

    public static Date addOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, 1);
        Date endDate = c.getTime();
        return endDate;
    }

    public static Date getIntegralDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearCalendar(calendar);
        return calendar.getTime();
    }

    public static Date getDateEndOfYearAndWeek(int year, int week) {
        Date date = getCurrentDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(1, year);
        calendar.set(3, week);
        calendar.set(7, 7);
        return calendar.getTime();
    }

    public static int getYearByDate(Date date) {
        int year = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        year = calendar.get(1);
        return year;
    }

    public static int getMonthByDate(Date date) {
        int month = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        month = calendar.get(2);
        return month;
    }

    public static int getDayOfYearByDate(Date date) {
        int result = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        result = calendar.get(6);
        return result;
    }

    public static int getDayOfWeeyByDate(Date date) {
        int result = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        result = calendar.get(7);
        return result;
    }

    public static boolean isAtTheSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return (c1.get(1) == c2.get(1)) && (c1.get(2) == c2.get(2))
                && (c1.get(5) == c2.get(5));
    }

    public static int getDayDateByDate(Date date) {
        int dayDate = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dayDate = calendar.get(5);
        return dayDate;
    }

    public static int getWeekOfYearByDate(Date date) {
        int week = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        week = calendar.get(3);
        return week;
    }

    public static Date getMonthStartDay(int year, int month) {
        Date date = getCurrentDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(1, year);
        calendar.set(2, month);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static int getQuarter(Calendar calendar) {
        int month = calendar.get(2);
        return month / 3 + 1;
    }

    public static int getMonth(Calendar calendar) {
        int month = calendar.get(2);
        return month;
    }

    public static Date getQuarterStartDay(int year, int quarter) {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(1, year);
        calendar.set(2, (quarter - 1) * 3);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getQuarterEndDay(int year, int quarter) {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(1, year);
        calendar.set(2, quarter * 3);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getFirstSunByQuarter(int year, int quarter) {
        int firstMonth = getFirstMonthByQuarter(quarter);
        Date date = getMonthStartDay(year, firstMonth);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(7) != 1) {
            calendar.add(5, 8 - calendar.get(7));
        }
        return calendar.getTime();
    }

    public static Date getFirstSunByYearAndMonth(int year, int month) {
        Date date = getMonthStartDay(year, month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(7) != 1) {
            calendar.add(5, 8 - calendar.get(7));
        }
        return calendar.getTime();
    }

    public static Date getLastSatByQuarter(int year, int quarter) {
        int lastMonth = getLastMonthByQuarter(quarter);
        Date date = getMonthEndDay(year, lastMonth);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(7) != 7) {
            calendar.add(5, 7 - calendar.get(7));
        }
        return calendar.getTime();
    }

    public static Date getLastSatByYearAndMonth(int year, int month) {
        Date date = getMonthEndDay(year, month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(7) != 7) {
            calendar.add(5, 7 - calendar.get(7));
        }
        return calendar.getTime();
    }

    public static Date getDayByYear(int year) {
        Date date = getCurrentDay();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(1, year);
        date = c.getTime();
        return date;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, 1);
        return c.getTime();
    }

    public static int getWeekYear(Calendar calendar) {
        int month = calendar.get(2);
        int weekOfYear = calendar.get(3);
        int year = calendar.get(1);
        int weekYear = year;
        if ((month == 0) && ((weekOfYear == 52) || (weekOfYear == 53))) {
            weekYear--;
        } else if ((month == 11) && (weekOfYear == 1)) {
            weekYear++;
        }
        return weekYear;
    }

    public static int getWeekYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(2);
        int weekOfYear = calendar.get(3);
        int year = calendar.get(1);
        int weekYear = year;
        if ((month == 0) && ((weekOfYear == 52) || (weekOfYear == 53)))
            weekYear--;
        else if ((month == 11) && (weekOfYear == 1)) {
            weekYear++;
        }
        return weekYear;
    }

    public static Date getMonthEndDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(1, year);
        calendar.set(2, month + 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static void clearCalendar(Calendar c) {
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
    }

    public static Date getYesterday() {
        Calendar c = Calendar.getInstance();
        clearCalendar(c);
        c.add(5, -1);
        return c.getTime();
    }

    public static Date getCurrentDay() {
        Calendar c = Calendar.getInstance();
        clearCalendar(c);
        return c.getTime();
    }

    public static Date getLastSaturday() {
        Calendar c = Calendar.getInstance();
        clearCalendar(c);
        c.add(5, -c.get(7));
        return c.getTime();
    }

    public static int getFirstMonthByQuarter(int quarter) {
        int firstMonth = 0;
        switch (quarter) {
            case 1:
                firstMonth = 0;
                break;
            case 2:
                firstMonth = 3;
                break;
            case 3:
                firstMonth = 6;
                break;
            case 4:
                firstMonth = 9;
        }

        return firstMonth;
    }

    public static int getLastMonthByQuarter(int quarter) {
        int lastMonth = 0;
        switch (quarter) {
            case 1:
                lastMonth = 2;
                break;
            case 2:
                lastMonth = 5;
                break;
            case 3:
                lastMonth = 8;
                break;
            case 4:
                lastMonth = 11;
        }

        return lastMonth;
    }

    public static Date getDateStartOfYearAndWeek(int year, int week) {
        Date date = getCurrentDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(1, year);
        calendar.set(3, week);
        calendar.set(7, 1);
        return calendar.getTime();
    }

    public static Date getThisYearLastDay() {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(2, 11);
        calendar.set(5, 31);
        return calendar.getTime();
    }

    public static Date getThisYearFirstDay() {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(2, 0);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static boolean judgeBissextile(int year) {
        boolean result = false;
        if ((year % 4 == 0) && (year % 100 != 0))
            result = true;
        else if ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)) {
            result = true;
        }
        return result;
    }

    public static Date getThisSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearCalendar(calendar);
        calendar.set(7, 1);
        return calendar.getTime();
    }

    public static Date getThisSaturday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearCalendar(calendar);
        calendar.set(7, 7);
        return calendar.getTime();
    }

    public static Date getLastDayOfTheYear(int year) {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(1, year);
        calendar.set(2, 11);
        calendar.set(5, 31);
        return calendar.getTime();
    }

    public static Date getFirstDayOfTheYear(int year) {
        Calendar calendar = Calendar.getInstance();
        clearCalendar(calendar);
        calendar.set(1, year);
        calendar.set(2, 0);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getCurrentDate() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static int getDateRangeDay(Date start, Date end) {
        double range = 0.0D;
        range = (end.getTime() - start.getTime()) / 86400000L + 1L;
        return new Double(range).intValue();
    }
}
