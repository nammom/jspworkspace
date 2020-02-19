package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthInfo;
import spring.AuthService;
import spring.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/login")
public class LoginController {
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(LoginCommand loginCommand,
					@CookieValue(value="REMEMBER", required=false)Cookie cookie) {
								//value: 쿠키이름
		if(cookie != null) {
			loginCommand.setEmail(cookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(LoginCommand loginCommand, Errors errors,
						HttpSession session, HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);//에러체크
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try{
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(), loginCommand.getPassword());
			
			//AuthInfo : 로그인 정보DTO
			//authenticate(email, password)
			//이메일 디비에서 찾아서 디비 비번과 사용자가 입력한 비번이 맞으면 정보 담아서 AuthInfo반환
			//틀리면 예외 반환
	
			session.setAttribute("authInfo", authInfo);
			//아이디 비번 같음, 로그인이 성공하면 => session에 로그인 정보DTO 담음 : authInfo 
	
			Cookie rememberCookie =
					new Cookie("REMEMBER", loginCommand.getEmail());
								//쿠키이름 : REMEMBER/ 값 : loginCommand의 이메일)
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {	//이메일기억하기 true인경우
				rememberCookie.setMaxAge(60*60*24*30);
			}else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess";
		}
		catch(IdPasswordNotMatchingException e) {
			errors.reject("IdPasswordNotMatching");
			//reject()객체 자체에 대한 검증을 하는 경우 사용(글로벌 에러코드)띠용?
			return "login/loginForm";
		}
	}
}
