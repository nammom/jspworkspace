package spring;

public class MemberInfoPrinter {
	private MemberDaoImpl memDaoImpl;
	private MemberPrinter printer;
	
	public void setMemberDao(MemberDaoImpl memberDaoImpl){
		this.memDaoImpl = memberDaoImpl;
	}
	
	public void setMemberPrinter(MemberPrinter printer){
		this.printer = printer;
	}
	
	public void printMemberInfo(String email){
		Member member = memDaoImpl.selectByEmail(email);
		if(member == null){
			System.out.println("정보 없음!\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
}
