package spring09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//webServlet() 그리고 extends 서블릿 =>할필요없음
@Controller
public class HelloController {

	//요청경로를 맵핑.
	//최상위의 hello에서 요청이 들어오면 동작할 것이다.
	@RequestMapping("/hello")
	public String hello(
			Model model,	//자동으로 스프링이 생성해서 모델 넣어줌(모델 : 서버쪽에서 처리한 결과를 담아두는 역할)
			@RequestParam(value= "name", required = false)String name) {	//요청받은 request객체안에 name이라는 파라미터가 있으면  String name에 넣어줌
						System.out.println("hello요청");
						model.addAttribute("greeting", "안녕하세요" + name);
						//이전에 session이나 request에 setAttribute했던것과 같음
		
		return "hello";	//jsp파일 경로로 포워딩해줌
	}
}
