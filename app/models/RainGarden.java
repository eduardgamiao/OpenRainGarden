package models;

import java.io.IOException;
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
  @Lob
  private byte [] image;
  
  @ManyToOne
  private UserInfo owner;
  
  @ManyToMany (cascade = CascadeType.PERSIST)
  private List<Plant> plants = new ArrayList<Plant>();
  
  @OneToMany (mappedBy = "garden")
  private List<GardenComment> comments = new ArrayList<GardenComment>();
  
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
  public List<GardenComment> getComments() {
    return comments;
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
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for rain gardens.
   */
  public static Finder<Long, RainGarden> find() {
    return new Finder<Long, RainGarden>(Long.class, RainGarden.class);
  }
}
