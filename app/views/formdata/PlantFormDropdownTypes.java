package views.formdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
  
  /**
   * Get types of plant placements.
   * @return A mapping of plant placements.
   */
  public static Map<String, Boolean> getPlacementTypes() {
    Map<String, Boolean> placementMap = new LinkedHashMap<String, Boolean>();
    for (String type : placementTypes) {
      placementMap.put(type, false);
    }
    return placementMap;
  }
  
  /**
   * Get types of plant placements.
   * @param type A type of plant placement.
   * @return A mapping of plant placements.
   */
  public static Map<String, Boolean> getPlacementTypes(String type) {
    Map<String, Boolean> placementMap = PlantFormDropdownTypes.getPlacementTypes();
    if (isPlacementType(type)) {
      placementMap.put(type, true);
    }
    return placementMap;
  }
  
  /**
   * Check if a type is a plant placement type.
   * @param type The type to check. 
   * @return True if the type is a placement type. False otherwise. 
   */
  public static Boolean isPlacementType(String type) {
    return PlantFormDropdownTypes.getPlacementTypes().containsKey(type);
  }

  /**
   * Get types of plant growth.
   * @return A mapping of plant growth types.
   */
  public static Map<String, Boolean> getGrowthTypes() {
    Map<String, Boolean> growthMap = new LinkedHashMap<String, Boolean>();
    for (String type : growthTypes) {
      growthMap.put(type, false);
    }
    return growthMap;
  }
  
  /**
   * Get types of plant growth.
   * @param type A plant growth type.
   * @return A mapping of plant growth types.
   */
  public static Map<String, Boolean> getGrowthTypes(String type) {
    Map<String, Boolean> growthMap = PlantFormDropdownTypes.getGrowthTypes();
    if (isGrowthType(type)) {
      growthMap.put(type, true);
    }
    return growthMap;
  }
  
  /**
   * Check if a plant growth type is valid.
   * @param type A plant growth type.
   * @return True if the type is a plant growth type. False otherwise.
   */
  public static Boolean isGrowthType(String type) {
    return PlantFormDropdownTypes.getGrowthTypes().containsKey(type);
  }

  /**
   * Get a mapping of plant climate types.
   * @return A mapping of plant climate types.
   */
  public static Map<String, Boolean> getClimateTypes() {
    Map<String, Boolean> climateMap = new LinkedHashMap<String, Boolean>();
    for (String type : climateTypes) {
      climateMap.put(type, false);
    }
    return climateMap;
  }
  
  /**
   * Get a mapping of plant climate types.
   * @param type A plant climate type.
   * @return A mapping of plant climate types.
   */
  public static Map<String, Boolean> getClimateTypes(String type) {
    Map<String, Boolean> climateMap = PlantFormDropdownTypes.getClimateTypes();
    if (isClimateType(type)) {
      climateMap.put(type, true);
    }
    return climateMap;
  }
  
  public static List<String> getClimateTypesAsList() {
    List<String> climateTypes = new ArrayList<String>();
    for (String current : PlantFormDropdownTypes.getClimateTypes().keySet()) {
      climateTypes.add(current);
    }
    return climateTypes;
  }
  
  /**
   * Check if a type is a plant climate type.
   * @param type The type to check.
   * @return True if the type is a plant climate type. False otherwise. 
   */
  public static Boolean isClimateType(String type) {
    return PlantFormDropdownTypes.getClimateTypes().containsKey(type);
  }
}
