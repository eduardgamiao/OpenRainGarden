package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.Logger;
import play.data.validation.ValidationError;
import models.Plant;
import models.PlantDB;

/**
 * Handles form data for adding/editing plants.
 * @author eduardgamiao
 *
 */
public class PlantFormData {  
  /** ID of plant. */
  public long id = -1;
  
  /** Name of plant. */
  public String name;
  
  /** Scientific name of plant. */
  public String scientificName;
  
  /** Optimal placement of plant. */
  public String placement;
  
  /** Growth type of plant. */
  public String growth;
  
  /** Climate type of plant. */
  public String climateType;
  
  /** Specify if the form data has a picture. */
  public boolean hasPicture = false;
  
  /**
   * Constructor.
   */
  public PlantFormData() {
    
  }

  /**
   * Constructor.
   * @param name Name of plant.
   * @param scientificName Scientific name of plant.
   * @param placement Placement in rain garden.
   * @param growth Growth type.
   * @param climateType Plant climate type. 
   */
  public PlantFormData(String name, String scientificName, String placement, String growth, 
      String climateType) {
    this.name = name;
    this.scientificName = scientificName;
    this.placement = placement;
    this.growth = growth;
    this.climateType = climateType;
  }
  
 /**
  * Constructor. 
  * @param plant The plant.
  */
 public PlantFormData(Plant plant) {
   this.id = plant.getID();
   this.name = plant.getName();
   this.scientificName = plant.getScientificName();
   this.placement = plant.getPlacement();
   this.growth = plant.getGrowth();
   this.climateType = plant.getClimateType();
 }
 
 /**
  * Validation for plant form.
  * @return If input into form is not valid, a list of errors, else an empty list.
  */
 public List<ValidationError> validate() {
   ArrayList<ValidationError> errors = new ArrayList<>();
   
   
   if (this.name == null || this.name.isEmpty()) {
     errors.add(new ValidationError("name", "The plant's name is required."));
   }
   if ((this.id == -1) && PlantDB.hasName(this.name)) {
     errors.add(new ValidationError("name", "The plant name \"" + this.name + "\" is already in use. "
                                    + "Please select a unique name."));     
   }
   if ((this.id != -1) && PlantDB.hasName(this.name) && !(this.name.equals(PlantDB.getPlant(id).getName()))) {
     errors.add(new ValidationError("name", "The plant name \"" + this.name + "\" is already in use. "
         + "Please select a unique name."));    
   }
   if (this.scientificName == null || this.scientificName.isEmpty()) {
     errors.add(new ValidationError("scientificName", "The plant's scientific name is required."));
   }
   if ((this.id == -1) && PlantDB.hasScientificName(this.scientificName)) {
     errors.add(new ValidationError("scientificName", "The scientific name \"" + this.scientificName 
                                    + "\" is already in use. Please select a unique scientific name."));
   }
   if ((this.id != -1) && PlantDB.hasScientificName(this.scientificName) 
        && !(this.scientificName.equals(PlantDB.getPlant(id).getScientificName()))) {
     errors.add(new ValidationError("scientificName", "The scientific name \"" + this.scientificName 
         + "\" is already in use. Please select a unique scientific name."));
   }
   if (this.placement == null || this.placement.isEmpty()) {
     errors.add(new ValidationError("placement", "The plant's placement is required."));     
   }
   if (this.growth == null || this.growth.isEmpty()) {
     errors.add(new ValidationError("growth", "The plant's growth type is required."));     
   }
   if (this.climateType == null || this.climateType.isEmpty()) {
     errors.add(new ValidationError("climateType", "The plant's climate type is required."));     
   }
   
   return errors.isEmpty() ? null : errors;
 }
}
