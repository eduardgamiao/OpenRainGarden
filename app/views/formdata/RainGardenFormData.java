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
  
  /** Title of rain garden. */
  public String title;
  
  /** Property type. */
  public String propertyType;
 
  /**
   * Default constructor.
   */
  public RainGardenFormData() {
    
  }

  /**
   * @param name Owner's name. 
   */
  public RainGardenFormData(String name) {
    this.title = name;
  }
  
  /**
   * Constructor.
   * @param rainGarden RainGarden object. 
   */
  public RainGardenFormData(RainGarden rainGarden) {
    this.title = rainGarden.getFirstName();
  }
  
  /**
   * Validation for rain garden registration form.
   * @return If input into form is not valid, a list of errors, else an empty list.
   */
  public List<ValidationError> validate() {
    ArrayList<ValidationError> errors = new ArrayList<>();
    
    if (this.title.length() == 0 || this.title == null) {
      errors.add(new ValidationError("firstName", "First name is required."));
    }
    
    return errors.isEmpty() ? null : errors;
  }
  

}
