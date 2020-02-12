package spring;

import java.util.Date;

public class MemberRegisterService {
	//회원정보를 저장하기 위해 확인하는 클래스
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new AlreadyExistingMemberException(
					"dup email" + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(),
				req.getPassword(),
				req.getName(),
				new Date()
				);
		memberDao.insert(newMember);
	}
	
}
