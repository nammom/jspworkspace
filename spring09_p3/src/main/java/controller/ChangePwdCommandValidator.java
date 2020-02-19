package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdCommandValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return ChangePwdCommand.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		//null, 공백문자, 길이가 0인경우
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
		
	}
}
