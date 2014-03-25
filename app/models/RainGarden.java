package models;

import java.util.ArrayList;
import java.util.List;

/**
 * An object that represents a rain garden.
 */
public class RainGarden {

  private Long id;
  private String title;
  private String propertyType;
  private String address;
  private String hideAddress;
  private String description;
  private String dateInstalled;
  private List<String> plants = new ArrayList<String>();
  private String rainGardenSize = "N/A";
  private String waterFlowSourceSize = "N/A";
  private String waterFlowDescription = "N/A";
  private String infiltrationRate = "N/A";
  private String numberOfRainGardens = "N/A";
  private boolean hasPicture = false;
  private UserInfo owner;
  
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
    if (!rainGardenSize.isEmpty()) {
      this.rainGardenSize = rainGardenSize;
    }
    if (!waterFlowSourceSize.isEmpty()) {
      this.waterFlowSourceSize = waterFlowSourceSize;
    }
    if (!waterFlowSourceSize.isEmpty()) {
      this.waterFlowDescription = waterFlowDescription;
    }
    if (!infiltrationRate.isEmpty()) {
      this.infiltrationRate = infiltrationRate;
    }
    if (!numberOfRainGardens.isEmpty()) {
      this.numberOfRainGardens = numberOfRainGardens;
    }
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
   * @return the hasPicture
   */
  public boolean hasPicture() {
    return hasPicture;
  }
  /**
   * @param hasPicture the hasPicture to set
   */
  public void setHasPicture(boolean hasPicture) {
    this.hasPicture = hasPicture;
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
   */
  public String getPictureName() {
    if (this.hasPicture) {
      return "rg" + this.id;
    }
    return "placeholder.gif";
  }
  
}
