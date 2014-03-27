package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types of permeable pavers materials.
 */
public class PaverMaterialTypes {
  
  private static String [] types = {"Asphalt", "Concrete", "Dirt", "Grass", "Other"};

  /**
   * Return a mapping of the types of rain barrel materials.
   * @return A mapping of rain barrel materials.
   */
  public static Map<String, Boolean> getMaterialTypes() {
    Map<String, Boolean> materialTypeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      materialTypeMap.put(type, false);
    }
    return materialTypeMap;
  }
  
  /**
   * Return a mapping of the types of rain barrel materials.
   * @param type The type of rain barrel material.
   * @return A mapping of rain barrel material types.
   */
  public static Map<String, Boolean> getMaterialTypes(String type) {
    Map<String, Boolean> materialTypeMap = MaterialTypes.getMaterialTypes();
    if (isType(type)) {
      materialTypeMap.put(type, true);
    }
    return materialTypeMap;
  }
  
  /**
   * Check if the material type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return MaterialTypes.getMaterialTypes().containsKey(type);
  }
}
