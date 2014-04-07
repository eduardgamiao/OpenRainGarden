package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types for rain garden sizes.
 * @author eduardgamiao
 *
 */
public class InfiltrationRateTypes {
  private static String [] types = {"Less than 0.25 inches/hour", "0.25 inches/hour", "0.50 inches/hour",
    "0.75 inches/hour", "1.00 inches/hour", "1.00 inches/hour"};
  
  /**
   * Return a mapping of the types of infiltration rates.
   * @return A mapping of infiltration rates.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> infiltrationRateMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      infiltrationRateMap.put(type, false);
    }
    return infiltrationRateMap;
  }
  
  /**
   * Return a mapping of the types of infiltration rates.
   * @param type The type of infiltration rates.
   * @return A mapping of infiltration rates.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> infiltrationRateMap = InfiltrationRateTypes.getTypes();
    if (isType(type)) {
      infiltrationRateMap.put(type, true);
    }
    return infiltrationRateMap;
  }
  
  /**
   * Check if the infiltration rate type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return InfiltrationRateTypes.getTypes().containsKey(type);
  }
}
