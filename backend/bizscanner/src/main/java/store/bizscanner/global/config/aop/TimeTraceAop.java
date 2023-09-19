package store.bizscanner.global.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class TimeTraceAop {
    @Around("execution(* store.bizscanner..*(..)) && !target(store.bizscanner.global.config.aop.AopConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        log.info(joinPoint.toString() + " - START");

        try {
            return joinPoint.proceed();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info(joinPoint + " - END(" + timeMs + "ms)");
        }
    }
}