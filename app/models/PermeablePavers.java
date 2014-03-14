package models;

import java.util.Date;

/**
 * An object that represents a permeable pavers.
 */
public class PermeablePavers {

  private String title;
  private String propertyType;
  private String address;
  private String hideAddress;
  private String description;
  private Date dateInstalled;
  
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
  public Date getDateInstalled() {
    return dateInstalled;
  }
  /**
   * @param dateInstalled the dateInstalled to set
   */
  public void setDateInstalled(Date dateInstalled) {
    this.dateInstalled = dateInstalled;
  }
  
}
