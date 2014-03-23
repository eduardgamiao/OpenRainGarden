package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements types of rain barrel materials.
 */
public class InstallationTypes {
  
  private static String [] types = {"Self-Installed", "Hired Company", "Other"};

  /**
   * Return a mapping of the types of rain barrel installation types.
   * @return A mapping of rain barrel installations.
   */
  public static Map<String, Boolean> getInstallationTypes() {
    Map<String, Boolean> installationTypeMap = new LinkedHashMap<String, Boolean>();
    for (String type : types) {
      installationTypeMap.put(type, false);
    }
    return installationTypeMap;
  }
  
  /**
   * Return a mapping of the types of rain barrel installations.
   * @param type The type of rain barrel installation.
   * @return A mapping of rain barrel installation types.
   */
  public static Map<String, Boolean> getInstallationTypes(String type) {
    Map<String, Boolean> installationTypeMap = InstallationTypes.getInstallationTypes();
    if (isType(type)) {
      installationTypeMap.put(type, true);
    }
    return installationTypeMap;
  }
  
  /**
   * Check if the installation type is valid.
   * @param type The type to check.
   * @return True if the type is valid, false otherwise. 
   */
  public static Boolean isType(String type) {
    return InstallationTypes.getInstallationTypes().containsKey(type);
  }
}
