package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	//HttpServletRequest request객체로 파라미터 받기
//	@RequestMapping(value="/register/step2", method = RequestMethod.POST)
//	public String handleStep2(HttpServletRequest request) {
//		String agreeParam = request.getParameter("agree");
//		if(agreeParam == null || !agreeParam.contentEquals("true")) {
//			return "register/step1";
//		}
//		return "register/step2";
//	}

	
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
	public String handleStep3(RegisterRequest regReq) {
							//@ModelAttribute("formData")RegisterRequest regReq
							//커맨드객체이름 변경
								//스프링 : RegisterRequest 띠용?
								//저거 찾아서 들어가보니까 set메서드들있음
								//request에 담긴 파라미터 이름 = set이름 자동으로 set해준후 
								//커맨드객체(RegisterRequest객체)를 인자로 넣어줌
		try {
				memberRegisterService.regist(regReq);
				return "register/step3";
				//커맨드객체의 클래스 이름(첫글자 소문자로 변경)을 사용해서 커맨드 객체를 뷰에전달
		}catch(AlreadyExistingMemberException e) {
			return "register/step2";
				//커맨드객체의 클래스 이름(첫글자 소문자로 변경)을 사용해서 커맨드 객체를 뷰에전달
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

		
}

                                                                   