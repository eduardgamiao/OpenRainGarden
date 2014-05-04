package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import play.Logger;
import views.formdata.RainGardenFormData;

/**
 * Stores a list of contacts in a data structure.
 */
public class RainGardenDB {

  /**
   * Add rain garden.
   * 
   * @param formData Data form data.
   * @param userInfo Registering user.
   * @return The rain garden that has been added.
   */
  public static RainGarden addRainGarden(RainGardenFormData formData, UserInfo userInfo) {
    RainGarden garden;
    if (formData.id == -1) {
      garden = new RainGarden(formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.plants, formData.rainGardenSize, formData.waterFlowSourceSize, 
                              formData.waterFlowDescription, formData.infiltrationRate);
      garden.setOwner(userInfo);
      garden.getOwner().getGardens().add(garden);
      garden.save();
      return garden;
    }
    else {
      garden = RainGardenDB.getRainGarden(formData.id);
      garden.setTitle(formData.title);
      garden.setPropertyType(formData.propertyType);
      garden.setAddress(formData.address);
      garden.setHideAddress(formData.hideAddress);
      garden.setDescription(formData.description);
      garden.setDateInstalled(formData.month + "/" + formData.day + "/" + formData.year);
      garden.setPlants(formData.plants);
      garden.setRainGardenSize(formData.rainGardenSize);
      garden.setWaterFlowSourceSize(formData.waterFlowSourceSize);
      garden.setWaterFlowDescription(formData.waterFlowDescription);
      garden.setInfiltrationRate(formData.infiltrationRate);
      garden.save();
      return garden;
    }
  }

  /**
   * Return list of rain gardens.
   * 
   * @return List of rain gardens.
   */
  public static List<RainGarden> getRainGardens() {
    return RainGarden.find().all();
  }

  /**
   * Return rain garden with matching ID.
   * 
   * @param id The ID to be matched.
   * @return The rain garden with the matching ID.
   */
  public static RainGarden getRainGarden(long id) {
   return RainGarden.find().byId(id);
  }

  /**
   * Delete a rain garden.
   * 
   * @param id ID of rain garden.
   */
  public static void deleteRainGarden(long id) {
    if (hasID(id)) {
      RainGarden.find().byId(id).delete();
    }
  }

  /**
   * Check if database has ID.
   * @param id ID to check.
   * @return True if the database has the ID, false otherwise.
   */
  public static boolean hasID(long id) {
    return (getRainGarden(id) != null);
  }
}
