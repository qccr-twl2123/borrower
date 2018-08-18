package com.trj.jk.web.aspect;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.util.MapUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;


/**
 * 控制器切面日志
 * <p>
 * Created by xierongli on 17/8/29.
 */
@Aspect
@Component
public class ControllerAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.trj.jk.web.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, String> parameterMap = MapUtil.getParameterMap(request);
        Iterator<Map.Entry<String, String>> entries = parameterMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            if (entry.getValue() != null && entry.getValue().length() > 1000) {
                parameterMap.put(entry.getKey(), " value is too long");
            }
        }
        logger.info("接口请求URL:{} 参数:{}", request.getRequestURL().toString(), JSON.toJSONString(parameterMap));
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) throws Throwable {
        logger.info("耗时:{} 接口调用返回:{} ", System.currentTimeMillis() - startTime.get(), JSON.toJSONString(object));
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void handle(JoinPoint point, Exception e) {
        String methodName = point.getTarget().getClass() + "." + point.getSignature().getName();
        logger.error("耗时:{} 调用方法{}错误:{} ", System.currentTimeMillis() - startTime.get(), methodName, e.getMessage());
    }

}
