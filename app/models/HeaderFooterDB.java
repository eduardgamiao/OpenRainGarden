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

  private static Map<Integer, Object> block = new LinkedHashMap<Integer, Object>();
  
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
    return (String)block.get(1);
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
    return (String)b;
  }
  
  /**
   * Retrieve a header from the database.
   * @ 2 represent the key of header.
   * @return string of the header to retrieve.
   */
  public static String getSubHeader() {
    return (String)block.get(2);
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
    return (String)block.get(3);
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
   * Retrieve a banner image from the database.
   * @ 2 represent the key of header.
   * @return string of the header to retrieve.
   */
  public static String getSubFooter() {
    return (String)block.get(4);
  }
  
  /**
   * Add a header banner image to the database.
   * @param header text to add.
   * @return The header that was added to the database.
   * @5 is the index number in linked list for the banner image.
   */public static String setBannerImage(String b) {
		if(block.containsValue(5)){
			block.remove(5);
		}
		block.put(5, b);
	    return b;
	  }
	  
	  /**
	   * Retrieve a header from the database.
	   * @ 2 represent the key of header.
	   * @return string of the header to retrieve.
	   */
	  public static String getBannerImage() {
	    return (String)block.get(5);
	  }

	  public static byte[] getImage() {
		byte[] b = (byte[]) block.get(6);
		return b;
	  }

		  /**
		   * @param image the image to set
		   */
	  public static void setImage(byte [] image) {
		  if(block.containsValue(6)){
				block.remove(6);
			}
			block.put(6, image);
		  
      }
}