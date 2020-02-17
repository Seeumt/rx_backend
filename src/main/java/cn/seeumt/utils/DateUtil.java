package cn.seeumt.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/17 12:01
 */
public class DateUtil {
    /**
     * 时间戳转标准日期格式
     * @param
     * @param
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String timeStampToDate(Long timeStamp) {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(timeStamp));
        return sdf.format(timeStamp);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str
     *            字符串日期
     * @param format
     *            如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**Date方式
     * @param
     *
     */
    public static String getNewDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * Calendar
     * @param
     *
     *
     */
    public static String getNewDate2() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    /** Calendar的简单使用
     * @param
     */
    public static void calendarUtil() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("年：" + calendar.get(calendar.YEAR));
        System.out.println("月：" + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("日：" + calendar.get(Calendar.DATE));
        /**
         * 获取时分秒
         */
        // 24小时制
        System.out.println("时：" + calendar.get(Calendar.HOUR_OF_DAY));
        // 12小时制
        // System.out.println(calendar.get(Calendar.HOUR));
        System.out.println("分：" + calendar.get(Calendar.MINUTE));
        System.out.println("秒：" + calendar.get(Calendar.SECOND));

        /**
         * 这一年的第几天,这个月的第几天，这周的第几天
         */
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        /**
         * 得到时间，Fri Aug 19 14:33:03 CST 2016 得到本周第一天 得到时间的毫秒数
         */
        System.out.println(calendar.getTime());
        System.out.println(calendar.getFirstDayOfWeek());
        System.out.println(calendar.getTimeInMillis());

        // System.out.println(calendar.compareTo(anotherCalendar));

    }

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(System.currentTimeMillis()));
    }

}
