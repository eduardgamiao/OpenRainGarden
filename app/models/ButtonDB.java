package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import views.formdata.ButtonFormData;

/**
 * A database of index page button.
 * @author vinson gao
 *
 */
public class ButtonDB {

  public static Button add(ButtonFormData formData) {
	  Button button;
	    
	    if (formData.id == -1) {
	    	button = new Button(formData.blockNumber, formData.title, formData.href);
	    }
	    else {
	    	button = ButtonDB.getButton(formData.id);
	    	button.setNumber(formData.blockNumber);
	    	button.setTitle(formData.title);
	    	button.setHref(formData.href);
	    	
	    }
	    button.save();
	    
	    return button;
  }
  public static Button getButton(long id) {
	    return Button.find().byId(id);
  }
  public static String getHref(long id) {
	    return Button.find().byId(id).getHref();
  }
  public static String getTitle(long id) {
	    return Button.find().byId(id).getTitle();
  }
  public static String getBlockNumber(long id) {
	    return Button.find().byId(id).getNumber();
  }
  public static List<Button> getButtons() {
	    return Button.find().all();
  }
}