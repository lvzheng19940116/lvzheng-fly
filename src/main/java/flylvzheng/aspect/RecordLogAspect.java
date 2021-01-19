package flylvzheng.aspect;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author LvZheng
 * @date 2020/12/31
 */
@Slf4j
@Aspect
@Component
public class RecordLogAspect {


    /**
     * controller履历日志
     */
    // @Pointcut("execution(public * com.eversec.everim.server.msg.controller.*.*(..)) && execution(public * com.eversec.everim.server.msg.controller.*.*(..))")
    public void log() {
    }

    //  @Around("log()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ApiOperation ann = method.getAnnotation(ApiOperation.class);
        log.info("RecordUrl:{}", request.getRequestURL().toString());
        log.info("RecordMethod:{}", request.getMethod());
        if (ann != null) {
            log.info("Operation:{}", ann.value());
        }
        log.info("RecordIp:{}", IpUtils.getIpAddr(request));
        String classMethod = point.getTarget().getClass().getName() + "." + point.getSignature().getName();
        log.info("RecordClassMethod:{}", classMethod);

        StringBuffer strControllerArgs = new StringBuffer();

        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            for (Object o : args) {
                if (o instanceof HttpServletRequest || o instanceof HttpServletResponse
                        || o instanceof MultipartFile) {
                    continue;
                }
                strControllerArgs.append(JSON.toJSON(o) + ",");
            }
            log.info("RecordArgs:{}", strControllerArgs.toString());
        }
        log.info("CreateBy:{}", request.getHeader("userId"));
        Object result;
        try {
            Long beginTime = System.currentTimeMillis();
            result = point.proceed();
            Long executeTime = System.currentTimeMillis() - beginTime;
            log.info("ExecutionTime:{}", executeTime);
            log.info("result:{}", result);
            return result;
        } catch (Exception e) {
            log.error("err:{}", e);
            throw new RuntimeException(e.getMessage());

        }
    }
}
