package indi.xezzon.school.common.exception;

import indi.xezzon.school.common.constant.enums.ResultCodeEnum;

/**
 * @author xezzon
 */
public class NormalClientException extends AbstractNormalException {
    public NormalClientException() {
        super(ResultCodeEnum.CLIENT);
    }

    public NormalClientException(ResultCodeEnum resultCodeEnum, String message) {
        super(resultCodeEnum, message);
    }
}
