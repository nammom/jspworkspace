package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


	@ControllerAdvice("spring09_p3")
	public class CommonExceptionHandler{
		
		@ExceptionHandler(Exception.class)
		public String handleException() {
			System.out.println("공통예외처리");
			return "error/commonException";
		}
		//controller에서 예외 발생
		//controller클래스 내에 있는 예외 처리면 거기 ExceptionHandler가 처리
		//없는 예외면 여기로와서 예외처리(모든 예외를 받을 수 있음) 
}
