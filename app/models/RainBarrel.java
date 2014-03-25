package models;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * An object that represents a rain barrel.
 */
public class RainBarrel {

  private Long id;
  private String title;
  private String propertyType;
  private String address;
  private String hideAddress;
  private String description;
  private String dateInstalled;
  private String rainBarrelType = "N/A";
  private String capacity = "N/A";
  private String color = "N/A";
  private String material = "N/A";
  private String estimatedCost = "N/A";
  private String waterUse = "N/A";
  private String overflowFrequency = "N/A";
  private String cover = "N/A";
  private String obtainedFrom = "N/A";
  private String installationType = "N/A";
  private String numberOfRainBarrels = "N/A";
  private UserInfo owner;
    
  /**
   * Constructor.
   * @param id ID of rain barrel.
   * @param title Title of rain barrel.
   * @param propertyType Property type of rain barrel address.
   * @param address Address of rain barrel.
   * @param hideAddress Hide address from public.
   * @param description Description of rain barrel.
   * @param dateInstalled Date barrel was installed.
   * @param rainBarrelType Type of rain barrel.
   * @param capacity Capacity of rain barrel.
   * @param color Color of rain barrel.
   * @param material Material of rain barrel.
   * @param estimatedCost Cost of rain barrel.
   * @param waterUse Water usage from rain barrel collection.
   * @param overflowFrequency Overflow frequency.
   * @param cover Cover type for barrel.
   * @param obtainedFrom Place rain barrel was obtained.
   * @param installationType Type of installation for rain barrel.
   * @param numberOfRainBarrel Number of rain barrels on property. 
   */
  public RainBarrel(Long id, String title, String propertyType, String address, String hideAddress, String description,
      String dateInstalled, String rainBarrelType, String capacity, String color, String material,
      String estimatedCost, String waterUse, String overflowFrequency, String cover, String obtainedFrom,
      String installationType, String numberOfRainBarrel) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.dateInstalled = dateInstalled;
    if (!rainBarrelType.isEmpty()) {
      this.rainBarrelType = rainBarrelType;
    }
    if (!capacity.isEmpty()) {
      this.capacity = capacity;
    }
    if (!color.isEmpty()) {
      this.color = color;
    }
    if (!material.isEmpty()) {
      this.material = material;
    }
    if (!estimatedCost.isEmpty()) {
      this.estimatedCost = estimatedCost;
    }
    if (!waterUse.isEmpty()) {
      this.waterUse = waterUse;
    }
    if (!overflowFrequency.isEmpty()) {
      this.overflowFrequency = overflowFrequency;
    }
    if (!cover.isEmpty()) {
      this.cover = cover;
    }
    if (!obtainedFrom.isEmpty()) {
      this.obtainedFrom = obtainedFrom;
    }
    if (!installationType.isEmpty()) {
      this.installationType = installationType;
    }
    if (!numberOfRainBarrels.isEmpty()) {
      this.numberOfRainBarrels = numberOfRainBarrel;
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
   * @return the numberOfRainGardens
   */
  public String getNumberOfRainBarrels() {
    return numberOfRainBarrels;
  }

  /**
   * @param numberOfRainBarrels the numberOfRainGardens to set
   */
  public void setNumberOfRainBarrels(String numberOfRainBarrels) {
    this.numberOfRainBarrels = numberOfRainBarrels;
  }

  /**
   * @return the rainBarrelType
   */
  public String getRainBarrelType() {
    return rainBarrelType;
  }

  /**
   * @param rainBarrelType the rainBarrelType to set
   */
  public void setRainBarrelType(String rainBarrelType) {
    this.rainBarrelType = rainBarrelType;
  }

  /**
   * @return the capacity
   */
  public String getCapacity() {
    return capacity;
  }

  /**
   * @param capacity the capacity to set
   */
  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  /**
   * @return the color
   */
  public String getColor() {
    return color;
  }

  /**
   * @param color the color to set
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * @return the material
   */
  public String getMaterial() {
    return material;
  }

  /**
   * @param material the material to set
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * @return the estimatedCost
   */
  public String getEstimatedCost() {
    return estimatedCost;
  }

  /**
   * @param estimatedCost the estimatedCost to set
   */
  public void setEstimatedCost(String estimatedCost) {
    this.estimatedCost = estimatedCost;
  }

  /**
   * @return the waterUse
   */
  public String getWaterUse() {
    return waterUse;
  }

  /**
   * @param waterUse the waterUse to set
   */
  public void setWaterUse(String waterUse) {
    this.waterUse = waterUse;
  }

  /**
   * @return the overflowFrequency
   */
  public String getOverflowFrequency() {
    return overflowFrequency;
  }

  /**
   * @param overflowFrequency the overflowFrequency to set
   */
  public void setOverflowFrequency(String overflowFrequency) {
    this.overflowFrequency = overflowFrequency;
  }

  /**
   * @return the cover
   */
  public String getCover() {
    return cover;
  }

  /**
   * @param cover the cover to set
   */
  public void setCover(String cover) {
    this.cover = cover;
  }

  /**
   * @return the obtainedFrom
   */
  public String getObtainedFrom() {
    return obtainedFrom;
  }

  /**
   * @param obtainedFrom the obtainedFrom to set
   */
  public void setObtainedFrom(String obtainedFrom) {
    this.obtainedFrom = obtainedFrom;
  }

  /**
   * @return the installationType
   */
  public String getInstallationType() {
    return installationType;
  }

  /**
   * @param installationType the installationType to set
   */
  public void setInstallationType(String installationType) {
    this.installationType = installationType;
  }
  
  /**
   * Get month installed.
   * @return The month the rain barrel was installed.
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
   * @return The day the rain barrel was installed.
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
   * @return The year the rain barrel was installed.
   */
  public String getYearInstalled() {
    String [] date = this.getDateInstalled().split("/");
    if (date.length > 2) {
    return date[2];
    }
    return "";
  }

  /**
   * @return the hasPicture
   * @throws IOException Thrown if there is an error during file checking.
   */
  public boolean hasPicture() throws IOException {
    return FileUtils.directoryContains(new File("public/images/upload"), new File("public/images/upload/rb" + this.id));
  }

  /**
   * @return the owner
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
   * Get the picture name tied to a rain barrel..
   * @return The name of the picture tied to the rain barrel.
   * @throws IOException Thrown if there is an error during file checking.
   */
  public String getPictureName() throws IOException {
    if (hasPicture()) {
      return "upload/rb" + this.id;
    }
    return "placeholder.gif";
  }
}
