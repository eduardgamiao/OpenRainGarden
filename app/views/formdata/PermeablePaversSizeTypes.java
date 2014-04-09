package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types for rain garden sizes.
 * @author eduardgamiao
 *
 */
public class PermeablePaversSizeTypes {
  private static String [] types = {"<200 Square Feet", "200 Square Feet", "300 Square Feet", "400 Square Feet", 
    "500+ Square Feet"};
  
  /**
   * Return a mapping of the types of pavement sizes.
   * @return A mapping of pavement sizes.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> pavementSizeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      pavementSizeMap.put(type, false);
    }
    return pavementSizeMap;
  }
  
  /**
   * Return a mapping of the types of pavement sizes.
   * @param type The type of pavement sizes.
   * @return A mapping of pavement sizes.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> pavementSizeMap = PermeablePaversSizeTypes.getTypes();
    if (isType(type)) {
      pavementSizeMap.put(type, true);
    }
    return pavementSizeMap;
  }
  
  /**
   * Check if the pavement size type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return PermeablePaversSizeTypes.getTypes().containsKey(type);
  }
}
