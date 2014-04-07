package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types for rain garden sizes.
 * @author eduardgamiao
 *
 */
public class WaterSourceSizeTypes {
  private static String [] types = {"Less than 1000 Square Feet", "1000 Square Feet", "1500 Square Feet",
    "2000 Square Feet", "2500 Squere Feet", "2500+ Square Feet"};
  
  /**
   * Return a mapping of the types of roof garden sizes.
   * @return A mapping of roof garden sizes.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> sourceSizeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      sourceSizeMap.put(type, false);
    }
    return sourceSizeMap;
  }
  
  /**
   * Return a mapping of the types of roof garden sizes.
   * @param type The type of roof garden sizes.
   * @return A mapping of roof garden sizes.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> sourceSizeMap = WaterSourceSizeTypes.getTypes();
    if (isType(type)) {
      sourceSizeMap.put(type, true);
    }
    return sourceSizeMap;
  }
  
  /**
   * Check if the roof size type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return WaterSourceSizeTypes.getTypes().containsKey(type);
  }
}
