package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间转换工具
 * @author Created by L.C.Y on 2018-11-6
 */
public class DateConvertUtil {

    /**
     * 毫秒时间戳
     * @return
     */
    public static long getMillisecond() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 秒时间戳
     * @return
     */
    public static long getSecond() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 将毫秒时间戳转换成字符串,默认格式为yyyy-MM-dd
     * @param timeStamp
     * @return
     */
    public static String convert2String(Long timeStamp) {
        return convert2String(timeStamp, DatePatternEnum.DATE);
    }

    /**
     * 将毫秒时间戳转换成限定格式的字符串
     * @param timeStamp
     * @param datePattenEnum 时间格式枚举类
     * @return
     */
    public static String convert2String(long timeStamp, DatePatternEnum datePattenEnum) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattenEnum.getDatePattern());
        return dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault()));
    }

    /**
     * 将DateTime字符串时间转换成毫秒时间戳
     * @param dateTime 时间格式必须与枚举类相同
     * @param datePattenEnum 时间格式枚举类
     * @return
     */
    public static Long convertDateTime2Long(String dateTime, DatePatternEnum datePattenEnum) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattenEnum.getDatePattern());
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return LocalDateTime.from(localDateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将Date字符串时间转换成毫秒时间戳
     * @param date 时间格式必须与枚举类相同
     * @return
     */
    public static Long convertDate2Long(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePatternEnum.DATE_TIME_SECOND.getDatePattern());
        date = date + " 00:00:00";
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return LocalDateTime.from(localDateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


}
