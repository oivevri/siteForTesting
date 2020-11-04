package co.avri.users.vo;

public class UsersDTO {
	String id;
	String name;
	String gender;
	String role;
	public UsersDTO() {}
	public UsersDTO(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UsersDTO [id=" + id + ", name=" + name + ", gender=" + gender + ", role=" + role + "]";
	}
}
