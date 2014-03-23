package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types of properties.
 */
public class PropertyTypes {
  
  private static String [] types = {"Residential", "Business", "Public Land", "Other"};

  /**
   * Return a mapping of the types of Properties.
   * @return A mapping of Property types.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> propertyMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      propertyMap.put(type, false);
    }
    return propertyMap;
  }
  
  /**
   * Return a mapping of the types of Properties.
   * @param type The type of Property.
   * @return A mapping of Property types.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> propertyMap = PropertyTypes.getTypes();
    if (isType(type)) {
      propertyMap.put(type, true);
    }
    return propertyMap;
  }
  
  /**
   * Check if the Property type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return PropertyTypes.getTypes().containsKey(type);
  }
}
