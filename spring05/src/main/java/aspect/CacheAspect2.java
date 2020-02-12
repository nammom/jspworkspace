package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
public class CacheAspect2 {
	private Map<Long, Object> cache = new HashMap<>();

	@Pointcut("execution(public * chap06..*(..))")
	private void publicTarget2(){
		
	}
	
	@Around("publicTarget2()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		Long num = (Long)joinPoint.getArgs()[0];
		if(cache.containsKey(num)){
			System.out.printf("CacheAspect2 : Cache에서 구함[%d]\n", num);
			joinPoint.proceed();
			return cache.get(num);
		}
		Object result = joinPoint.proceed();
		cache.put(num, result);
		System.out.printf("CacheAspect2 : Cache에 추가[%d]\n", num);
		return result;
	}
} 
