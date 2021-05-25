package indi.xezzon.school.common.constant.enums;

/**
 * @author xezzon
 */

public enum GenderEnum {
    NONE(0, "保密"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private final int code;
    private final String description;

    GenderEnum(int code, String description) {
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
