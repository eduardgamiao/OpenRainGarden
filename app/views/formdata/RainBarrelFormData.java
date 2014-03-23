package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.RainBarrel;

/**
 * Handles form data form rain barrel registration.
 * @author eduardgamiao
 *
 */
public class RainBarrelFormData {

  /** Id of rain barrel. */
  public long id = 0;
  
  /** Title of rain barrel. */
  public String title;
  
  /** Property type of rain barrel. */
  public String propertyType;
  
  /** Address of rain barrel. */
  public String address;
  
  /** Specify if address is hidden from public. */
  public String hideAddress;
  
  /** Description of rain barrel. */
  public String description;
  
  /** Month rain barrel was installed. */
  public String month;
  
  /** Day rain barrel was installed. */
  public String day;
  
  /** Year rain barrel was installed. */
  public String year;
  
  /** Type of rain barrel. */
  public String rainBarrelType;
  
  /** Capacity of rain barrel. */
  public String capacity;
  
  /** Color of rain barrel. */
  public String color;
  
  /** Material of rain barrel. */
  public String material;
  
  /** Cost of rain barrel. */
  public String estimatedCost;
  
  /** Water use of rain barrel collection. */
  public String waterUse;
  
  /** Overflow frequency. */
  public String overflowFrequency;
  
  /** Cover of rain barrel. */
  public String cover;
  
  /** Place obtained from. */
  public String obtainedFrom;
  
  /** Installation method of rain barrel. */
  public String installationType;
  
  /** Number of rain barrels on property. */
  public String numberOfRainBarrels; 
  
  /**
   * Constructor.
   */
  public RainBarrelFormData() {
    
  }

  /**
   * @param id ID of rain barrel.
   * @param title Title of rain barrel.
   * @param propertyType Property type of rain barrel address.
   * @param address Address of rain barrel.
   * @param hideAddress Hide address from public.
   * @param description Description of rain barrel.
   * @param month Month installed.
   * @param day Day installed.
   * @param year Year installed.
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
  public RainBarrelFormData(long id, String title, String propertyType, String address, String hideAddress,
      String description, String month, String day, String year,  String rainBarrelType, String capacity, 
      String color, String material, String estimatedCost, String waterUse, String overflowFrequency, String cover, 
      String obtainedFrom, String installationType, String numberOfRainBarrel) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.month = month;
    this.day = day;
    this.year = year;
    this.rainBarrelType = rainBarrelType;
    this.capacity = capacity;
    this.color = color;
    this.material = material;
    this.estimatedCost = estimatedCost;
    this.waterUse = waterUse;
    this.overflowFrequency = overflowFrequency;
    this.cover = cover;
    this.obtainedFrom = obtainedFrom;
    this.installationType = installationType;
    this.numberOfRainBarrels = numberOfRainBarrel;
  }
  
  /**
   * Constructor.
   * @param rainBarrel A rain barrel.
   */
  public RainBarrelFormData(RainBarrel rainBarrel) {
    this.id = rainBarrel.getID();
    this.title = rainBarrel.getTitle();
    this.propertyType = rainBarrel.getPropertyType();
    this.address = rainBarrel.getAddress();
    this.hideAddress = rainBarrel.getHideAddress();
    this.description = rainBarrel.getDescription();
    this.month = rainBarrel.getMonthInstalled();
    this.day = rainBarrel.getDayInstalled();
    this.year = rainBarrel.getYearInstalled();
    this.rainBarrelType = rainBarrel.getRainBarrelType();
    this.capacity = rainBarrel.getCapacity();
    this.color = rainBarrel.getColor();
    this.material = rainBarrel.getMaterial();
    this.estimatedCost = rainBarrel.getEstimatedCost();
    this.waterUse = rainBarrel.getWaterUse();
    this.overflowFrequency = rainBarrel.getOverflowFrequency();
    this.cover = rainBarrel.getCover();
    this.obtainedFrom = rainBarrel.getObtainedFrom();
    this.installationType = rainBarrel.getInstallationType();
    this.numberOfRainBarrels = rainBarrel.getNumberOfRainGardens();
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
    if (this.day.equals("")) {
      errors.add(new ValidationError("day", "Please select a day."));     
    }    
    if (this.year.equals("")) {
      errors.add(new ValidationError("year", "Please select a year."));     
    }
    if (!(this.capacity.isEmpty()) && !(isValidDouble(this.capacity))) {
      errors.add(new ValidationError("capacity", 
          "Please enter a positive number for the rain barrel's capacity."));       
    }
    if (!(this.estimatedCost.isEmpty()) && !(isValidDouble(this.estimatedCost))) {
      errors.add(new ValidationError("estimatedCost", 
          "Please enter a positive number for the rain barrel's cost."));       
    }
    
    return errors.isEmpty() ? null : errors;
  }
  
  /**
   * Checks if an input is all numerical characters.
   * @param input The input to check.
   * @return True if the input is all numerical characters, false otherwise. 
   */
  private static boolean isValidDouble(String input) {
      return input.matches("[0-9]{1,13}(\\.[0-9]*)?");
  }
  
}
