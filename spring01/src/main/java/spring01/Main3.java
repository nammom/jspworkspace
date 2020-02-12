package spring01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main3 {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:applicationContext2.xml");
		
		Greeter g1 = ctx.getBean("greeter1", Greeter.class);
		Greeter g2 = ctx.getBean("greeter2", Greeter.class);
		System.out.println(g1 == g2);// 객체가 싱글톤으로 관리됨, 빈을 하나 더 만들고 싶으면 빈을 하나더 정의해야 함
		ctx.close();
	}
}
