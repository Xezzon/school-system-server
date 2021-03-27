package indi.xezzon.school.common.exception;

import indi.xezzon.school.common.constant.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xezzon
 */
@Getter
@ToString
public abstract class AbstractNormalException extends RuntimeException {
    private final ResultCodeEnum resultCodeEnum;

    public AbstractNormalException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.resultCodeEnum = resultCodeEnum;
    }

    public AbstractNormalException(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum, "");
    }
}
