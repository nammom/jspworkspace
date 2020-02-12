package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class ExeTimeAspect {
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
