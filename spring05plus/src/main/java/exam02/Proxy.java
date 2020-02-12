package exam02;

public class Proxy implements Pet{
	private Pet pet;
	
	//자신이 프록시할 대상을 매개변수로 받음
	public Proxy(Pet pet) {
		this.pet = pet;
		//전달받은 객체를 참조하게함
	}
	
	public void cry() {
		System.out.println("울기전(개냥이 공통기능)");
		pet.cry();
		System.out.println("울기후(개냥이 공통기능)");
	}
}
