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
      CommentDB.initializeCommentSection(garden.getKey());
      return garden;
    }
    else {
      byte [] picture = RainGardenDB.getRainGarden(formData.id).getImage();
      garden = new RainGarden(formData.id, formData.title, formData.propertyType, formData.address, 
          formData.hideAddress,  formData.description, formData.month + "/" + formData.day + "/" + formData.year, 
          formData.plants, formData.rainGardenSize, formData.waterFlowSourceSize, 
          formData.waterFlowDescription, formData.infiltrationRate, formData.numberOfRainGardens);
      garden.setOwner(userInfo);
      garden.setImage(picture);
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
    RainGarden garden = RainGardenDB.getRainGarden(id);
    if (garden != null) {
      garden.getOwner().deleteGarden(garden);
    }
    gardens.remove(id);
  }

  /**
   * Check if database has ID.
   * @param id ID to check.
   * @return True if the database has the ID, false otherwise.
   */
  public static boolean hasID(long id) {
    if ((id == 0) || gardens.containsKey(id)) {
      return true;
    }
    return false;
  }
}
