package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap06.Calculator;
import chap06.ImplCalculator;
import chap06.RecCalculator;

@Configuration
@EnableAspectJAutoProxy
//@EnableAspectJAutoProxy(proxyTargetClass=true)
public class JavaConfig {
	
	@Bean
	public ExeTimeAspect exeTimeAspect(){
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator impCal(){
		return new ImplCalculator();
	}
	
	@Bean
	public Calculator recCal(){
		return new RecCalculator();
	}
}
