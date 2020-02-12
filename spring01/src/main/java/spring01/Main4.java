package spring01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main4 {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:applicationContext3.xml");
		
		Calculator g = ctx.getBean("cal", Calculator.class);
		int msg = g.setcal('+',1,2);
		System.out.println(msg);
		ctx.close();
	}
}
