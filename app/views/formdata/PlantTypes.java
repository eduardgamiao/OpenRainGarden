package views.formdata;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements plant types.
 * @author eduardgamiao
 *
 */
public class PlantTypes {
  
  private static String [] types = {"'Ahu'awa'", "'Akia", "'Akulikuli", "Carex", "'Ilie'e", "Koki'o ke'oke'o",
    "Ko'oko'olau", "Kupukupu"};

  /**
   * Return list of choices for plants.
   * @return List of strings that represent the choices.
   */
  public static Map<String, Boolean> getPlantMap() {
    Map<String, Boolean> plantMap = new LinkedHashMap<String, Boolean>();
    for (String current : types) {
      plantMap.put(current, false);
    }
    return plantMap;
  }
  
  /**
   * Return list of choices for plants.
   * @param plants List of plants.
   * @return List of strings that represent the choices.
   */
  public static Map<String, Boolean> getPlantMap(List<String> plants) {
    Map<String, Boolean> plantMap = getPlantMap();
    for (String current : plantMap.keySet()) {
      if (plants.contains(current)) {
        plantMap.put(current, true);
      }
    }
    return plantMap;
  }

}
