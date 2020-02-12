package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//@Aspect를 적용한 클래스는 Pointcut과 Advice설정과 Aspect구현을 함께 제공함

@Aspect
public class ExeTimeAspect2 {
	@Pointcut("execution(public * chap06..*(..))")
	private void publicTarget() {
		
	}
	
	

	//해당 Pointcut에 적용할 공통 기능으로 @Around를 적용한 measure()메서드를 사용함 => measure메서드가 Around Adivice로 사용됨
	
	@Around("publicTarget()") //Around의 값으로는 Pointcut을 설정한 메서드의 이름을 사용한다.
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		}finally {
			long finish = System.nanoTime();
			org.aspectj.lang.Signature sig = joinPoint.getSignature();
			System.out.printf("%s, %s(%s) 실행시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.deepToString(joinPoint.getArgs()),
					(finish-start)
					);
		}
	}
	
}
