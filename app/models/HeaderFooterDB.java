package models;

import views.formdata.HeaderFooterFormData;

/**
 * A database of index page button.
 * @author vinson gao
 *
 */
public class HeaderFooterDB {
  /**
   * 
   * @param formData
   * @return
   */
  public static HeaderFooter add(HeaderFooterFormData formData) {
    HeaderFooter headerFooter;
    
    if (formData.id == -1) {
      headerFooter = new HeaderFooter(formData.header, formData.subHeader, formData.footer, formData.subFooter);
    }
    else {
      headerFooter = HeaderFooterDB.getHeaderFooter(formData.id);
      headerFooter.setHeader(formData.header);
      headerFooter.setSubHeader(formData.subHeader);
      headerFooter.setFooter(formData.footer);
      headerFooter.setSubFooter(formData.subFooter);
    }
    headerFooter.save();
    
    return headerFooter;
  }
/**
 * 
 * @param id
 * @return
 */
  public static HeaderFooter getHeaderFooter(long id) {
    return HeaderFooter.find().byId(id);
  }
  /**
   * 
   * @param id
   * @return
   */
  public static String getHeader(long id) {
    return HeaderFooter.find().byId(id).getHeader();
  }
  /**
   * 
   * @param id
   * @return
   */
  public static String getFooter(long id) {
    return HeaderFooter.find().byId(id).getFooter();
  }
  /**
   * 
   * @param id
   * @return
   */
  public static String getSubHeader(long id) {
    return HeaderFooter.find().byId(id).getSubHeader();
  }
  /**
   * 
   * @param id
   * @return
   */
  public static String getSubFooter(long id) {
    return HeaderFooter.find().byId(id).getSubFooter();
  }
  /**
   * 
   * @return
   */
  public static boolean isEmpty() {
    return HeaderFooter.find().all().isEmpty();
  }
}