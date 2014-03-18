package models;

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
  private List<String> plants;
  
  /**
   * Constructor.
   * @param id Rain garden ID.
   * @param title Title of rain garden.
   * @param propertyType Property type of rain garden location.
   * @param address Address of rain garden.
   * @param hideAddress Hide address from public view.
   * @param description Description of rain garden.
   * @param dateInstalled Installation date of rain garden.
   */
  public RainGarden(Long id, String title, String propertyType, String address, String hideAddress, String description,
      String dateInstalled) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.dateInstalled = dateInstalled;
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
  
}
