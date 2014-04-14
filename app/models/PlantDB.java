package models;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import views.formdata.PlantFormData;

/**
 * A database of plants.
 * @author eduardgamiao
 *
 */
public class PlantDB {

  private static Map<Long, Plant> plants = new LinkedHashMap<Long, Plant>();
  private static long currentID = 1;
  private static List<String> plantScientificNames = new ArrayList<String>();
  
  /**
   * Add a plant to the database.
   * @param formData Form data from the plant form.
   * @return The plant that was added to the database.
   */
  public static Plant addPlant(PlantFormData formData) {    
    Plant plant;
    if (formData.id == 0) {      
      plant = new Plant(currentID, formData.name, formData.scientificName, formData.placement, formData.growth,
                        formData.climateType);
      currentID++;
      plants.put(plant.getID(), plant);
      plantScientificNames.add(plant.getScientificName());
    }
    else {
      plant = new Plant(formData.id, formData.name, formData.scientificName, formData.placement, formData.growth,
          formData.climateType);
      plants.put(plant.getID(), plant);
      plantScientificNames.add(plant.getScientificName());
    }
    return plant;
  }
 
  /**
   * Retrieve a plant from the database.
   * @param name Name of the plant to retrieve.
   * @return A specified plant, if it exists, from the database. Null if the plant is not in the database.
   */
  public static Plant getPlant(String name) {
    for (Plant plant : plants.values()) {
      if (plant.getName().equals(name)) {
        return plant;
      }
    }
    return null;
  }

  /**
   * Get list of all plants in database.
   * @return A list of all plants in the database.
   */
  public static List<Plant> getPlants() {
    return new ArrayList<Plant>(plants.values());
  }
  
  /**
   * Check if a name is used.
   * @param name The name of the plant.
   * @return True if the name is used, false otherwise.
   */
  public static Boolean hasName(String name) {
    for (Plant plant : plants.values()) {
      if (simplifyName(plant.getName()).equals(simplifyName(name))) {
        return true;
      }
    }
    return false;
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
    for (Plant plant : plants.values()) {
      if (simplifyName(plant.getScientificName()).equals(simplifyName(name))) {
        return true;
      }
    }
    return false;
  }
}
