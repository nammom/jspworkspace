


package spring;

import java.util.Date;

public class MemberRegisterService {
	private MemberDaoImpl memberDaoImpl;
	
	public MemberRegisterService(MemberDaoImpl memberDaoImpl){
		this.memberDaoImpl = memberDaoImpl;
	}
	
	public void regist(RegisterRequest req){
		Member member = memberDaoImpl.selectByEmail(req.getEmail());
		if(member != null){
			throw new AlreadyExistingMemberException(
						"dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(),
				req.getPassword(),
				req.getName(),
				new Date()
				);
		memberDaoImpl.insert(newMember);		
	}
}
