package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession(false); 
		//false => HttpSession이 존재하면 있던거 주고 없으면  null반환.
		//true => HttpSession이 존재하면 있던거 주고 없으면 생성해서 줌
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}


}
