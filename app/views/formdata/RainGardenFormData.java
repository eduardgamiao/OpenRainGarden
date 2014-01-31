package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.RainGarden;

/**
 * Handles form data for registering and editing rain gardens.
 * @author eduardgamiao
 *
 */
public class RainGardenFormData {
  
  /** First name. */
  public String firstName;
  
  /** Last name. */
  public String lastName;
  
  /** Email. */
  public String email;
  
  /** Telephone. */
  public String telephone;
  
  /** Address. */
  public String address;
  
  /** City. */
  public String city;
  
  /** State. */
  public String state;
  
  /** Zip Code. */
  public String zipCode;
  
  /** Country. */
  public String country;
  
  /** Property Type. */
  public String propertyType;
  
  /** Rain garden description. */
  public String description;
  
  /** Square feet of impervious surface. */
  public String imperviousSurfaceSize;
  
  /** Size of rain garden is square feet. */
  public String rainGardenSize;
  
  /** Disconnected down spout. */
  public Boolean downSpoutsDisconnected;
  
  /** Soil amended. */
  public Boolean soilAmended;
  
  /** Material used to amend soil. */
  public String soilAmenedType;
  
  /** Allow public release of information (op-in only). */
  public Boolean allowInformationPublishing = false;
  
  /**
   * Default constructor.
   */
  public RainGardenFormData() {
    
  }

  /**
   * @param firstName First name.
   * @param lastName Last name.
   * @param email Email.
   * @param telephone Telephone.
   * @param address Address.
   * @param city City.
   * @param state State.
   * @param zipCode Zip Code.
   * @param country Country.
   * @param propertyType Property Type.
   * @param description Description.
   * @param imperviousSurfaceSize Impervious surface size.
   * @param rainGardenSize Rain garden size.
   * @param downSpoutsDisconnected Down spouts disconnected.
   * @param soilAmended Soil amended.
   * @param soilAmenedType Material used to amend soil.
   * @param allowInformationPublishing Make information public.
   */
  public RainGardenFormData(String firstName, String lastName, String email, String telephone, String address,
      String city, String state, String zipCode, String country, String propertyType, String description,
      String imperviousSurfaceSize, String rainGardenSize, Boolean downSpoutsDisconnected, Boolean soilAmended,
      String soilAmenedType, Boolean allowInformationPublishing) {
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
    this.allowInformationPublishing = allowInformationPublishing;
  }
  
  /**
   * Constructor.
   * @param rainGarden RainGarden object. 
   */
  public RainGardenFormData(RainGarden rainGarden) {
    this.firstName = rainGarden.getFirstName();
    this.lastName = rainGarden.getLastName();
    this.email = rainGarden.getEmail();
    this.telephone = rainGarden.getTelephone();
    this.address = rainGarden.getAddress();
    this.city = rainGarden.getCity();
    this.state = rainGarden.getState();
    this.zipCode = rainGarden.getZipCode();
    this.country = rainGarden.getCountry();
    this.propertyType = rainGarden.getPropertyType();
    this.description = rainGarden.getDescription();
    this.imperviousSurfaceSize = rainGarden.getImperviousSurfaceSize();
    this.rainGardenSize = rainGarden.getRainGardenSize();
    this.downSpoutsDisconnected = rainGarden.getDownSpoutsDisconnected();
    this.soilAmended = rainGarden.getSoilAmended();
    this.soilAmenedType = rainGarden.getSoilAmenedType();
    this.allowInformationPublishing = rainGarden.getAllowInformationPublishing();
  }
  
  /**
   * Validation for rain garden registration form.
   * @return If input into form is not valid, a list of errors, else an empty list.
   */
  public List<ValidationError> validate() {
    ArrayList<ValidationError> errors = new ArrayList<>();
    
    if (this.firstName.length() == 0 || this.firstName == null) {
      errors.add(new ValidationError("firstName", "First name is required."));
    }
    if (this.lastName.length() == 0 || this.lastName == null) {
      errors.add(new ValidationError("lastName", "Last name is required."));
    }
    
    return errors.isEmpty() ? null : errors;
  }
  

}
