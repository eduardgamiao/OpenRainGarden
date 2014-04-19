package models;

import java.util.List;
import views.formdata.PermeablePaversFormData;

/**
 * Stores a list of contacts in a data structure.
 */
public class PermeablePaversDB {

  /**
   * Add permeable paver.
   * 
   * @param formData Permeable paver form data.
   * @param userInfo User tied to permeable paver.
   * @return The permeable paver that has been added.
   */
  public static PermeablePavers addPermeablePavers(PermeablePaversFormData formData, UserInfo userInfo) {
    PermeablePavers paver;
    if (formData.id == -1) {
      paver = new PermeablePavers(formData.title, formData.propertyType, formData.address, formData.hideAddress, 
                              formData.description, formData.month + "/" + formData.day + "/" + formData.year,
                              formData.material, formData.previousMaterial, formData.size, formData.installer);
      paver.setOwner(userInfo);
      userInfo.getPavers().add(paver);
      userInfo.save();
      paver.save();
      return paver;
    }
    else {
      paver = PermeablePaversDB.getPermeablePavers(formData.id);
      paver.setTitle(formData.title);
      paver.setPropertyType(formData.propertyType);
      paver.setAddress(formData.address);
      paver.setHideAddress(formData.hideAddress);
      paver.setDateInstalled(formData.month + "/" + formData.day + "/" + formData.year);
      paver.setMaterial(formData.material);
      paver.setPreviousMaterial(formData.previousMaterial);
      paver.setSize(formData.size);
      paver.setInstaller(formData.installer);
      paver.save();
      return paver;
    }
  }

  /**
   * Return list of permeable pavers.
   * 
   * @return List of permeable pavers.
   */
  public static List<PermeablePavers> getPermeablePavers() {
    return PermeablePavers.find().all();
  }

  /**
   * Return permeable paver with matching ID.
   * 
   * @param id The ID to be matched.
   * @return Thepermeable paver with the matching ID.
   */
  public static PermeablePavers getPermeablePavers(long id) {
   return PermeablePavers.find().byId(id);
  }
  
  /**
   * Delete a permeable paver.
   * @param id ID of permeable paver.
   */
  public static void deletePermeablePaver(long id) {

  }
  
  /**
   * Check if database has ID.
   * @param id ID to check.
   * @return True if the database has the ID, false otherwise.
   */
  public static boolean hasID(long id) {
    return (getPermeablePavers(id) != null);
  }
}
