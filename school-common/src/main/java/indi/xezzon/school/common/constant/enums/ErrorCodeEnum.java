package indi.xezzon.school.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author xezzon
 */
@JsonFormat (shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodeEnum {
    SUCCESS("00000", "操作成功"),
    
    CLIENT("A0001", "用户端错误"), REGISTER("A0100", "用户注册错误"), ACCOUNT_EXISTED("A0101", "用户已存在"), LOGIN("A0200", "用户登录异常"), ACCOUNT_NONEXISTENT("A0201", "账号不存在"), ACCOUNT_LOCKED("A0202", "账号被冻结"), AUTHORIZATION("A0300", "访问权限异常"), UNAUTHENTICATED("A0301", "未认证"), UNAUTHORIZED("A0302", "无访问权限"),
    
    SERVER("B0001", "系统执行错误"),
    
    THIRD_PART_SERVICE("C0001", "调用第三方服务出错");
    
    private final String code;
    private final String message;
    
    ErrorCodeEnum(String code, String message) {
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
