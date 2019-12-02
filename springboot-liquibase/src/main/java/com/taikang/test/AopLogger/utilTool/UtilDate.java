package com.taikang.test.AopLogger.utilTool;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilDate {
    public static final String empty = "";
    public static final int SECOND = 0;
    public static final int MINUTE = 1;
    public static final int HOUR = 2;
    public static final int DAY = 3;
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static Map<String, DateTimeFormatter> dateFormatMap = null;

    public UtilDate() {
    }
//
//    private static DateTimeFormatter getDateFormatByPattern(String dateText) {
//        if (StringUtils.isBlank(dateText)) {
//            return null;
//        } else {
//            Iterator keyiter = dateFormatMap.keySet().iterator();
//
//            String regExp;
//            Matcher matcher;
//            do {
//                if (!keyiter.hasNext()) {
//                    return null;
//                }
//
//                regExp = (String)keyiter.next();
//                Pattern pattern = Pattern.compile(regExp);
//                matcher = pattern.matcher(dateText);
//            } while(!matcher.matches());
//
//            return (DateTimeFormatter)dateFormatMap.get(regExp);
//        }
//    }
//
//    public static boolean isValidDate(String dateText) {
//        DateTimeFormatter format = getDateFormatByPattern(dateText);
//        if (format == null) {
//            return false;
//        } else {
//            try {
//                format.parseLocalDateTime(dateText).toDate();
//                return true;
//            } catch (Exception var3) {
//                return false;
//            }
//        }
//    }
//
////    public static Date getCurrentDate() {
////        return DateTime.now().toDate();
////    }
//
////    public static Timestamp getCurrentTimestamp() {
////        return new Timestamp(DateTime.now().toDate().getTime());
////    }
//
////    public static String getMillisecondAsString() {
////        return DateTime.now().toString("yyyyMMddHHmmssSSS");
////    }
//
////    public static BigDecimal getCurrentTimeAsNumber() {
////        String returnStr = DateTime.now().toString("yyyyMMddHHmmss");
////        return new BigDecimal(returnStr);
////    }
//
//    public static Date parseDateTime(String dateText) {
//        return parseDateTime(dateText, (String)null);
//    }
//
//    public static Date parseDateTime(String dateText, String format) {
//        if (StringUtils.isBlank(dateText)) {
//            return null;
//        } else {
//            DateTimeFormatter dateFormat = null;
//            if (StringUtils.isNotEmpty(format)) {
//                dateFormat = DateTimeFormat.forPattern(format);
//            } else {
//                dateFormat = getDateFormatByPattern(dateText);
//            }
//
//            if (dateFormat == null) {
//                throw new RuntimeException(dateText + ":无法识别的日期格式");
//            } else {
//                try {
//                    return dateFormat.parseLocalDateTime(dateText).toDate();
//                } catch (Exception var4) {
//                    throw new RuntimeException(var4);
//                }
//            }
//        }
//    }
//
//    public static Timestamp parseTimestamp(String dateText, String format) {
//        if (UtilString.isBlank(dateText)) {
//            return null;
//        } else {
//            DateTimeFormatter dateFormat = null;
//            if (UtilString.isNotEmpty(format)) {
//                dateFormat = DateTimeFormat.forPattern(format);
//            } else {
//                dateFormat = getDateFormatByPattern(dateText);
//            }
//
//            if (dateFormat == null) {
//                throw new RuntimeException(dateText + ":无法识别的日期格式");
//            } else {
//                try {
//                    Date date = dateFormat.parseLocalDateTime(dateText).toDate();
//                    return new Timestamp(date.getTime());
//                } catch (Exception var4) {
//                    throw new RuntimeException(var4);
//                }
//            }
//        }
//    }
//
//    public static Timestamp parseTimestamp(String dateText) {
//        return parseTimestamp(dateText, (String)null);
//    }
//
//    public static String formatCurrentDateTime() {
//        return formatCurrentDate("yyyy-MM-dd HH:mm:ss");
//    }
//
//    public static String formatCurrentDate() {
//        return formatCurrentDate("yyyy-MM-dd");
//    }
//
//    public static String formatCurrentDate(String format) {
//        Date date = getCurrentDate();
//        return formatDateTime(date, format);
//    }
//
//    public static String formatDateTime(Date time) {
//        return formatDateTime(time, "yyyy-MM-dd");
//    }
//
//    public static String formatDateTime(Date time, String format) {
//        if (time == null) {
//            return null;
//        } else {
//            DateTimeFormatter dateFormat = null;
//            if (UtilString.isNotEmpty(format)) {
//                dateFormat = DateTimeFormat.forPattern(format);
//            } else {
//                dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
//            }
//
//            return (new DateTime(time)).toString(dateFormat);
//        }
//    }
//
//    public static String formatTimeMillis(long timeMillis) {
//        long day = timeMillis / 86400000L;
//        long hour = timeMillis / 3600000L - day * 24L;
//        long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
//        long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
//        long sss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
//        return (day > 0L ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
//    }
//
//    public static String formatTimestamp(Timestamp time) {
//        return formatTimestamp(time, (String)null);
//    }
//
//    public static String formatTimestamp(Timestamp time, String format) {
//        if (time == null) {
//            return null;
//        } else {
//            Date date = new Date(time.getTime());
//            return formatDateTime(date, format);
//        }
//    }
//itw_pangxl01 15:50:26
//public static String formatToFormat(String datetext, String targetFormat) {
//    if (UtilString.isBlank(datetext)) {
//        return null;
//    } else {
//        DateTimeFormatter dateFormat = getDateFormatByPattern(datetext);
//        if (dateFormat == null) {
//            throw new RuntimeException(datetext + ":无法识别的日期格式");
//        } else {
//            try {
//                Date date1 = dateFormat.parseLocalDateTime(datetext).toDate();
//                return (new DateTime(date1)).toString(targetFormat);
//            } catch (Exception var4) {
//                throw new RuntimeException(var4);
//            }
//        }
//    }
//}
//
//public static boolean isBetweenDateRange(String startDate, String endDate) {
//    String now = formatCurrentDateTime();
//    return isBetweenDateRange(now, startDate, endDate);
//}
//
//public static boolean isBetweenDateRange(String dateText, String startDate, String endDate) {
//    Timestamp startTime = null;
//    if (UtilString.isNotEmpty(startDate)) {
//        startTime = parseTimestamp(startDate);
//    }
//
//    Timestamp endTime = null;
//    if (UtilString.isNotEmpty(endDate)) {
//        endTime = parseTimestamp(endDate);
//    }
//
//    Timestamp inputTime = parseTimestamp(dateText);
//    if (inputTime == null) {
//        return false;
//    } else {
//        if (startTime != null && endTime == null) {
//            if (inputTime.compareTo(startTime) >= 0) {
//                return true;
//            }
//        } else if (startTime == null && endTime != null) {
//            if (inputTime.compareTo(endTime) <= 0) {
//                return true;
//            }
//        } else {
//            if (startTime == null && endTime == null) {
//                return true;
//            }
//
//            if (startTime != null && endTime != null && inputTime.compareTo(startTime) >= 0 && inputTime.compareTo(endTime) <= 0) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//}
//
//public static int getIntervalMonths(Date startDate, Date endDate) {
//    Calendar startCal = Calendar.getInstance();
//    startCal.setTime(startDate);
//    Calendar endCal = Calendar.getInstance();
//    endCal.setTime(endDate);
//    int startDateM = 2;
//    int startDateY = 1;
//    int enddatem = 2;
//    int enddatey = 1;
//    int interval = enddatey * 12 + enddatem - (startDateY * 12 + startDateM);
//    return interval;
//}
//
//public static int getIntervalDays(Date startDate, Date endDate) {
//    if (startDate != null && endDate != null) {
//        long startdate = startDate.getTime();
//        long enddate = endDate.getTime();
//        long interval = enddate - startdate;
//        int intervalday = (int)(interval / 86400000L);
//        return intervalday;
//    } else {
//        throw new IllegalArgumentException("startDate 和 endDate不能为空！");
//    }
//}
//
//public static long pastDays(Date date) {
//    long t = (new Date()).getTime() - date.getTime();
//    return t / 86400000L;
//}
//
//public static long pastHour(Date date) {
//    long t = (new Date()).getTime() - date.getTime();
//    return t / 3600000L;
//}
//
//public static long pastMinutes(Date date) {
//    long t = (new Date()).getTime() - date.getTime();
//    return t / 60000L;
//}
//
//public static String beforeDateAsFormat(String datestr, int dayNum) {
//    if (UtilString.isEmpty(datestr)) {
//        return datestr;
//    } else {
//        Date beforeDate = beforeDate(parseDateTime(datestr), dayNum);
//        DateTimeFormatter format = getDateFormatByPattern(datestr);
//        if (format == null) {
//            throw new UnknownFormatConversionException("无法识别日期格式");
//        } else {
//            return (new DateTime(beforeDate)).toString(format);
//        }
//    }
//}
//
//public static Date beforeDate(int dayNum) {
//    return beforeDate(new Date(), dayNum);
//}
//
//public static Date beforeDate(Date date, int dayNum) {
//    if (date == null) {
//        date = new Date();
//    }
//
//    return (new DateTime(date)).minusDays(dayNum).toDate();
//}
//
//public static String afterDateAsFormat(String datestr, int dayNum) {
//    if (UtilString.isEmpty(datestr)) {
//        return datestr;
//    } else {
//        Date afterDate = afterDate(parseDateTime(datestr), dayNum);
//        DateTimeFormatter format = getDateFormatByPattern(datestr);
//        if (format == null) {
//            throw new UnknownFormatConversionException("无法识别日期格式");
//        } else {
//            return (new DateTime(afterDate)).toString(format);
//        }
//    }
//}
//
//public static Date afterDate(int dayNum) {
//    return afterDate(new Date(), dayNum);
//}
//
//public static Date afterDate(Date date, int dayNum) {
//    if (date == null) {
//        date = new Date();
//    }
//
//    return (new DateTime(date)).plusDays(dayNum).toDate();
//}
//
//public static String beforeMonthAsFormat(String datestr, int monthNum) {
//    if (UtilString.isEmpty(datestr)) {
//        return datestr;
//    } else {
//        Date beforeDate = beforeMonth(parseDateTime(datestr), monthNum);
//        DateTimeFormatter format = getDateFormatByPattern(datestr);
//        if (format == null) {
//            throw new UnknownFormatConversionException("无法识别日期格式");
//        } else {
//            return (new DateTime(beforeDate)).toString(format);
//        }
//    }
//}
//
//public static Date beforeMonth(int monthNum) {
//    return beforeMonth(new Date(), monthNum);
//}
//
//public static Date beforeMonth(Date date, int monthNum) {
//    if (date == null) {
//        date = new Date();
//    }
//
//    return (new DateTime(date)).minusMonths(monthNum).toDate();
//}
//
//public static String afterMonthAsFormat(String datestr, int monthNum) {
//    if (UtilString.isEmpty(datestr)) {
//        return datestr;
//    } else {
//        Date afterDate = afterMonth(parseDateTime(datestr), monthNum);
//        DateTimeFormatter format = getDateFormatByPattern(datestr);
//        if (format == null) {
//            throw new UnknownFormatConversionException("无法识别日期格式");
//        } else {
//            return (new DateTime(afterDate)).toString(format);
//        }
//    }
//}
//itw_pangxl01 15:51:31
//public static Date afterMonth(int monthNum) {
//    return afterMonth(new Date(), monthNum);
//}
//
//public static Date afterMonth(Date date, int monthNum) {
//    if (date == null) {
//        date = new Date();
//    }
//
//    return (new DateTime(date)).plusMonths(monthNum).toDate();
//}
//
//public static String beforeYearAsFormat(String datestr, int yearNum) {
//    if (UtilString.isEmpty(datestr)) {
//        return datestr;
//    } else {
//        Date beforeDate = beforeYear(parseDateTime(datestr), yearNum);
//        DateTimeFormatter format = getDateFormatByPattern(datestr);
//        if (format == null) {
//            throw new UnknownFormatConversionException("无法识别日期格式");
//        } else {
//            return (new DateTime(beforeDate)).toString(format);
//        }
//    }
//}
//
//public static Date beforeYear(int yearNum) {
//    return beforeYear(new Date(), yearNum);
//}
//
//public static Date beforeYear(Date date, int yearNum) {
//    if (date == null) {
//        date = new Date();
//    }
//
//    return (new DateTime(date)).minusYears(yearNum).toDate();
//}
//
//public static String afterYearAsFormat(String datestr, int yearNum) {
//    if (UtilString.isEmpty(datestr)) {
//        return datestr;
//    } else {
//        Date beforeDate = afterYear(parseDateTime(datestr), yearNum);
//        DateTimeFormatter format = getDateFormatByPattern(datestr);
//        if (format == null) {
//            throw new UnknownFormatConversionException("无法识别日期格式");
//        } else {
//            return (new DateTime(beforeDate)).toString(format);
//        }
//    }
//}
//
//public static Date afterYear(int yearNum) {
//    return afterYear(new Date(), yearNum);
//}
//
//public static Date afterYear(Date date, int yearNum) {
//    if (date == null) {
//        date = new Date();
//    }
//
//    return (new DateTime(date)).plusYears(yearNum).toDate();
//}
//
//public static String getWeekDayByDate(String strdate) {
//    String[] dayNames = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(new Date());
//    int dayOfWeek = calendar.get(7) - 1;
//    if (dayOfWeek < 0) {
//        dayOfWeek = 0;
//    }
//
//    return dayNames[dayOfWeek];
//}
//
//public static int getDaysInMonth(int year, int month) {
//    if (month != 1 && month != 3 && month != 5 && month != 7 && month != 8 && month != 10 && month != 12) {
//        if (month != 4 && month != 6 && month != 9 && month != 11) {
//            return (year % 4 != 0 || year % 100 == 0) && year % 400 != 0 ? 28 : 29;
//        } else {
//            return 30;
//        }
//    } else {
//        return 31;
//    }
//}
//
//public static String getAge(String birthDayStr) {
//    if (UtilString.isBlank(birthDayStr)) {
//        return null;
//    } else {
//        try {
//            Date birthDay = parseDateTime(birthDayStr);
//            Calendar cal = Calendar.getInstance();
//            if (cal.before(birthDay)) {
//                return null;
//            } else {
//                int yearNow = cal.get(1);
//                int monthNow = cal.get(2) + 1;
//                int dayOfMonthNow = cal.get(5);
//                cal.setTime(birthDay);
//                int yearBirth = cal.get(1);
//                int monthBirth = cal.get(2) + 1;
//                int dayOfMonthBirth = cal.get(5);
//                int age = yearNow - yearBirth;
//                if (monthNow <= monthBirth) {
//                    if (monthNow == monthBirth) {
//                        if (dayOfMonthNow < dayOfMonthBirth) {
//                            --age;
//                        }
//                    } else {
//                        --age;
//                    }
//                }
//
//                return Integer.toString(age);
//            }
//        } catch (Exception var10) {
//            return "";
//        }
//    }
//}
//
//public static Date[] buildNotEmptyDateTimeRange(String inputDateFormat) {
//    if (UtilString.isEmpty(inputDateFormat)) {
//        throw new IllegalArgumentException("入参日期不能为空");
//    } else {
//        String inputDate = formatToFormat(inputDateFormat, "yyyy-MM-dd");
//        Date[] result = new Date[]{parseDateTime(inputDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"), parseDateTime(inputDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss")};
//        return result;
//    }
//}
//
//public static Date[] buildNotEmptyDateTimeRange(String inputDateStart, String inputDateEnd) {
//    if (!UtilString.isEmpty(inputDateStart) && !UtilString.isEmpty(inputDateEnd)) {
//        String dateStart = formatToFormat(inputDateStart, "yyyy-MM-dd");
//        String dateEnd = formatToFormat(inputDateEnd, "yyyy-MM-dd");
//        Date[] result = new Date[]{parseDateTime(dateStart + " 00:00:00", "yyyy-MM-dd HH:mm:ss"), parseDateTime(dateEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss")};
//        return result;
//    } else {
//        throw new IllegalArgumentException("入参日期不能为空");
//    }
//}
//
//public static void main(String[] args) {
//    System.out.println(formatToFormat("1991-04-14", "MM/dd/yyyy"));
//}
//
//itw_pangxl01 15:51:41
//static {
//        dateFormatMap = Maps.newConcurrentMap();
//        dateFormatMap.put("\\d{1,2}/\\d{1,2}/\\d{4}", DateTimeFormat.forPattern("MM/dd/yyyy"));
//        dateFormatMap.put("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}", DateTimeFormat.forPattern("MM/dd/yyyy HH"));
//        dateFormatMap.put("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}", DateTimeFormat.forPattern("MM/dd/yyyy HH:mm"));
//        dateFormatMap.put("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}", DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
//        dateFormatMap.put("\\d{4}-\\d{1,2}-\\d{1,2}", DateTimeFormat.forPattern("yyyy-MM-dd"));
//        dateFormatMap.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}", DateTimeFormat.forPattern("yyyy-MM-dd HH"));
//        dateFormatMap.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));
//        dateFormatMap.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
//        dateFormatMap.put("\\d{4}/\\d{1,2}/\\d{1,2}", DateTimeFormat.forPattern("yyyy/MM/dd"));
//        dateFormatMap.put("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}", DateTimeFormat.forPattern("yyyy/MM/dd HH"));
//        dateFormatMap.put("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}", DateTimeFormat.forPattern("yyyy/MM/dd HH:mm"));
//        dateFormatMap.put("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}", DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss"));
//        dateFormatMap.put("\\d{8}", DateTimeFormat.forPattern("yyyyMMdd"));
//        dateFormatMap.put("\\d{10}", DateTimeFormat.forPattern("yyyyMMddHH"));
//        dateFormatMap.put("\\d{12}", DateTimeFormat.forPattern("yyyyMMddHHmm"));
//        dateFormatMap.put("\\d{14}", DateTimeFormat.forPattern("yyyyMMddHHmmss"));
//    }
}