package models;

/**
 * An object that represents a rain garden.
 */
public class RainGarden {
  private String firstName;
  private String lastName;
  private String email;
  private String telephone;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private String country;
  private String propertyType;
  private String description;
  private String imperviousSurfaceSize;
  private String rainGardenSize;
  private Boolean downSpoutsDisconnected;
  private Boolean soilAmended;
  private String soilAmenedType;
  private Boolean allowInformationPublishing = false;
  
  /**
   * @param firstName First name of garden's owner.
   * @param lastName Last name of garden's owner.
   * @param email Email of garden's owner.
   * @param telephone Telephone of garden's owner.
   * @param address Address of the garden.
   * @param city City of the garden.
   * @param state State of the garden.
   * @param zipCode Zip code of the garden.
   * @param country Country of the garden.
   * @param propertyType Type of property the garden is on.
   * @param description Description of the garden.
   * @param imperviousSurfaceSize Square footage of impervious rain garden surface.
   * @param rainGardenSize Square footage of rain garden.
   * @param downSpoutsDisconnected State of down spout connections.
   * @param soilAmended State of the soil.
   * @param soilAmenedType Type of material used to amend soil.
   * @param publishInformation Opt-in for information release.
   */
  public RainGarden(String firstName, String lastName, String email, String telephone, String address, String city,
      String state, String zipCode, String country, String propertyType, String description,
      String imperviousSurfaceSize, String rainGardenSize, Boolean downSpoutsDisconnected, Boolean soilAmended,
      String soilAmenedType, Boolean publishInformation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.telephone = telephone;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
    this.propertyType = propertyType;
    this.description = description;
    this.imperviousSurfaceSize = imperviousSurfaceSize;
    this.rainGardenSize = rainGardenSize;
    this.downSpoutsDisconnected = downSpoutsDisconnected;
    this.soilAmended = soilAmended;
    this.soilAmenedType = soilAmenedType;
    this.allowInformationPublishing = publishInformation;
  }
  

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the telephone
   */
  public String getTelephone() {
    return telephone;
  }


  /**
   * @param telephone the telephone to set
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
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
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the zipCode
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
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
   * @return the imperviousSurfaceSize
   */
  public String getImperviousSurfaceSize() {
    return imperviousSurfaceSize;
  }

  /**
   * @param imperviousSurfaceSize the imperviousSurfaceSize to set
   */
  public void setImperviousSurfaceSize(String imperviousSurfaceSize) {
    this.imperviousSurfaceSize = imperviousSurfaceSize;
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
   * @return the downSpoutsDisconnected
   */
  public Boolean getDownSpoutsDisconnected() {
    return downSpoutsDisconnected;
  }

  /**
   * @param downSpoutsDisconnected the downSpoutsDisconnected to set
   */
  public void setDownSpoutsDisconnected(Boolean downSpoutsDisconnected) {
    this.downSpoutsDisconnected = downSpoutsDisconnected;
  }

  /**
   * @return the soilAmended
   */
  public Boolean getSoilAmended() {
    return soilAmended;
  }

  /**
   * @param soilAmended the soilAmended to set
   */
  public void setSoilAmended(Boolean soilAmended) {
    this.soilAmended = soilAmended;
  }

  /**
   * @return the soilAmenedType
   */
  public String getSoilAmenedType() {
    return soilAmenedType;
  }

  /**
   * @param soilAmenedType the soilAmenedType to set
   */
  public void setSoilAmenedType(String soilAmenedType) {
    this.soilAmenedType = soilAmenedType;
  }


  /**
   * @return the allowInformationPublishing
   */
  public Boolean getAllowInformationPublishing() {
    return allowInformationPublishing;
  }


  /**
   * @param allowInformationPublishing the allowInformationPublishing to set
   */
  public void setAllowInformationPublishing(Boolean allowInformationPublishing) {
    this.allowInformationPublishing = allowInformationPublishing;
  }
  
}
