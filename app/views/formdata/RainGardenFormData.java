package views.formdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import play.data.validation.ValidationError;
import models.RainGarden;

/**
 * Handles form data for registering and editing rain gardens.
 * @author eduardgamiao
 *
 */
public class RainGardenFormData {
  
  /** Title of rain garden. */
  public String title;
  
  /** Property type. */
  public String propertyType;
  
  /** Address of rain garden. */
  public String address;
  
  /** Hide address from public. */
  public String hideAddress;
  
  /** Description of rain garden. */
  public String description; 
  
  /** Installation month. */
  public String month;
  
  /** Installation day. */
  public String day;
  
  /** Installation year. */
  public String year;
 
  /**
   * Default constructor.
   */
  public RainGardenFormData() {
    
  }

  /**
   * Constructor.
   * @param name Name of rain garden.
   * @param propertyType Property type of rain garden location.
   * @param address Address of rain garden.
   * @param hideAddress Hide address of rain garden.
   * @param description Description of rain garden.
   * @param month Month rain garden was installed.
   * @param day Day rain garden was installed.
   * @param year Year rain garden was installed. 
   */
  public RainGardenFormData(String name, String propertyType, String address, String hideAddress, 
                            String description, String month, String day, String year) {
    this.title = name;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.month = month;
    this.day = day;
    this.year = year;
  }
  
  /**
   * Constructor.
   * @param rainGarden RainGarden object. 
   */
  public RainGardenFormData(RainGarden rainGarden) {
    this.title = rainGarden.getTitle();
    this.propertyType = rainGarden.getPropertyType();
    this.address = rainGarden.getAddress();
    this.hideAddress = rainGarden.getHideAddress();
    this.description = rainGarden.getDescription();
  }
  
  /**
   * Validation for rain garden registration form.
   * @return If input into form is not valid, a list of errors, else an empty list.
   */
  public List<ValidationError> validate() {
    ArrayList<ValidationError> errors = new ArrayList<>();
    
    if (this.propertyType.length() == 0 || this.propertyType == null) {
      errors.add(new ValidationError("propertyType", "Please select a property type."));
    }
    if (this.address.length() == 0 || this.address == null) {
      errors.add(new ValidationError("address", "Please enter an address"));
    }
    if (this.month.equals("")) {
      errors.add(new ValidationError("month", "Please select a month."));     
    }    
    if (this.month.equals("")) {
      errors.add(new ValidationError("day", "Please select a day."));     
    }    
    if (this.month.equals("")) {
      errors.add(new ValidationError("year", "Please select a year."));     
    }
    return errors.isEmpty() ? null : errors;
  }
  

}
