package indi.xezzon.school.common.constant.enums;

/**
 * @author xezzon
 */

public enum WeekdayEnum {
    Mon(1, "周一"),
    Tus(2, "周二"),
    Wen(3, "周三"),
    Thu(4, "周四"),
    Fri(5, "周五"),
    Sat(6, "周六"),
    Sun(7, "周日");

    private final int code;
    private final String description;

    WeekdayEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
