package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果（非分页）
 *
 * @author xezzon
 */
@Deprecated
@Data
@NoArgsConstructor
public class Result<T> {
    private String code;
    private String message;
    private T data;
    
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
}
