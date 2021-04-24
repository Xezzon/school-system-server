package indi.xezzon.school.common.exception;

import indi.xezzon.school.common.constant.enums.ResultCodeEnum;

/**
 * @author xezzon
 */
public class NormalServerException extends AbstractNormalException {
    public NormalServerException() {
        super(ResultCodeEnum.SERVER);
    }

    public NormalServerException(ResultCodeEnum resultCodeEnum, String message) {
        super(resultCodeEnum, message);
    }
}
