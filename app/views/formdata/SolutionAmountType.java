package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;;

/**
 * Implement selection type for amount of rainwater runoff solutions installed.
 * @author eduardgamiao
 *
 */
public class SolutionAmountType {
  private static String [] types = {"1", "2", "3", "4+"};

  /**
   * Return a mapping of the types of solution amounts.
   * @return A mapping of solution amount types.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> propertyMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      propertyMap.put(type, false);
    }
    return propertyMap;
  }
  
  /**
   * Return a mapping of the types of solution amounts.
   * @param type The type of solution amounts.
   * @return A mapping of solution amounts types.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> propertyMap = SolutionAmountType.getTypes();
    if (isType(type)) {
      propertyMap.put(type, true);
    }
    return propertyMap;
  }
  
  /**
   * Check if the solution amount type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return PropertyTypes.getTypes().containsKey(type);
  }
  
}
