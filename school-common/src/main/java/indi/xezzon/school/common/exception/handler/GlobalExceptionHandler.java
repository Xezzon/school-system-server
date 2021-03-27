package indi.xezzon.school.common.exception.handler;

import indi.xezzon.school.common.constant.enums.ResultCodeEnum;
import indi.xezzon.school.common.exception.NormalClientException;
import indi.xezzon.school.common.exception.NormalServerException;
import indi.xezzon.school.common.exception.NormalThirdPartyException;
import indi.xezzon.school.common.model.ResultVO;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xezzon
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 通用异常处理方法
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<String> error(Exception e) {
        e.printStackTrace();
        return ResultVO.error();
    }

    /**
     * 客户端错误，返回状态码400
     */
    @ExceptionHandler(NormalClientException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultVO<String> error(NormalClientException e) {
        e.printStackTrace();
        return new ResultVO<String>(e.getResultCodeEnum(), e.getMessage());
    }

    /**
     * 服务端错误，返回状态码500
     */
    @ExceptionHandler(NormalServerException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<String> error(NormalServerException e) {
        e.printStackTrace();
        return new ResultVO<String>(e.getResultCodeEnum(), e.getMessage());
    }

    /**
     * 调用第三方服务出错
     */
    @ExceptionHandler(NormalThirdPartyException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<String> error(NormalThirdPartyException e) {
        e.printStackTrace();
        return new ResultVO<String>(e.getResultCodeEnum(), e.getMessage());
    }

    /**
     * 未登录，返回401，前端跳转到登录页面。
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultVO<String> error(UnauthenticatedException e) {
        e.printStackTrace();
        return ResultVO.error(ResultCodeEnum.UNAUTHENTICATED);
    }

    /**
     * 用户没有权限访问相关资源或相关资源不允许被访问，返回403，前端跳转到上一页。
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResultVO<String> error(UnauthorizedException e) {
        e.printStackTrace();
        return ResultVO.error(ResultCodeEnum.UNAUTHORIZED);
    }
}
