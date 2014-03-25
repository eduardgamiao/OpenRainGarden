package models;

import java.util.ArrayList;
import java.util.List;
import play.db.ebean.Model;

public class UserInfo extends Model {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String password;
	private boolean admin = false;
	private List<RainGarden> gardens = new ArrayList<RainGarden>();

	public UserInfo(String firstName, String lastName, String email, String telephone, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

  /**
   * @return the gardens
   */
  public List<RainGarden> getGardens() {
    return gardens;
  }

  /**
   * @param gardens the gardens to set
   */
  public void setGardens(List<RainGarden> gardens) {
    this.gardens = gardens;
  }
	
}
