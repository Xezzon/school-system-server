package indi.xezzon.school.common.exception;

import indi.xezzon.school.common.constant.enums.ErrorCodeEnum;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xezzon
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 未登录
     *
     * @param e 异常信息
     */
    @ExceptionHandler (value = {UnauthenticatedException.class})
    @ResponseStatus (HttpStatus.UNAUTHORIZED)
    public ErrorCodeEnum unauthenticated(UnauthenticatedException e) {
        return ErrorCodeEnum.UNAUTHENTICATED;
    }
    
    /**
     * 没有操作权限
     *
     * @param e 异常信息
     */
    @ExceptionHandler (value = {UnauthorizedException.class})
    @ResponseStatus (HttpStatus.FORBIDDEN)
    public ErrorCodeEnum unauthorized(UnauthorizedException e) {
        return ErrorCodeEnum.UNAUTHORIZED;
    }
}
