package indi.xezzon.school.common.exception;

import indi.xezzon.school.common.constant.enums.ResultCodeEnum;

/**
 * @author xezzon
 */
public class NormalThirdPartyException extends AbstractNormalException {
    public NormalThirdPartyException() {
        super(ResultCodeEnum.THIRD_PART_SERVICE);
    }

    public NormalThirdPartyException(ResultCodeEnum resultCodeEnum, String message) {
        super(resultCodeEnum, message);
    }
}
