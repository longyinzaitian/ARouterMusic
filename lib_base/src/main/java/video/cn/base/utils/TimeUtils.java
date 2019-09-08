package video.cn.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author husy
 * @date 2019/9/7
 */
public class TimeUtils {
    public static String getTimeFormat(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = addZero(month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = addZero(day);
        //24小时制
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String hourStr = addZero(hour);
        int minute = calendar.get(Calendar.MINUTE);
        String minuteStr = addZero(minute);
        int second = calendar.get(Calendar.SECOND);
        String secondStr =addZero(second);
        return(year + "-" + monthStr  + "-" + dayStr + " "
                + hourStr + ":" + minuteStr + ":" + secondStr);
    }

    /**
     * 将毫秒字符串转成时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String getTimeFormat(String timeStr) {
        long time=Long.parseLong(timeStr);
        return getTimeFormat(time);
    }
    private static String addZero(int param) {
        String paramStr= param<10 ? "0"+param : "" + param ;
        return paramStr;
    }
    /**
     * 将时间格式yyyy-MM-dd HH:mm:ss转成毫秒
     */
    public static Long getTimeFormat2mill(String timeStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(timeStr);
        return date.getTime();
    }
    /**
     * 将时间格式yyyyMMddHHmmss转成毫秒
     */
    public static Long getTimeString2mill(String timeStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = simpleDateFormat.parse(timeStr);
        long ts = date.getTime();
        return ts;
    }
    /**
     * 将毫秒转成时间串yyyyMMddHHmmss
     */
    public static String getmill2TimeString(String timeStr) throws ParseException {
        return getTimeFormat(timeStr).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
    }
    /**
     * 将时间格式yyyyMMddHHmmss转成将时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String getTimeString2TimeFormat(String timeStr) throws ParseException {
        return getTimeFormat(""+getTimeString2mill(timeStr));
    }
    /**
     * 将时间格式yyyy-MM-dd HH:mm:ss转成将时间格式yyyyMMddHHmmss
     */
    public static String getTimeFormat2TimeString(String timeFormat) throws ParseException {
        return getmill2TimeString(""+getTimeFormat2mill(timeFormat));
    }
    /**
     * 计算两个时间的间隔,返回分钟数值，不足一分钟舍去，计算的时间为标准时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String getIntevalTime(String startTime,String endTime) throws ParseException {
        long intevaltime=getTimeFormat2mill(endTime)-getTimeFormat2mill(startTime);
        return ""+intevaltime/60000;
    }
    /**
     * 将分钟数值转成时间字符串
     */
    public static String minute2Hour(String min){
        int num=Integer.parseInt(min);
        int hour=num/60;
        int minute=num%60;
        if (hour==0) {
            return minute+"分钟";
        }
        return hour+"小时"+minute+"分钟";
    }
}
