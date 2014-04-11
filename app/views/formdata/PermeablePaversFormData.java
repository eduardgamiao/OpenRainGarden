package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.PermeablePavers;

/**
 * Handles form data form permeable paver registration.
 * @author eduardgamiao
 *
 */
public class PermeablePaversFormData {

  /** ID of permeable paver. */
  public long id = 0;
  
  /** Title of permeable paver. */
  public String title;
  
  /** Property type of permeable paver. */
  public String propertyType;
  
  /** Address of permeable paver. */
  public String address;
  
  /** Hide address from public. */
  public String hideAddress;
  
  /** Description of permeable paver. */
  public String description;
  
  /** Month of installation. */
  public String month;
  
  /** Day of installation. */
  public String day;
  
  /** Year of installation. */
  public String year;
  
  /** Material of permeable paver. */
  public String material;
  
  /** Previus material of permeable paver. */
  public String previousMaterial;
  
  /** Size of permeable paver. */
  public String size;
  
  /** Installer of permeable paver. */
  public String installer;
  
  /**
   * Constructor.
   */
  public PermeablePaversFormData() {
    
  }
  
  /**
   * @param id ID of permeable paver.
   * @param title Title of permeable paver.
   * @param propertyType Type of property of paver's address.
   * @param address Address of permeable paver.
   * @param hideAddress Specify whether to hide address from public.
   * @param description Description of permeable paver.
   * @param month Month installed.
   * @param day Day installed.
   * @param year Year installed.
   * @param material Material of previous paver.
   * @param previousMaterial Material of material before paver installation.
   * @param size Size of paver.
   * @param installer Installer if paver.
   */
  public PermeablePaversFormData(long id, String title, String propertyType, String address, String hideAddress,
      String description, String month, String day, String year, String material, String previousMaterial, String size,
      String installer) {
    this.id = id;
    this.title = title;
    this.propertyType = propertyType;
    this.address = address;
    this.hideAddress = hideAddress;
    this.description = description;
    this.month = month;
    this.day = day;
    this.year = year;
    this.material = material;
    this.previousMaterial = previousMaterial;
    this.size = size;
    this.installer = installer;
  }



  /**
   * Constructor.
   * @param permeablePaver A permeable paver.
   */
  public PermeablePaversFormData(PermeablePavers permeablePaver) {
    this.id = permeablePaver.getID();
    this.title = permeablePaver.getTitle();
    this.propertyType = permeablePaver.getPropertyType();
    this.address = permeablePaver.getAddress();
    this.hideAddress = permeablePaver.getHideAddress();
    this.description = permeablePaver.getDescription();
    this.month = permeablePaver.getMonthInstalled();
    this.day = permeablePaver.getDayInstalled();
    this.year = permeablePaver.getYearInstalled();
    this.material = permeablePaver.getMaterial();
    this.previousMaterial = permeablePaver.getPreviousMaterial();
    this.size = permeablePaver.getSize();
    this.installer = permeablePaver.getInstaller();
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
