package main;

import org.springframework.context.support.GenericXmlApplicationContext;

import chap06.Calculator;

public class MainXmlPojo {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:aopPojo.xml");
		
		Calculator impCal = ctx.getBean("impCal", Calculator.class);
		//execution(public * chap06..*(..)) :하위의 모든 메서드들이 호출될때 (pointcut) 
		//measure메서드 호출(around)
		//measure메서드내에서 joinPoint.proceed()쓰면 (원래 호출했던)impCal.factorial()함수 실행시켜짐
		long fiveFact1 = impCal.factorial(5);
//		System.out.println("impCal.factorial(5) = " + fiveFact1);
//		
//		Calculator recCal = ctx.getBean("recCal", Calculator.class);
//		long fiveFact2 = recCal.factorial(5);
//		System.out.println("recCal.factorial(5) = " + fiveFact2);
	}
}
