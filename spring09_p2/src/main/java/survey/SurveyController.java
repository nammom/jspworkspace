package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
//	@RequestMapping(method=RequestMethod.GET)
//	public String form() {
//		return "survey/surveyForm";
//	}
//
	
	private List<Question> createQuestions(){
		Question q1 = new Question("당신의 역할은?", 
							Arrays.asList("서버", "프론트", "풀스택"));

		Question q2 = new Question("주로 사용하는 개발 도구는?", 
							Arrays.asList("이클립스", "인텔리j", "서브라임"));
		

		Question q3 = new Question("하고싶은말");
		
		return Arrays.asList(q1, q2, q3);
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//		List<Question> questions = createQuestions();
//		model.addAttribute("questions", questions);
//		return "survey/surveyForm2";
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form(Model model) {
		List<Question> questions = createQuestions();
		ModelAndView mav = new ModelAndView();
		//Model을 이용하여 View에 전달할 데이터를 설정 => ModelandView클래스 사용하면 한번에 묶어서 처리완료
		mav.addObject("questions", questions);
		//ModelandView에 model에 저장한것처럼 저장
		mav.setViewName("survey/surveyForm");
		//리턴할 뷰경로 set
		return mav;
		//mav(뷰경로 + object)
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData")AnsweredData data) {
		return "survey/submitted";
	}

}
