package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

@Aspect
public class ExeTimeAspect2 implements Ordered{
	
	@Pointcut("execution(public * chap06..*(..))")
	private void publicTarget(){
		
	}
	
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime();
		Object result = null;
		try{
			result = joinPoint.proceed();
			System.out.println(joinPoint);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finish = System.nanoTime();
		Signature sig = joinPoint.getSignature();
		System.out.printf("%s, %s(%s) 실행 시간 : %d ns\n", joinPoint.getTarget().getClass().getSimpleName(), sig.getName(),
				Arrays.toString(joinPoint.getArgs()), (finish - start));
		return result;
	}
//	@Before("publicTarget()")
//	public void measure() throws Throwable{
//		System.out.println("test");
//	}
//	

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
}

