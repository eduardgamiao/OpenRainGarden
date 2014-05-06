package models;

import java.util.ArrayList;
import java.util.List;
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
  
  public static List<Button> getButtonsFromOneBlock(String blocknum) {
	    List<Button> mylist =  new ArrayList<Button>();
	    List<Button> thelist =  new ArrayList<Button>();
	    thelist= Button.find().all();
	    for(Button button:thelist){
	    	if(button.getNumber().equals(blocknum)){
	    		mylist.add(button);
	    	}
	    }
	    return mylist;
  }
  public static boolean isEmpty() {
	  return Button.find().all().isEmpty();
  }
}