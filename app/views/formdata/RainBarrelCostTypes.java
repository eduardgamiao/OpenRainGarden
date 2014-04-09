package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types for rain barrel costs.
 * @author eduardgamiao
 *
 */
public class RainBarrelCostTypes {
  private static String [] types = {"Free", "Less than $50", "$100", "$150", "$200", "More than $200"};
  
  /**
   * Return a mapping of the types of cost.
   * @return A mapping of cost.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> costMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      costMap.put(type, false);
    }
    return costMap;
  }
  
  /**
   * Return a mapping of the types of cost.
   * @param type The type of cost.
   * @return A mapping of cost.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> costMap = RainBarrelCostTypes.getTypes();
    if (isType(type)) {
      costMap.put(type, true);
    }
    return costMap;
  }
  
  /**
   * Check if the infiltration rate type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return RainBarrelCostTypes.getTypes().containsKey(type);
  }
}
