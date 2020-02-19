package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;

@Controller
public class MemberListController {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@RequestMapping("/member/list")
	public String list(
			@ModelAttribute("cmd")ListCommand listCommand,
			Errors errors, Model model) {
			//타입이 맞지 않는 예외 발생시에 스프링에서 Errors에 "typeMismatch"에러코드를 추가해줌
			if(errors.hasErrors()) {
				return "member/memberList";
			}
		
			if(listCommand.getFrom() != null && listCommand.getTo() != null) {
				//from과 to둘다 null이 아니어야 정보 담음
				List<Member> members = memberDao.selectByRegdate(
							listCommand.getFrom(), listCommand.getTo());
				model.addAttribute("members", members);
			}
			return "member/memberList";
	}
	
}
