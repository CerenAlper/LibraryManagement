package domain.classes;

public class Member {
	private int id;
	private String name;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public Member(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
}