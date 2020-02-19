package spring;


public class ChangePasswordService {
	private MemberDaoImpl memberDaoImpl;
	
	public ChangePasswordService(MemberDaoImpl memberDaoImpl){
		this.memberDaoImpl = memberDaoImpl;
	}
	

	public void changePassword(String email, String oldPwd, String newPwd){
		Member member = memberDaoImpl.selectByEmail(email);
		if(member == null){
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd,  newPwd);
		memberDaoImpl.update(member);
	}
}
