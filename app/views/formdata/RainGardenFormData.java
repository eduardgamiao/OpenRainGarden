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
  
  /** ID of rain garden. */
  public long id = 0;
  
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
  
  /** Plants used. */
  public List<String> plants = new ArrayList<String>();
  
  /** Size of rain garden. */
  public String rainGardenSize;
  
  /** Size of water flow source. */
  public String waterFlowSourceSize;
  
  /** Description of water flow. */
  public String waterFlowDescription;
  
  /** Infiltration rate. */
  public String infiltrationRate;
  
  /** Number of rain gardens. */
  public String numberOfRainGardens;
 
  /**
   * Default constructor.
   */
  public RainGardenFormData() {
    
  }

  /**
   * Constructor.
   * @param id ID of garden.
   * @param title Title of garden.
   * @param propertyType Property type of garden.
   * @param address Address of garden.
   * @param hideAddress Hide address of garden.
   * @param description Description of garden.
   * @param month Month installed.
   * @param day Day installed.
   * @param year Year installed.
   * @param plants Plants used in garden.
   * @param rainGardenSize Size of rain garden.
   * @param waterFlowSource Size of water flow source.
   * @param waterFlowDescription Water flow description.
   * @param infiltrationRate Infiltration rate.
   * @param numberOfRainGardens Number of rain gardens.
   */
  public RainGardenFormData(long id, String title, String propertyType, String address, String hideAddress,
      String description, String month, String day, String year, List<String> plants, String rainGardenSize,
      String waterFlowSource, String waterFlowDescription, String infiltrationRate, String numberOfRainGardens) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.month = month;
    this.day = day;
    this.year = year;
    this.plants = plants;
    this.rainGardenSize = rainGardenSize;
    this.waterFlowSourceSize = waterFlowSource;
    this.waterFlowDescription = waterFlowDescription;
    this.infiltrationRate = infiltrationRate;
    this.numberOfRainGardens = numberOfRainGardens;
  } 
  
  /**
   * Constructor.
   * @param rainGarden RainGarden object. 
   */
  public RainGardenFormData(RainGarden rainGarden) {
    this.id = rainGarden.getID();
    this.title = rainGarden.getTitle();
    this.propertyType = rainGarden.getPropertyType();
    this.address = rainGarden.getAddress();
    this.hideAddress = rainGarden.getHideAddress();
    this.description = rainGarden.getDescription();
    this.month = rainGarden.getMonthInstalled();
    this.day = rainGarden.getDayInstalled();
    this.year = rainGarden.getYearInstalled();
    this.plants = rainGarden.getPlants();
    this.rainGardenSize = rainGarden.getRainGardenSize();
    this.waterFlowSourceSize = rainGarden.getWaterFlowSourceSize();
    this.waterFlowDescription = rainGarden.getWaterFlowDescription();
    this.infiltrationRate = rainGarden.getInfiltrationRate();
    this.numberOfRainGardens = rainGarden.getNumberOfRainGardens();
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
    if (!this.month.isEmpty() && (this.day.isEmpty())) {
      errors.add(new ValidationError("day", "Day is missing."));
    }    
    if (!this.month.isEmpty() && (this.year.isEmpty())) {
      errors.add(new ValidationError("year", "Year is missing."));
    }
    if (!this.day.isEmpty() && (this.month.isEmpty())) {
      errors.add(new ValidationError("month", "Missing day."));
    }    
    if (!this.day.isEmpty() && (this.year.isEmpty())) {
      errors.add(new ValidationError("year", "Year is missing."));
    }
    if (!this.year.isEmpty() && (this.day.isEmpty())) {
      errors.add(new ValidationError("day", "Day is missing."));
    }    
    if (!this.year.isEmpty() && (this.month.isEmpty())) {
      errors.add(new ValidationError("month", "Month is missing."));
    }
    
    return errors.isEmpty() ? null : errors;
  }
}
