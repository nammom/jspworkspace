package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {

		private MemberDao memberDao;
		
		public void setMemberDao(MemberDao memberDao) {
			this.memberDao = memberDao;
		}
		
		@RequestMapping("/member/detail/{id}")
		public String detail(@PathVariable("id")Long memId, Model model) {
			System.out.println("요청처리");
			Member member = memberDao.selectbyId(memId);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			
			model.addAttribute("member", member);
			return "member/memberDetail";
		}
		
		@ExceptionHandler(TypeMismatchException.class)
		public String handlerTypeMismatchException() {
			return "member/invalidId";
		}
		
		
		@ExceptionHandler(MemberNotFoundException.class)
		public String handlerMemberNotFoundException() {
			return "member/noMember";
		}
		//여기 ExceptionHandler가 처리하지 않는 예외는 
		//@ControllerAdvice(프로젝트명)에서 처리됨
}
