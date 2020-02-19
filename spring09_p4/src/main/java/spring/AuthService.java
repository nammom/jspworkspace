package spring;

public class AuthService {
	private MemberDaoImpl memberDaoImpl;
	
	public void setMemberDaoImpl(MemberDaoImpl memberDaoImpl) {
		this.memberDaoImpl = memberDaoImpl;
	}
	
	public AuthInfo authenticate(String email, String password) {
		Member member = memberDaoImpl.selectByEmail(email);
		if(member == null) {
			throw new IdPasswordNotMatchingException();
		}
		if(!member.matchPassword(password)) {
			throw new IdPasswordNotMatchingException();	
		}
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
}
