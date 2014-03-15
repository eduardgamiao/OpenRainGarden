package models;

import play.db.ebean.Model;

public class UserInfo extends Model {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String telephone;
	private String password;
	private boolean admin = false;

	public UserInfo(String name, String email, String telephone, String password) {
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.password = password;		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
