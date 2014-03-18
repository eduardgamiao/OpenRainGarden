package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A database of plants.
 * @author eduardgamiao
 *
 */
public class PlantDB {

  private static Map<String, Plant> plants = new LinkedHashMap<String, Plant>();
  
  /**
   * Add a plant to the database.
   * @param plant Plant to add.
   * @return The plant that was added to the database.
   */
  public static Plant addPlant(Plant plant) {
    plants.put(plant.getName(), plant);
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
}
