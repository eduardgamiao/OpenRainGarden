package models;

import java.text.Normalizer;
import java.util.List;
import views.formdata.PlantFormData;

/**
 * A database of plants.
 * @author eduardgamiao
 *
 */
public class PlantDB {
  
  /**
   * Add a plant to the database.
   * @param formData Form data from the plant form.
   * @return The plant that was added to the database.
   */
  public static Plant addPlant(PlantFormData formData) {
    boolean isNewPlant = (formData.id == -1);
    Plant plant;
    if (isNewPlant) {      
      plant = new Plant(formData.name, formData.scientificName, formData.placement, formData.growth,
                        formData.climateType);
      plant.save();
    }
    else {
      plant = Plant.find().byId(formData.id);
      plant.setName(formData.name);
      plant.setScientificName(formData.scientificName);
      plant.setPlacement(formData.placement);
      plant.setGrowth(formData.growth);
      plant.setClimateType(formData.climateType);
      //plant.save();
    }
    return plant;
  }
 
  /**
   * Retrieve a plant from the database.
   * @param id ID of the plant.
   * @return A specified plant, if it exists, from the database. Null if the plant is not in the database.
   */
  public static Plant getPlant(Long id) {
    return Plant.find().byId(id);
  }
  
  /**
   * Retrieve a plant from the database.
   * @param name Name of the plant to retrieve.
   * @return A specified plant, if it exists, from the database. Null if the plant is not in the database.
   */
  public static Plant getPlant(String name) {
    return Plant.find().where().eq("name", name).findUnique();
  }

  /**
   * Get list of all plants in database.
   * @return A list of all plants in the database.
   */
  public static List<Plant> getPlants() {
    return Plant.find().all();
  }
  
  /**
   * Check if a name is used.
   * @param name The name of the plant.
   * @return True if the name is used, false otherwise.
   */
  public static Boolean hasName(String name) {
    return (Plant.find().where().eq("name", name).findUnique() != null);
  }
  
  /**
   * Simplify a string to alphanumeric characters only.
   * @param plantName The plant name to simplify.
   * @return The alphanumeric representation of the passed-in String.
   */
  private static String simplifyName(String plantName) {
    return Normalizer.normalize(plantName, Normalizer.Form.NFD).replaceAll("[^A-Za-z0-9]", "").toLowerCase();
  }
  
  /**
   * Check if a scientific name is used.
   * @param name The scientific name of the plant.
   * @return True if the scientific name is used, false otherwise.
   */
  public static Boolean hasScientificName(String name) {
    return (Plant.find().where().eq("scientificName", name).findUnique() != null);
  }
  
  /**
   * Delete a plant from the database.
   * @param id The ID of the plant to delete.
   */
  public static void deletePlant(Long id) {
    Plant plant = Plant.find().byId(id);
    plant.delete();
  }
  
  /**
   * Delete a plant from the database.
   * @param name The name of the plant to delete.
   */
  public static void deletePlant(String name) {
    Plant plant = Plant.find().where().eq("name", name).findUnique();
    plant.delete();
  }
}
