package spring;

import spring.*;

public class Assembler {
//위에서 만든 클래스들을 사용하는 조립기
	//객체 조립기란 객체 생성에 사용할 클래스를 변경하기 위해 코드를 변경하지 않고 객체를 주입하는 방법이다.
	//조립기는 객체를 생성하고 의존 객체를 주입해주는 기능을 제공,
	//또한 특정 객체를 필요로 하는 곳에 객체를 제공할 수 있음
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;

	public Assembler() {
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao); 
		pwdSvc = new ChangePasswordService(memberDao);
	} 
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
	//의존 DI의 개념을 이해하고 
	//객체를 생성하고 의존을 주입해주는 역할을 하는것이 조립기
	//Spring은 바로 이 조립기의 역할과 같은 기능을 제공하는 개발 프레임 워크이다.
}
