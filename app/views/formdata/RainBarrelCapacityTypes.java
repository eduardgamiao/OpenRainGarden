package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types for rain garden sizes.
 * @author eduardgamiao
 *
 */
public class RainBarrelCapacityTypes {
  private static String [] types = {"Less than 30 Gallons", "30 Gallons", "40 Gallons", "50 Gallons", "60 Gallons",
    "70 Gallons", "More than 70 Gallons"};
  
  /**
   * Return a mapping of the types of capacities..
   * @return A mapping of capacities..
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> capacityMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      capacityMap.put(type, false);
    }
    return capacityMap;
  }
  
  /**
   * Return a mapping of the types of capacities..
   * @param type The type of capacities..
   * @return A mapping of capacities..
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> capacityMap = RainBarrelCapacityTypes.getTypes();
    if (isType(type)) {
      capacityMap.put(type, true);
    }
    return capacityMap;
  }
  
  /**
   * Check if the infiltration rate type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return RainBarrelCapacityTypes.getTypes().containsKey(type);
  }
}
