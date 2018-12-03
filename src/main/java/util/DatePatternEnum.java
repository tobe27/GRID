package util;

/**
 * 时间格式枚举类-保证时间参数限定
 * @author Created by L.C.Y on 2018-11-6
 */
public enum DatePatternEnum {
    DATE("yyyy-MM-dd"),
    DATE_TIME_SECOND("yyyy-MM-dd HH:mm:ss"),
    DATE_TIME_MILLI("yyyy-MM-dd HH:mm:ss.SSS");

    private String datePattern;

    DatePatternEnum(String datePattern) {
        this.datePattern = datePattern;
    }

    public String getDatePattern() {
        return datePattern;
    }
}
