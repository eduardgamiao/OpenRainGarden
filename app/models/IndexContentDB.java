package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A database of plants.
 * @author vinson gao
 *
 */
public class IndexContentDB {

  private static Map<String, IndexContent> block = new LinkedHashMap<String, IndexContent>();
  
  /**
   * Add a plant to the database.
   * @param plant Plant to add.
   * @return The plant that was added to the database.
   */
  public static IndexContent addPlant(IndexContent plant) {
	  block.put(plant.getSerial(), plant);
    return plant;
  }
  
  /**
   * Retrieve a plant from the database.
   * @param name Name of the plant to retrieve.
   * @return A specified plant, if it exists, from the database. Null if the plant is not in the database.
   */
  public static IndexContent getBlock(String name) {
    return block.get(name);
  }

  /**
   * Get list of all plants in database.
   * @return A list of all plants in the database.
   */
  public static List<IndexContent> getBlocks() {
    return new ArrayList<IndexContent>(block.values());
  }
}