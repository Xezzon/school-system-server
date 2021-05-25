package indi.xezzon.school.common.constant.enums;

/**
 * @author xezzon
 */

public enum AbWeekEnum {
    NONE(0, ""),
    SINGLE(1, "单"),
    DOUBLE(2, "双");

    private final int code;
    private final String description;

    AbWeekEnum(int code, String description) {
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
