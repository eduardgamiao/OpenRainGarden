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
  private static long currentId = 1;

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
      long id = currentId;
      currentId++;
      barrel = new RainBarrel(id, formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.rainBarrelType, formData.capacity, formData.color, formData.material, 
                              formData.estimatedCost, formData.waterUse, formData.overflowFrequency, formData.cover,
                              formData.obtainedFrom, formData.installationType, formData.numberOfRainBarrels);
      barrel.setOwner(userInfo);
      userInfo.getBarrels().add(barrel);
      barrels.put(id, barrel);
      CommentDB.initializeCommentSection(barrel.getKey());
      return barrel;
    }
    else {
      byte [] picture = getRainBarrel(formData.id).getImage();
      barrel = new RainBarrel(formData.id, formData.title, formData.propertyType, formData.address, 
                              formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.rainBarrelType, formData.capacity, formData.color, formData.material, 
                              formData.estimatedCost, formData.waterUse, formData.overflowFrequency, formData.cover,
                              formData.obtainedFrom, formData.installationType, formData.numberOfRainBarrels);
      barrel.setImage(picture);
      barrel.setOwner(userInfo);
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
  
  /**
   * Delete a rain barrel from database.
   * @param id The ID of the rain barrel to delete.
   */
  public static void deleteRainBarrel(long id) {
    barrels.remove(id);
  }
  
  /**
   * Check if database has ID.
   * @param id ID to check.
   * @return True if the database has the ID, false otherwise.
   */
  public static boolean hasID(long id) {
    if ((id == 0) || barrels.containsKey(id)) {
      return true;
    }
    return false;
  }
}
