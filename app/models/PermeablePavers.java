package models;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * An object that represents a permeable pavers.
 */
public class PermeablePavers {

  private long id;
  private String title;
  private String propertyType;
  private String address;
  private String hideAddress;
  private String description;
  private String dateInstalled;
  private String material;
  private String previousMaterial;
  private String size;
  private String installer;
  private UserInfo owner;

  /**
   * Constructor.
   * @param id ID of permeable paver.
   * @param title Title of permeable paver.
   * @param propertyType Type of property of paver's address.
   * @param address Address of permeable paver.
   * @param hideAddress Specify whether to hide address from public.
   * @param description Description of permeable paver.
   * @param dateInstalled Date paver was installed.
   * @param material Material of previous paver.
   * @param previousMaterial Material of material before paver installation.
   * @param size Size of paver.
   * @param installer Installer if paver.
   */
  public PermeablePavers(long id, String title, String propertyType, String address, String hideAddress,
      String description, String dateInstalled, String material, String previousMaterial, String size,
      String installer) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.dateInstalled = dateInstalled;
    this.material = material;
    this.previousMaterial = previousMaterial;
    this.size = size;
    this.installer = installer;
  }

  /**
   * @return the id
   */
  public long getID() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setID(long id) {
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
   * @return the previousMaterial
   */
  public String getPreviousMaterial() {
    return previousMaterial;
  }

  /**
   * @param previousMaterial the previousMaterial to set
   */
  public void setPreviousMaterial(String previousMaterial) {
    this.previousMaterial = previousMaterial;
  }

  /**
   * @return the size
   */
  public String getSize() {
    return size;
  }

  /**
   * @param size the size to set
   */
  public void setSize(String size) {
    this.size = size;
  }

  /**
   * @return the installer
   */
  public String getInstaller() {
    return installer;
  }

  /**
   * @param installer the installer to set
   */
  public void setInstaller(String installer) {
    this.installer = installer;
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
   * @return the hasPicture
   * @throws IOException 
   */
  public boolean hasPicture() throws IOException {
    return FileUtils.directoryContains(new File("public/images/upload"), new File("public/images/upload/pp" + this.id));
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
      return "upload/pp" + this.id;
    }
    return "placeholder.gif";
  }

}
