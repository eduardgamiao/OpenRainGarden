package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types of rain barrels.
 */
public class RainBarrelTypes {
  
  private static String [] types = {"Old Drum", "Garbage Can", "Commercially Manafactured", "Wine Barrel", "Other"};

  /**
   * Return a mapping of the types of rain barrels.
   * @return A mapping of rain barrel types.
   */
  public static Map<String, Boolean> getRainBarrelTypes() {
    Map<String, Boolean> rainBarrelTypeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      rainBarrelTypeMap.put(type, false);
    }
    return rainBarrelTypeMap;
  }
  
  /**
   * Return a mapping of the types of rain barrels.
   * @param type The type of rain barrel.
   * @return A mapping of rain barrel types.
   */
  public static Map<String, Boolean> getRainBarrelTypes(String type) {
    Map<String, Boolean> rainBarrelTypeMap = RainBarrelTypes.getRainBarrelTypes();
    if (isType(type)) {
      rainBarrelTypeMap.put(type, true);
    }
    return rainBarrelTypeMap;
  }
  
  /**
   * Check if the Property type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return RainBarrelTypes.getRainBarrelTypes().containsKey(type);
  }
}
