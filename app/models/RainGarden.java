package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.ebean.Model;
import scala.reflect.internal.Trees.This;

/**
 * An object that represents a rain garden.
 */
@Entity
public class RainGarden extends Model {
  private static final long serialVersionUID = 1L;
  private static final int VALID_DATE_LENGTH = 8;
  
  @Id
  private Long id;
  private String title = "Rain Garden";
  private String propertyType;
  private String address;
  private String hideAddress;
  @Lob
  private String description;
  private String dateInstalled;
  private String rainGardenSize;
  private String waterFlowSourceSize;
  @Lob
  private String waterFlowDescription;
  private String infiltrationRate;
  @Lob
  private byte [] image;
  private boolean isApproved = false;
  private String externalImageURL;
  
  @ManyToOne
  private UserInfo owner;
  
  @ManyToMany (cascade = CascadeType.ALL)
  private List<Plant> plants = new ArrayList<Plant>();
  
  @OneToMany (mappedBy = "garden", cascade = CascadeType.ALL)
  private List<Comment> commentList = new ArrayList<Comment>();
  
  /**
   * Constructor.
   * @param title Title of rain garden
   * @param propertyType Property type of rain garden's address.
   * @param address Address of rain garden.
   * @param hideAddress Specify whether or not to hide address from public viewing. 
   * @param description Description of rain garden.
   * @param dateInstalled Date installed.
   * @param plants Plants used in rain garden.
   * @param rainGardenSize Size of rain garden.
   * @param waterFlowSourceSize Size of water flow source.
   * @param waterFlowDescription Description of water flow.
   * @param infiltrationRate Infiltration rate.
   */
  public RainGarden(String title, String propertyType, String address, String hideAddress, String description,
      String dateInstalled, List<String> plants, String rainGardenSize, String waterFlowSourceSize,
      String waterFlowDescription, String infiltrationRate) {
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.dateInstalled = dateInstalled;
    this.plants = convertToPlantList(plants);
    this.rainGardenSize = rainGardenSize;
    this.waterFlowSourceSize = waterFlowSourceSize;
    this.waterFlowDescription = waterFlowDescription;
    this.infiltrationRate = infiltrationRate;
  }
  
  /**
   * @return the id
   */
  public Long getID() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setID(Long id) {
    this.id = id;
  }
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }
  /**
   * @return the propertyType
   */
  public String getPropertyType() {
    return propertyType;
  }
  /**
   * @param propertyType the propertyType to set
   */
  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }
  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }
  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }
  /**
   * @return the hideAddress
   */
  public String getHideAddress() {
    return hideAddress;
  }
  /**
   * @param hideAddress the hideAddress to set
   */
  public void setHideAddress(String hideAddress) {
    this.hideAddress = hideAddress;
  }
  
  /**
   * Returns true if hideAddress equals "Yes" and false if hideAddress equals "No".
   * @return True if hideAddress is "Yes". False if hideAddress is "No".
   */
  public boolean hideAddress() {
	  return this.hideAddress.equals("Yes");
  }
  
  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }
  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * @return the dateInstalled
   */
  public String getDateInstalled() {
    if (this.dateInstalled.length() < VALID_DATE_LENGTH) {
      return "N/A";
    }
    return dateInstalled;
  }
  
  /**
   * @param dateInstalled the dateInstalled to set
   */
  public void setDateInstalled(String dateInstalled) {
    this.dateInstalled = dateInstalled;
  }

  /**
   * @return the plants
   */
  public List<String> getPlants() {
    return convertToPlantNameList(this.plants);
  }

  /**
   * @param plants the plants to set
   */
  public void setPlants(List<String> plants) {
    this.plants = convertToPlantList(plants);
  }

  /**
   * @return the rainGardenSize
   */
  public String getRainGardenSize() {
    return rainGardenSize;
  }

  /**
   * @param rainGardenSize the rainGardenSize to set
   */
  public void setRainGardenSize(String rainGardenSize) {
    this.rainGardenSize = rainGardenSize;
  }

  /**
   * @return the waterFlowSourceSize
   */
  public String getWaterFlowSourceSize() {
    return waterFlowSourceSize;
  }

  /**
   * @param waterFlowSourceSize the waterFlowSourceSize to set
   */
  public void setWaterFlowSourceSize(String waterFlowSourceSize) {
    this.waterFlowSourceSize = waterFlowSourceSize;
  }

  /**
   * @return the waterFlowDescription
   */
  public String getWaterFlowDescription() {
    return waterFlowDescription;
  }

  /**
   * @param waterFlowDescription the waterFlowDescription to set
   */
  public void setWaterFlowDescription(String waterFlowDescription) {
    this.waterFlowDescription = waterFlowDescription;
  }

  /**
   * @return the infiltrationRate
   */
  public String getInfiltrationRate() {
    return infiltrationRate;
  }

  /**
   * @param infiltrationRate the infiltrationRate to set
   */
  public void setInfiltrationRate(String infiltrationRate) {
    this.infiltrationRate = infiltrationRate;
  }

  /**
   * @return the owner.
   */
  public UserInfo getOwner() {
    return owner;
  }
  
  /**
   * @param owner the owner to set
   */
  public void setOwner(UserInfo owner) {
    this.owner = owner;
  }
  
  /**
   * @return the image
   */
  public byte [] getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(byte [] image) {
    this.image = image;
  }

  /**
   * @return the isApproved
   */
  public boolean isApproved() {
    return isApproved;
  }

  /**
   * @param isApproved the isApproved to set
   */
  public void setApproved(boolean isApproved) {
    this.isApproved = isApproved;
  }
  
  /**
   * @return the externalImageURL
   */
  public String getExternalImageURL() {
    return externalImageURL;
  }

  /**
   * @param externalImageURL the externalImageURL to set
   */
  public void setExternalImageURL(String externalImageURL) {
    this.externalImageURL = externalImageURL;
  }

  /**
   * @return the hasPicture
   */
  public boolean hasPicture() {
    if (this.image == null) {
      return false;
    }
    else {
      return (this.image.length > 0);
    }
  }

  /**
   * Get month installed.
   * @return The month the rain garden was installed.
   */
  public String getMonthInstalled() {
    String [] date = this.getDateInstalled().split("/");
    if (date.length > 0) {
    return date[0];
    }
    return "";
  }
  
  /**
   * Get day installed.
   * @return The day the rain garden was installed.
   */
  public String getDayInstalled() {
    String [] date = this.getDateInstalled().split("/");
    if (date.length > 1) {
    return date[1];
    }
    return "";
  }
  
  /**
   * Get year installed.
   * @return The year the rain garden was installed.
   */
  public String getYearInstalled() {
    String [] date = this.getDateInstalled().split("/");
    if (date.length > 2) {
    return date[2];
    }
    return "";
  }
  
  /**
   * Return name of owner.
   * @return The owner's name.
   */
  public String getNameOfOwner() {
    return this.owner.getFirstName() + " " + this.owner.getLastName();
  }
  
  /**
   * Return a list of garden comments.
   * @return A list of comments about a rain garden.
   */
  public List<Comment> getComments() {
    return commentList;
  }
  
  /**
   * Add comment to rain garden.
   * @param comment The comment to add.
   */
  public void addComment(Comment comment) {
    this.commentList.add(comment);
  }
  
  /**
   * Convert a list of plant names to plants.
   * @param plantNameList A list of plant names.
   * @return A list of plants.
   */
  private List<Plant> convertToPlantList(List<String> plantNameList) {
    List<Plant> plants = new ArrayList<Plant>();
    for (String plantName : plantNameList) {
      plants.add(Plant.find().where().eq("name", plantName).findUnique());
    }
    return plants;
  }
  
  /**
   * Convert a list of plants to a list of plant names.
   * @param plantList A list of plants.
   * @return A list of plant names.
   */
  private List<String> convertToPlantNameList(List<Plant> plantList) {
    List<String> plants = new ArrayList<String>();
    for (Plant plant : plantList) {
      plants.add(plant.getName());
    }
    return plants;
  }
  
  /**
   * Return plants as a list.
   * @return A list of plants.
   */
  public List<Plant> getPlantsAsList() {
    return this.plants;
  }
  
  /**
   * Return a valid date if it exists.
   * @return The string for date installed formatted for CSV format.
   */
  public String getDateInstalledAsCSV() {
    if (this.dateInstalled.length() < VALID_DATE_LENGTH) {
      return "";
    }
    return dateInstalled;   
  }
  
  /**
   * Check if a user is the owner of a rain garden.
   * @param userInfo The user to check.
   * @return True if the user is the owner, false otherwise.
   */
  public boolean isOwner(UserInfo userInfo) {
    if (userInfo == null) {
      return false;
    }
    return (this.owner.getId() == userInfo.getId());
  }
  
  /**
   * Return the information of the garden in CSV (comma-separated value) format.
   * @return The information of a garden in formatted as a CSV.
   */
  public String formatToCSV() {
    return "\"" + this.title + "\", " + "\"" + this.propertyType + "\", " + "\"" + this.address + "\", " + "\"" 
           + this.description + "\", " + "\"" + getDateInstalledAsCSV() + "\", " + "\"" + this.rainGardenSize + "\", "
           + "\"" + this.waterFlowSourceSize + "\", " + "\"" + this.waterFlowDescription + "\", " 
           + "\"" + this.infiltrationRate + "\", " + "\"" + this.getOwner().getEmail() + "\"\n";
  }
  
  /**
   * Check if a user can edit a rain garden.
   * @param userInfo The user to check. 
   * @return True if the user is the owner or an admin otherwise false.
   */
  public boolean canEdit(UserInfo userInfo) {
    if (userInfo == null) {
      return false;
    }
    return (userInfo.isAdmin() || isOwner(userInfo));
  }
  
  /**
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for rain gardens.
   */
  public static Finder<Long, RainGarden> find() {
    return new Finder<Long, RainGarden>(Long.class, RainGarden.class);
  }
}
