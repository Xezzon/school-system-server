package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.ErrorCodeEnum;

/**
 * 统一返回结果（非分页）
 *
 * @author xezzon
 */
@Deprecated
public class Result<T> {
    private String code;
    private String message;
    private T data;
    
    public Result() {
        super();
    }
    
    protected Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    /**
     * 请求成功
     *
     * @return 封装的统一返回结果
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage(), data);
    }
    
    /**
     * 请求成功
     *
     * @param message 附带的消息
     * @return 封装的统一返回结果
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(ErrorCodeEnum.SUCCESS.getCode(), message, data);
    }
    
    /**
     * 请求失败
     *
     * @return 封装的统一返回结果
     */
    public static <T> Result<T> failed(ErrorCodeEnum errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
    
    /**
     * 请求失败
     *
     * @param message 附带的错误消息
     * @return 封装的统一返回结果
     */
    public static <T> Result<T> failed(ErrorCodeEnum errorCode, String message) {
        return new Result<>(errorCode.getCode(), message, null);
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "Result{" + "code='" + code + '\'' + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
