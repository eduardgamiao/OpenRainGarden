package views.formdata;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Specifies the selectiont types for each dropdown menu in the Plant registration form.
 * @author eduardgamiao
 *
 */
public class PlantFormDropdownTypes {
  private static String [] placementTypes = {"Basin", "Slope/Berm", "Inlet", "Accent", "Other"};
  private static String [] growthTypes = {"Bush", "Tall Bush", "Sedge", "Tall Sedge", "Low Shrub", "Tall Shrub", 
                                          "Herb", "Herb/Low Shurb", "Ground Cover", "Accent", "Bunching Grass", "Vine", 
                                          "Tree", "Tall Fern", "Other"};
  private static String [] climateTypes = {"Wet & Dry Climate", "Dry Climate", "Wet Climate", "Other"};
  
  public static Map<String, Boolean> getPlacementTypes() {
    Map<String, Boolean> placementMap = new LinkedHashMap<String, Boolean>();
    for (String type : placementTypes) {
      placementMap.put(type, false);
    }
    return placementMap;
  }
  
  public static Map<String, Boolean> getPlacementTypes(String type) {
    Map<String, Boolean> placementMap = PlantFormDropdownTypes.getPlacementTypes();
    if (isPlacementType(type)) {
      placementMap.put(type, true);
    }
    return placementMap;
  }
  
  public static Boolean isPlacementType(String type) {
    return PlantFormDropdownTypes.getPlacementTypes().containsKey(type);
  }

  public static Map<String, Boolean> getGrowthTypes() {
    Map<String, Boolean> growthMap = new LinkedHashMap<String, Boolean>();
    for (String type : growthTypes) {
      growthMap.put(type, false);
    }
    return growthMap;
  }
  
  public static Map<String, Boolean> getGrowthTypes(String type) {
    Map<String, Boolean> growthMap = PlantFormDropdownTypes.getGrowthTypes();
    if (isGrowthType(type)) {
      growthMap.put(type, true);
    }
    return growthMap;
  }
  
  public static Boolean isGrowthType(String type) {
    return PlantFormDropdownTypes.getGrowthTypes().containsKey(type);
  }

  public static Map<String, Boolean> getClimateTypes() {
    Map<String, Boolean> climateMap = new LinkedHashMap<String, Boolean>();
    for (String type : climateTypes) {
      climateMap.put(type, false);
    }
    return climateMap;
  }
  
  public static Map<String, Boolean> getClimateTypes(String type) {
    Map<String, Boolean> climateMap = PlantFormDropdownTypes.getClimateTypes();
    if (isClimateType(type)) {
      climateMap.put(type, true);
    }
    return climateMap;
  }
  
  public static Boolean isClimateType(String type) {
    return PlantFormDropdownTypes.getClimateTypes().containsKey(type);
  }
}
