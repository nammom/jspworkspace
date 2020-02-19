package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator{
			//Validator를 구현하여 객체의 값을 검증하는 기능/Validate()메서드/을 정의하자
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
	}
	
	//suport()메서드/타입검증/ 검증할 수 있는 타입인지를 검사하는 용도로 사용한다.
	public boolean supports(Class<?> arg0) {
		return RegisterRequest.class.isAssignableFrom(arg0);
		/*
		 스프링 MVC에서 전달받은 객체를 자동으로 검증할 경우 올바르게 구현해야 함
		 현재 실습에서는 사용하지 않음
		 */
	}
	 
	//Validate()메서드는 첫번째 파라미터로 받은 객체를 검증하고 결과를 errors에 담는다.
	public void validate(Object target, Errors errors) {
		//target : 검사 대상 객체
		//errors : 검사 결과 에러코드를 저장하는 객체
		RegisterRequest regReq = (RegisterRequest)target;
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			//이메일이 null 또는 isEmpty
			errors.rejectValue("email", "required");
			//에러코드 저장
		}else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				//이메일이 정규식과 맞지 않다면
				errors.rejectValue("email", "bad");	//errors.rejectValue(필드, 에러코드) : 에러코드 저장메서드
				//에러코드 저장
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
						//null, 공백문자, 길이가 0인경우
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
						//null, 길이가 0
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		//검사대상객체가 비어있는지 확인하고(target의 name) 비어있다면=> errors객체에 required추가
		//target을 전달 받지않아도 target의 name값을 검사할 수 있다.
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
	
	
}
