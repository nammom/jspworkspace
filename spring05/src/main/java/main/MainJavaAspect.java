package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap06.Calculator;
import config.JavaConfig;

public class MainJavaAspect {

	public static void main(String[] args){

//		AnnotationConfigApplicationContext ctx = 
//				new AnnotationConfigApplicationContext(JavaConfig.class);
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(JavaConfig.class);
		
		long fiveFact = 0;

		Calculator impCal = ctx.getBean("impCal", Calculator.class);
		fiveFact = impCal.factorial(5);
		System.out.println("impCal.factorial(5) = " + fiveFact);
		
		Calculator recCal = ctx.getBean("recCal", Calculator.class);
		fiveFact = recCal.factorial(5);
		System.out.println("recCal.factorial(5) = " + fiveFact);

		ctx.close();
		
	}
}
