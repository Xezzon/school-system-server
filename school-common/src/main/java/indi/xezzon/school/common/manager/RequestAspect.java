package indi.xezzon.school.common.manager;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * @author xezzon
 */
@Component
@Aspect
@Slf4j
public class RequestAspect {
    @Pointcut("execution(public * indi.xezzon.school.*.controller.*.*(..))")
    public void requestAspect() {
    }

    @Around("requestAspect()")
    public Object doAround(ProceedingJoinPoint pjp) {
        LocalDateTime beginDateTime = LocalDateTime.now();
        long beginTimestamp = beginDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String uri = null;
        String method = null;
        String ip = null;
        Object result = null;
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
            uri = request.getRequestURI();
            method = request.getMethod();
            ip = request.getRemoteAddr();
            result = pjp.proceed();
            long endTimestamp = System.currentTimeMillis();
            log.debug("ACCESS-: {} 于北京时间 {} 以 {} 请求 {} ,用时 {} ms", ip, beginDateTime, method, uri, endTimestamp - beginTimestamp);
        } catch (Throwable throwable) {
            log.error("{} 于北京时间{} {} {}失败 {}", ip, beginDateTime, method, uri, throwable.getMessage());
        }
        return result;
    }
}
