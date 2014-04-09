package models;

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

  private static Map<String, Plant> plants = new LinkedHashMap<String, Plant>();
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
      plants.put(plant.getName(), plant);
      plantScientificNames.add(plant.getScientificName());
    }
    else {
      plant = new Plant(formData.id, formData.name, formData.scientificName, formData.placement, formData.growth,
          formData.climateType);
      plants.put(plant.getName(), plant);
      plantScientificNames.add(plant.getScientificName());
    }
    return plant;
  }
  
  /**
   * Add a plant to the database.
   * @param plant Plant to add.
   * @return The plant that was added to the database.
   */
  public static Plant addPlant(Plant plant) {    
    plants.put(plant.getName(), plant);
    plant.setID(currentID);
    currentID++;
    return plant;
  }
  
  /**
   * Retrieve a plant from the database.
   * @param name Name of the plant to retrieve.
   * @return A specified plant, if it exists, from the database. Null if the plant is not in the database.
   */
  public static Plant getPlant(String name) {
    return plants.get(name);
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
    return plants.containsKey(name);
  }
  
  /**
   * Check if a scientific name is used.
   * @param name The scientific name of the plant.
   * @return True if the name is used, false otherwise.
   */
  public static Boolean hasScientificName(String name) {
    return plantScientificNames.contains(name);
  }
}
