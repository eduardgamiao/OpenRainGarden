package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types for rain garden sizes.
 * @author eduardgamiao
 *
 */
public class RainGardenSizeTypes {
  private static String [] types = {"Less than 100 Square Feet", "100 Square Feet", "200 Square Feet", 
    "300 Square Feet", "400 Square Feet", "500 Square Feet", "500+ Square Feet"};
  
  /**
   * Return a mapping of the types of rain garden sizes.
   * @return A mapping of rain garden sizes.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> gardenSizeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      gardenSizeMap.put(type, false);
    }
    return gardenSizeMap;
  }
  
  /**
   * Return a mapping of the types of rain garden sizes.
   * @param type The type of rain garden sizes.
   * @return A mapping of rain garden sizes.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> gardenSizeMap = RainGardenSizeTypes.getTypes();
    if (isType(type)) {
      gardenSizeMap.put(type, true);
    }
    return gardenSizeMap;
  }
  
  /**
   * Check if the garden size type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return RainGardenSizeTypes.getTypes().containsKey(type);
  }
}
