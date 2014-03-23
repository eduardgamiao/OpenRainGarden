package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types of properties.
 */
public class WaterUsageTypes {
  
  private static String [] types = {"Gardening", "Lawn Watering", "Carwashing", "Other"};

  /**
   * Return a mapping of water usage types.
   * @return A mapping of water usage types.
   */
  public static Map<String, Boolean> getWaterUsageTypes() {
    Map<String, Boolean> waterUsageMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      waterUsageMap.put(type, false);
    }
    return waterUsageMap;
  }
  
  /**
   * Return a mapping of water usage types.
   * @param type The type of water usage.
   * @return A mapping of water usage types.
   */
  public static Map<String, Boolean> getWaterUsageTypes(String type) {
    Map<String, Boolean> waterUsageMap = WaterUsageTypes.getWaterUsageTypes();
    if (isType(type)) {
      waterUsageMap.put(type, true);
    }
    return waterUsageMap;
  }
  
  /**
   * Check if the water usage type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return WaterUsageTypes.getWaterUsageTypes().containsKey(type);
  }
}
