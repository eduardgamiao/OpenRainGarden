package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import views.formdata.RainGardenFormData;

/**
 * Stores a list of contacts in a data structure.
 */
public class RainGardenDB {
  private static long currentId = 1;

  private static HashMap<Long, RainGarden> gardens = new HashMap<Long, RainGarden>();

  /**
   * Add rain garden.
   * 
   * @param formData Data form data.
   * @param userInfo Registering user.
   * @return The rain garden that has been added.
   */
  public static RainGarden addRainGarden(RainGardenFormData formData, UserInfo userInfo) {
    RainGarden garden;
    if (formData.id == 0) {
      long id = currentId;
      currentId++;
      garden = new RainGarden(id, formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.plants, formData.rainGardenSize, formData.waterFlowSourceSize, 
                              formData.waterFlowDescription, formData.infiltrationRate, formData.numberOfRainGardens);
      garden.setOwner(userInfo);
      garden.getOwner().getGardens().add(garden);
      gardens.put(id, garden);
      return garden;
    }
    else {
      garden = new RainGarden(formData.id, formData.title, formData.propertyType, formData.address, 
          formData.hideAddress,  formData.description, formData.month + "/" + formData.day + "/" + formData.year, 
          formData.plants, formData.rainGardenSize, formData.waterFlowSourceSize, 
          formData.waterFlowDescription, formData.infiltrationRate, formData.numberOfRainGardens);
      garden.setOwner(userInfo);
      garden.getOwner().getGardens().add(garden);
      gardens.put(garden.getID(), garden);
      return garden;
    }
  }

  /**
   * Return list of rain gardens.
   * 
   * @return List of rain gardens.
   */
  public static List<RainGarden> getRainGardens() {
    return new ArrayList<RainGarden>(gardens.values());
  }

  /**
   * Return rain garden with matching ID.
   * 
   * @param id The ID to be matched.
   * @return The rain garden with the matching ID.
   */
  public static RainGarden getRainGarden(long id) {
   return gardens.get(id);
  }

  /**
   * Delete a rain garden.
   * 
   * @param id ID of rain garden.
   */
  public static void deleteRainGarden(long id) {
    gardens.remove(id);
  }

}
