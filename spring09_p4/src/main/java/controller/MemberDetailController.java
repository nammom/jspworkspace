package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDaoImpl;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {

		private MemberDaoImpl memberDaoImpl;
		
		public void setMemberDaoImpl(MemberDaoImpl memberDaoImpl) {
			this.memberDaoImpl = memberDaoImpl;
		}
		
		@RequestMapping("/member/detail/{e}")
		///member/detail/123 => 123부분을 {id}로 지정하겠다.
		public String detail(@PathVariable("e")Long memId, Model model) {
							///{id}로 지정한 부분 값 가져오기 : 123
			System.out.println("요청처리");
			Member member = memberDaoImpl.selectById(memId);
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
