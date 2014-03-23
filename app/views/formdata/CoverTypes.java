package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types of rain barrel materials.
 */
public class CoverTypes {
  
  private static String [] types = {"Covered", "Screened", "Open", "Other"};

  /**
   * Return a mapping of the types of rain barrel covers.
   * @return A mapping of rain barrel covers.
   */
  public static Map<String, Boolean> getCoverTypes() {
    Map<String, Boolean> coverTypeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      coverTypeMap.put(type, false);
    }
    return coverTypeMap;
  }
  
  /**
   * Return a mapping of the types of rain barrel covers.
   * @param type The type of rain barrel cover.
   * @return A mapping of rain barrel cover types.
   */
  public static Map<String, Boolean> getCoverTypes(String type) {
    Map<String, Boolean> coverTypeMap = CoverTypes.getCoverTypes();
    if (isType(type)) {
      coverTypeMap.put(type, true);
    }
    return coverTypeMap;
  }
  
  /**
   * Check if the cover type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return CoverTypes.getCoverTypes().containsKey(type);
  }
}
