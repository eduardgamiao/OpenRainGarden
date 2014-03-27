package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import views.formdata.RainBarrelFormData;

/**
 * Stores a list of contacts in a data structure.
 */
public class RainBarrelDB {

  private static HashMap<Long, RainBarrel> barrels = new HashMap<Long, RainBarrel>();

  /**
   * Add rain barrel.
   * 
   * @param formData Rain barrel form data.
   * @param userInfo User tied to rain barrel.
   * @return The rain barrel that has been added.
   */
  public static RainBarrel addRainBarrel(RainBarrelFormData formData, UserInfo userInfo) {
    RainBarrel barrel;
    if (formData.id == 0) {
      long id = barrels.size() + 1;
      barrel = new RainBarrel(id, formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.rainBarrelType, formData.capacity, formData.color, formData.material, 
                              formData.estimatedCost, formData.waterUse, formData.overflowFrequency, formData.cover,
                              formData.obtainedFrom, formData.installationType, formData.numberOfRainBarrels);
      barrel.setOwner(userInfo);
      userInfo.getBarrels().add(barrel);
      barrels.put(id, barrel);
      return barrel;
    }
    else {
      barrel = new RainBarrel(formData.id, formData.title, formData.propertyType, formData.address, 
                              formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.rainBarrelType, formData.capacity, formData.color, formData.material, 
                              formData.estimatedCost, formData.waterUse, formData.overflowFrequency, formData.cover,
                              formData.obtainedFrom, formData.installationType, formData.numberOfRainBarrels);
      barrel.setOwner(userInfo);
      userInfo.getBarrels().add(barrel);
      barrels.put(barrel.getID(), barrel);
      return barrel;
    }
  }

  /**
   * Return list of rain barrels.
   * 
   * @return List of rain barrels.
   */
  public static List<RainBarrel> getRainBarrels() {
    return new ArrayList<RainBarrel>(barrels.values());
  }

  /**
   * Return rain barrel with matching ID.
   * 
   * @param id The ID to be matched.
   * @return The rain barrel with the matching ID.
   */
  public static RainBarrel getRainBarrel(long id) {
   return barrels.get(id);
  }
}
