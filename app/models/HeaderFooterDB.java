package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import views.formdata.HeaderFooterFormData;

/**
 * A database of index page button.
 * @author vinson gao
 *
 */
public class HeaderFooterDB {
  
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

  public static HeaderFooter getHeaderFooter(long id) {
    return HeaderFooter.find().byId(id);
  }
  
  public static String getHeader(long id) {
    return HeaderFooter.find().byId(id).getHeader();
  }
  
  public static String getFooter(long id) {
    return HeaderFooter.find().byId(id).getFooter();
  }
  
  public static String getSubHeader(long id) {
    return HeaderFooter.find().byId(id).getSubHeader();
  }
  
  public static String getSubFooter(long id) {
    return HeaderFooter.find().byId(id).getSubFooter();
  }
  
  public static boolean isEmpty() {
    return HeaderFooter.find().all().isEmpty();
  }
}