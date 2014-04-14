package views.formdata;

import java.util.ArrayList;
import java.util.List;
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
  public long id = 0;
  
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
  
  /** Specify if the plant is being edited or not. */
  public boolean isEditing = false;
  
  /**
   * Constructor.
   */
  public PlantFormData() {
    
  }

  /**
   * Constructor.
   * @param id ID of plant.
   * @param name Name of plant.
   * @param scientificName Scientific name of plant.
   * @param placement Placement in rain garden.
   * @param growth Growth type.
   * @param climateType Plant climate type. 
   */
  public PlantFormData(long id, String name, String scientificName, String placement, String growth, 
      String climateType) {
    this.id = id;
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
   
   if (this.name.isEmpty() || this.name == null) {
     errors.add(new ValidationError("name", "The plant's name is required."));
   }
   if ((this.id == 0) && PlantDB.hasName(this.name)) {
     errors.add(new ValidationError("name", "The plant name \"" + this.name + "\" is already in use. "
                                    + "Please select another name."));     
   }
   if (this.scientificName.isEmpty() || this.scientificName == null) {
     errors.add(new ValidationError("scientificName", "The plant's scientific name is required."));
   }
   
   return errors.isEmpty() ? null : errors;
 }
}
