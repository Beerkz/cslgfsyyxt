package com.cslg.secruity.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import com.cslg.annoation.LoginConfigAnnotation;
import com.cslg.secruity.entity.SysOperLog;
import com.cslg.secruity.repository.LogRepository;
import com.cslg.system.entity.SysUser;
import com.cslg.system.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Aop切面记录系统操作日志
 */
@Configuration
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogAspectConfig {

    private final LogRepository logRepository;


    @Pointcut("@annotation(com.cslg.annoation.LoginConfigAnnotation)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行 后置通知
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint);
    }

    protected void handleLog(final JoinPoint joinPoint) {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            // *========数据库日志=========*//
            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(1);
            // 请求的地址
            operLog.setOperIp(getIpAddress());
            operLog.setOperUrl(request.getRequestURI());

            String token = request.getHeader("token");
            SysUser user = (SysUser) StpUtil.getSession().get(StpUtil.getLoginIdAsString());
            operLog.setOperName(user.getUsername());
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(request.getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, operLog);
            // 保存数据库
            logRepository.insertOperLog(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, SysOperLog operLog) throws Exception {
        String methodName = joinPoint.getSignature().getName();
        Method method = currentMethod(joinPoint, methodName);
        LoginConfigAnnotation annotation = method.getAnnotation(LoginConfigAnnotation.class);
        // 设置action动作
        operLog.setBusinessType(annotation.businessType().getMessage());
        // 设置标题
        operLog.setTitle(annotation.title());
        // 设置操作人类别
        operLog.setOperatorType(annotation.operatorType().name());
        // 是否需要保存request，参数和值
        //if (log.isSaveRequestData()) {
        //    // 获取参数的信息，传入到数据库中。
        //    setRequestValue(joinPoint, operLog);
        //}
        //// 是否需要保存response，参数和值
        //if (log.isSaveResponseData() && !StringUtils.isEmpty(jsonResult)) {
        //    operLog.setJsonResult(JSON.toJSONString(jsonResult));
        //}
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    //private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) throws Exception {
    //    String requestMethod = operLog.getRequestMethod();
    //    if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
    //        String params = argsArrayToString(joinPoint.getArgs());
    //        operLog.setOperParam(params);
    //    }
    //}

    /**
     * 参数拼装
     */
    //private String argsArrayToString(Object[] paramsArray) {
    //    String params = "";
    //    if (paramsArray != null && paramsArray.length > 0) {
    //        for (Object o : paramsArray) {
    //            if (!StringUtils.isEmpty(o) && !isFilterObject(o)) {
    //                try {
    //                    Object jsonObj = JSON.toJSON(o);
    //                    params += jsonObj.toString() + " ";
    //                } catch (Exception e) {
    //                }
    //            }
    //        }
    //    }
    //    return params.trim();
    //}

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    //@SuppressWarnings("rawtypes")
    //public boolean isFilterObject(final Object o) {
    //    Class<?> clazz = o.getClass();
    //    if (clazz.isArray()) {
    //        return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
    //    } else if (Collection.class.isAssignableFrom(clazz)) {
    //        Collection collection = (Collection) o;
    //        for (Object value : collection) {
    //            return value instanceof MultipartFile;
    //        }
    //    } else if (Map.class.isAssignableFrom(clazz)) {
    //        Map map = (Map) o;
    //        for (Object value : map.entrySet()) {
    //            Map.Entry entry = (Map.Entry) value;
    //            return entry.getValue() instanceof MultipartFile;
    //        }
    //    }
    //    return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
    //            || o instanceof BindingResult;
    //}

    /**
     * 获取访问接口ip地址
     *
     * @return 返回ip地址
     */
    public static String getIpAddress() {
        //获取请求的request信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("获取用户ip地址，避免反向代理获取服务器的ip地址");
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info("访问的ip地址:{}", ip);
        return ip;

    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return 方法
     */
    private Method currentMethod(JoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

}
