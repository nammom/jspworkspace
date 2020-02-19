package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthInfo;
import spring.ChangePasswordService;
import spring.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	private ChangePasswordService changePasswordService; 
	
	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(
				@ModelAttribute("command") ChangePwdCommand pwdCmd) {
		return "edit/changePwdForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCmd,
						Errors errors,
						HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCmd, errors);//에러체크
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		//기존의 세션에서 로그인한 사용자 AuthInfo꺼내서 로그인한 사용자 판별
		
		try { 
			//패스워드 변경
			changePasswordService.changePassword(
					authInfo.getEmail(),
					pwdCmd.getCurrentPassword(),
					pwdCmd.getNewPassword());
			
			return "edit/changePwd";
		}
		catch(IdPasswordNotMatchingException e) {
			errors.rejectValue("currentPassword", "notMatching");
			//reject()객체 자체에 대한 검증을 하는 경우 사용(글로벌 에러코드)띠용?
			return "edit/changePwdForm";
		}
	}
}
