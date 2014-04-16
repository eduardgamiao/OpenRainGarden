package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.apache.commons.io.FileUtils;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import controllers.routes;

/**
 * An object that represents a rain garden.
 */
@Entity
public class RainGarden extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String title;
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
  private String numberOfRainGardens;
  private String commentKey;
  @Lob
  private byte [] image;
  
  @ManyToOne
  private UserInfo owner;
  private List<String> plants = new ArrayList<String>();
  
  /**
   * Constructor.
   * @param id ID of rain garden.
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
   * @param numberOfRainGardens Number of rain gardens.
   */
  public RainGarden(Long id, String title, String propertyType, String address, String hideAddress, String description,
      String dateInstalled, List<String> plants, String rainGardenSize, String waterFlowSourceSize,
      String waterFlowDescription, String infiltrationRate, String numberOfRainGardens) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.dateInstalled = dateInstalled;
    this.plants = plants;
    this.rainGardenSize = rainGardenSize;
    this.waterFlowSourceSize = waterFlowSourceSize;
    this.waterFlowDescription = waterFlowDescription;
    this.infiltrationRate = infiltrationRate;
    this.numberOfRainGardens = numberOfRainGardens;
    this.commentKey = "rg" + this.id;
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
   * Returns true if hideAddress equals "Yes" and false if hideAddress equals "No"
   * @return
   */
  public boolean hideAddress() {
	  if (this.hideAddress.equals("Yes")) {
		  return true;
	  }
	  else {
		  return false;
	  }
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
    return plants;
  }

  /**
   * @param plants the plants to set
   */
  public void setPlants(List<String> plants) {
    this.plants = plants;
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
   * @return the numberOfRainGardens
   */
  public String getNumberOfRainGardens() {
    return numberOfRainGardens;
  }

  /**
   * @param numberOfRainGardens the numberOfRainGardens to set
   */
  public void setNumberOfRainGardens(String numberOfRainGardens) {
    this.numberOfRainGardens = numberOfRainGardens;
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
   * Get the picture name tied to a rain garden.
   * @return The name of the picture tied to the rain garden.
   * @throws IOException 
   */
  public String getPictureName() throws IOException {
    if (hasPicture()) {
      return "images/upload/rg" + this.id;
    }
    return "images/placeholder.gif";
  }
  
  /**
   * Return key of rain garden.
   * @return The key of the rain garden.
   */
  public String getKey() {
    return this.commentKey;
  }
  
  /**
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for rain gardens.
   */
  public static Finder<Long, RainGarden> find() {
    return new Finder<Long, RainGarden>(Long.class, RainGarden.class);
  }
}
