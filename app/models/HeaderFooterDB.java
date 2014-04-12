package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A database of index page button.
 * @author vinson gao
 *
 */
public class HeaderFooterDB {

  private static Map<Integer, String> block = new LinkedHashMap<Integer, String>();
  
  /**
   * Add a header to the database.
   * @param header text to add.
   * @return The header that was added to the database.
   * @ 1 represent the key of header.
   * @ 2 represent the key of subheader.
   */
  public static String setHeader(String b) {
	if(block.containsValue(1)){
		block.remove(1);
	}
	block.put(1, b);
    return b;
  }
  
  /**
   * Retrieve a header from the database.
   * @ 1 represent the key of header.
   * @return string of the header to retrieve.
   */
  public static String getHeader() {
    return block.get(1);
  }
  
  /**
   * Add a header to the database.
   * @param header text to add.
   * @return The header that was added to the database.
   * @ 1 represent the key of header.
   * @ 2 represent the key of subheader.
   */
  public static String setSubHeader(String b) {
	if(block.containsValue(2)){
		block.remove(2);
	}
	block.put(2, b);
    return b;
  }
  
  /**
   * Retrieve a header from the database.
   * @ 2 represent the key of header.
   * @return string of the header to retrieve.
   */
  public static String getSubHeader() {
    return block.get(2);
  }
  
  
  
  
  
  
  
  public static String setFooter(String b) {
	if(block.containsValue(3)){
		block.remove(3);
	}
	block.put(3, b);
    return b;
  }
  
  /**
   * Retrieve a header from the database.
   * @ 1 represent the key of header.
   * @return string of the header to retrieve.
   */
  public static String getFooter() {
    return block.get(3);
  }
  
  /**
   * Add a header to the database.
   * @param header text to add.
   * @return The header that was added to the database.
   * @ 1 represent the key of header.
   * @ 2 represent the key of subheader.
   */
  public static String setSubFooter(String b) {
	if(block.containsValue(4)){
		block.remove(4);
	}
	block.put(4, b);
    return b;
  }
  
  /**
   * Retrieve a header from the database.
   * @ 2 represent the key of header.
   * @return string of the header to retrieve.
   */
  public static String getSubFooter() {
    return block.get(4);
  }
  


}