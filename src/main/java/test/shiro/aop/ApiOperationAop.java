package test.shiro.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import test.shiro.dto.LogInDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.metadata.ValidateUnwrappedValue;
import java.lang.reflect.Method;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/12  13:18
 * Version: V1.0
 * Description: 接口操作AOP
 * 并将接口操作的信息保存在指定的地方
 * ======================
 */
@Aspect
@Order(0)
@Component
public class ApiOperationAop {


	private static final Logger logger = LoggerFactory.getLogger(ApiOperationAop.class);
	/**
	 * 切入点
	 */
	@Pointcut(value = "execution(* test.shiro.controller..*.*(..)) || @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void pointCut(){
	}


	@Around(value = "pointCut()")
	public Object around(ProceedingJoinPoint point){
		otherOPeration(point);
		Object result = null;
		try {
			result = point.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			logger.info("执行出现异常");
		}
		return result;
	}

	private void otherOPeration(ProceedingJoinPoint point) {
		long startTime = System.currentTimeMillis();

		//获取请求相关的信息
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		String requestURI = request.getRequestURI();
		String ipAddresss = request.getRemoteAddr();

		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		Object[] args = point.getArgs();

		String parameters = JSON.toJSONString(args);
		logger.info("当前调用的方法是：{} ----->入参是：{}",methodName,parameters);
		saveLog(startTime,requestURI,ipAddresss,methodName);

	}

	private void saveLog(long startTime, String requestURI, String ipAddresss, String methodName) {
		//保存在其他的数据下
	}


}
