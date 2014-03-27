package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import views.formdata.PermeablePaversFormData;
import views.formdata.RainBarrelFormData;

/**
 * Stores a list of contacts in a data structure.
 */
public class PermeablePaversDB {

  private static HashMap<Long, PermeablePavers> pavers = new HashMap<Long, PermeablePavers>();

  /**
   * Add permeable paver.
   * 
   * @param formData Permeable paver form data.
   * @param userInfo User tied to permeable paver.
   * @return The permeable paver that has been added.
   */
  public static PermeablePavers addPermeablePavers(PermeablePaversFormData formData, UserInfo userInfo) {
    PermeablePavers paver;
    if (formData.id == 0) {
      long id = pavers.size() + 1;
      paver = new PermeablePavers(id, formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.material, formData.previousMaterial, formData.size, formData.installer);
      paver.setOwner(userInfo);
      userInfo.getPavers().add(paver);
      pavers.put(id, paver);
      return paver;
    }
    else {
      paver = new PermeablePavers(formData.id, formData.title, formData.propertyType, formData.address, 
          formData.hideAddress,  formData.description, formData.month + "/" + formData.day + "/" + formData.year,
          formData.material, formData.previousMaterial, formData.size, formData.installer);
      paver.setOwner(userInfo);
      userInfo.getPavers().add(paver);
      pavers.put(paver.getID(), paver);
      return paver;
    }
  }

  /**
   * Return list of permeable pavers.
   * 
   * @return List of permeable pavers.
   */
  public static List<PermeablePavers> getPermeablePavers() {
    return new ArrayList<PermeablePavers>(pavers.values());
  }

  /**
   * Return permeable paver with matching ID.
   * 
   * @param id The ID to be matched.
   * @return Thepermeable paver with the matching ID.
   */
  public static PermeablePavers getPermeablePavers(long id) {
   return pavers.get(id);
  }
}
