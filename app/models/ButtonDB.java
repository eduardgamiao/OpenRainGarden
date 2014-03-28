package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A database of index page button.
 * @author vinson gao
 *
 */
public class ButtonDB {

  private static Map<String, Button> block = new LinkedHashMap<String, Button>();
  
  /**
   * Add a plant to the database.
   * @param plant Plant to add.
   * @return The plant that was added to the database.
   */
  public static Button addButton(Button b) {
	block.put(b.getTitle()+b.getNumber(), b);
    return b;
  }
  
  /**
   * Retrieve a plant from the database.
   * @param name Name of the plant to retrieve.
   * @return A specified plant, if it exists, from the database. Null if the plant is not in the database.
   */
  public static Button getButton(String name) {
    return block.get(name);
  }

  /**
   * Get list of all plants in database.
   * @return A list of all plants in the database.
   */
  public static List<Button> getButtons() {
    return new ArrayList<Button>(block.values());
  }
}