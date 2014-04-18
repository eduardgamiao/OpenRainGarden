package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import play.Logger;
import views.formdata.RainBarrelFormData;

/**
 * Stores a list of contacts in a data structure.
 */
public class RainBarrelDB {
  
  /**
   * Add rain barrel.
   * 
   * @param formData Rain barrel form data.
   * @param userInfo User tied to rain barrel.
   * @return The rain barrel that has been added.
   */
  public static RainBarrel addRainBarrel(RainBarrelFormData formData, UserInfo userInfo) {
    RainBarrel barrel;
    if (formData.id == -1) {
      barrel = new RainBarrel(formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.rainBarrelType, formData.capacity, formData.color, formData.material, 
                              formData.estimatedCost, formData.waterUse, formData.overflowFrequency, formData.cover,
                              formData.obtainedFrom, formData.installationType);
      barrel.setOwner(userInfo);
      userInfo.getBarrels().add(barrel);
      userInfo.save();
      barrel.save();
      return barrel;
    }
    else {
      barrel = RainBarrelDB.getRainBarrel(formData.id);
      barrel.setTitle(formData.title);
      barrel.setPropertyType(formData.propertyType);
      barrel.setAddress(formData.address);
      barrel.setHideAddress(formData.hideAddress);
      barrel.setDescription(formData.description);
      barrel.setDateInstalled(formData.month + "/" + formData.day + "/" + formData.year);
      barrel.setRainBarrelType(formData.rainBarrelType);
      barrel.setCapacity(formData.capacity);
      barrel.setColor(formData.color);
      barrel.setMaterial(formData.material);
      barrel.setEstimatedCost(formData.estimatedCost);
      barrel.setWaterUse(formData.waterUse);
      barrel.setOverflowFrequency(formData.overflowFrequency);
      barrel.setCover(formData.cover);
      barrel.setObtainedFrom(formData.obtainedFrom);
      barrel.setInstallationType(formData.installationType);
      barrel.setOwner(userInfo);
      userInfo.save();
      barrel.save();
      return barrel;
    }
  }

  /**
   * Return list of rain barrels.
   * 
   * @return List of rain barrels.
   */
  public static List<RainBarrel> getRainBarrels() {
    return RainBarrel.find().all();
  }

  /**
   * Return rain barrel with matching ID.
   * 
   * @param id The ID to be matched.
   * @return The rain barrel with the matching ID.
   */
  public static RainBarrel getRainBarrel(long id) {
   return RainBarrel.find().byId(id);
  }
  
  /**
   * Delete a rain barrel from database.
   * @param id The ID of the rain barrel to delete.
   */
  public static void deleteRainBarrel(long id) {
    
  }
  
  /**
   * Check if database has ID.
   * @param id ID to check.
   * @return True if the database has the ID, false otherwise.
   */
  public static boolean hasID(long id) {
    return (getRainBarrel(id) != null);
  }
}
