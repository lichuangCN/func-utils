package com.example.func.local.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Order(value = Integer.MIN_VALUE)
@Component
public class LogAspect {

    @Pointcut("@annotation(com.example.func.local.aop.LogPrint)")
    public void pointCuts() {
    }

    /**
     * 切面环绕
     */
    @Around(value = "pointCuts()")
    public Object doAround(ProceedingJoinPoint pjp) {
        String funName = pjp.getSignature().getName();
        log.info("前置通知：{}方法准备执行", funName);
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("LogPrint doAround error is{}", throwable);
        }
        log.info("后置通知：{}方法已经执行完毕", funName);
        return obj;
    }

}
