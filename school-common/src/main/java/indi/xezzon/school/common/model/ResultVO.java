package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors (chain = true)
public class ResultVO<T> {
    private String code;
    private String message;
    private T payload;

    /**
     * 正常返回结果
     *
     * @param payload 数据
     */
    public ResultVO(T payload) {
        this(payload, ResultCodeEnum.SUCCESS.getMessage());
    }

    public ResultVO(T payload, String message) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = message;
        this.payload = payload;
    }

    /**
     * 发生异常时（包括客户端异常和服务端异常）返回结果
     *
     * @param resultCodeEnum 错误码
     * @param payload 展示给调用方查看的信息
     */
    public ResultVO(ResultCodeEnum resultCodeEnum, T payload) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.payload = payload;
    }

    private ResultVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResultVO<T> ok() {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    public static ResultVO<String> error() {
        return new ResultVO<>(ResultCodeEnum.SERVER.getCode(), ResultCodeEnum.SERVER.getMessage());
    }

    public static ResultVO<String> error(ResultCodeEnum resultCodeEnum) {
        return new ResultVO<>(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }
}
