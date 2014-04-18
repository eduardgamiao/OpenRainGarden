package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class UserInfo extends Model {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String password;
	private boolean admin = false;
	
	// Relationships.
	@OneToMany (mappedBy = "owner")
	private List<RainGarden> gardens = new ArrayList<RainGarden>();
	
  @OneToMany (mappedBy = "poster")
  private List<GardenComment> gardenComments = new ArrayList<GardenComment>();
	
  @OneToMany (mappedBy = "owner")
	private List<RainBarrel> barrels = new ArrayList<RainBarrel>();

  @OneToMany (mappedBy = "poster")
  private List<BarrelComment> barrelComments = new ArrayList<BarrelComment>();
  
  @OneToMany (mappedBy = "owner")
  private List<PermeablePavers> pavers = new ArrayList<PermeablePavers>();

	public UserInfo(String firstName, String lastName, String email, String telephone, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.password = password;		
	}
	public UserInfo(String firstName, String lastName, String email, String telephone, String password, Boolean isadmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.password = password;	
		this.admin = isadmin;
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
  
  /**
   * @return the barrels
   */
  public List<RainBarrel> getBarrels() {
    return barrels;
  }

  /**
   * @param barrels the barrels to set
   */
  public void setBarrels(List<RainBarrel> barrels) {
    this.barrels = barrels;
  }

  /**
   * @return the pavers
   */
  public List<PermeablePavers> getPavers() {
    return pavers;
  }

  /**
   * @param pavers the pavers to set
   */
  public void setPavers(List<PermeablePavers> pavers) {
    this.pavers = pavers;
  }

  /**
   * Return name of UserInfo.
   * @return The name of the UserInfo.
   */
  public String getName() {
    return this.firstName + " " + this.lastName;
  }
  
  /**
   * Add a rain garden to user.
   * @param garden The rain gardne to add.
   */
  public void addGarden(RainGarden garden) {
    this.gardens.add(garden);
  }
  
  /**
   * Delete a rain garden from the user's list.
   * @param garden The rain garden to delete.
   */
  public void deleteGarden(RainGarden garden) {
    this.getGardens().remove(garden);
  }
    
  /**
   * Delete a rain barrel from the user's list.
   * @param barrel The rain barrel to delete.
   */
  public void deleteBarrel(RainBarrel barrel) {
    this.getBarrels().remove(barrel);
  }
  
  /**
   * Delete a permeable paver from the user's list.
   * @param paver The permeable paver to delete.
   */
  public void deletePaver(PermeablePavers paver) {
    this.getPavers().remove(paver);
  }
  
  /**
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for user info.
   */
  public static Finder<Long, UserInfo> find() {
    return new Finder<Long, UserInfo>(Long.class, UserInfo.class);
  }
}
