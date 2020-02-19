package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.AlreadyExistingMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {
	//DAO기능을 사용하기 위해 써준것
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(
			MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	
	@RequestMapping("/register/step1")
//	@RequestMapping(value="/register/step1", method = RequestMethod.POST)
	public String handleStep1() {
		return "register/step1";
	}
	
	//어노테이션으로 파라미터 받기
	//step1.jsp 폼은 POST방식으로 전송
	@RequestMapping(value="/register/step2", method = RequestMethod.POST)
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false")Boolean agree,
				Model model) {				//value(HTTP요청 파라미터 name) defaultValue(요청파라미터의 기본값)
				//자동으로 스프링이 생성해서 모델 넣어줌(모델 : 서버쪽에서 처리한 결과를 담아두는 역할)							
		if(!agree) {
			return "register/step1";
			//이/register/step2 url이면 이경우에  step1.jsp페이지를 보여주겠다
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
	
	//웹브라우저에서 약관동의를 거치지않고 직접 /register/step2로 요청하는 경우 
	@RequestMapping(value="/register/step2", method = RequestMethod.GET)
	public String handleStep2() {
		//리다이렉트해줌
		return "redirect:register/step1";
				//step1 웹경로로 다시 요청시키는것
	}
	
	
	@RequestMapping(value="/register/step3", method = RequestMethod.POST)
	public String handleStep3(RegisterRequest regReq, Errors errors) {
								//커맨드 객체
		
		new RegisterRequestValidator().validate(regReq, errors);
		//에러 검증(에러 객체에 에러 담겨나옴)
		
		if(errors.hasErrors()) {	//validate실행 중 한번이라도 rejectValue()가 실행되었을 경우 true를 반환
			return "register/step2";	
		}
		try {
				memberRegisterService.regist(regReq);
				return "register/step3";
				//커맨드객체의 클래스 이름(첫글자 소문자로 변경)을 사용해서 커맨드 객체를 뷰에전달
		}catch(AlreadyExistingMemberException e) {
			//regist()할때 동일한 이메일 주소인 경우 에러 캐치 
			errors.rejectValue("email", "duplicate");	//errors객체에 email에 중복 에러 저장
			return "register/step2";
				//커맨드객체의 클래스 이름(첫글자 소문자로 변경)을 사용해서 커맨드 객체를 뷰에전달
		}
	}
	
	
}

                                                                   