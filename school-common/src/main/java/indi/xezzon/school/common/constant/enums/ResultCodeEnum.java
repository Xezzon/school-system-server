package indi.xezzon.school.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author xezzon
 */
@JsonFormat (shape = JsonFormat.Shape.OBJECT)
public enum ResultCodeEnum {
    SUCCESS("00000", "操作成功"),
    
    CLIENT("A0001", "用户端错误"), UNAUTHORIZED("A0300", "没有相应授权"), UNAUTHENTICATED("A0301", "未认证"),
    
    SERVER("B0001", "系统执行错误"),
    
    THIRD_PART_SERVICE("C0001", "调用第三方服务出错");
    
    private final String code;
    private final String message;
    
    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}
