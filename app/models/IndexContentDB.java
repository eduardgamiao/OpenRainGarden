package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import views.formdata.IndexContentBlockFormData;

/**
 * A database of plants.
 * @author vinson gao
 *
 */
public class IndexContentDB {

	  public static IndexContent addBlock(IndexContentBlockFormData formData) {
		  IndexContent idexContent;
		    
		    if (formData.id == -1) {
		    	idexContent = new IndexContent(formData.serial, formData.title, formData.content, formData.image);
		    }
		    else {
		    	idexContent = IndexContentDB.getBlock(formData.id);
		    	idexContent.setSerial(formData.serial);
		    	idexContent.setTitle(formData.title);
		    	idexContent.setContent(formData.content);
		    	idexContent.setImage(formData.image);
		    	
		    }
		    idexContent.save();
		    
		    return idexContent;
	  }
	  public static IndexContent getBlock(long id) {
		  return IndexContent.find().byId(id);
	  }
	  public static IndexContent getBlock(String serial) {
		  List<IndexContent> i = IndexContent.find().all();
		  for(IndexContent c: i){
			  if(c.getSerial().equals(serial)){
				  return IndexContent.find().byId(c.getId());
			  }
		  }
		  return null;
	  }
	
		public static String getContent(long id) {
			    return IndexContent.find().byId(id).getContent();
		}
		public static String getTitle(long id) {
			    return IndexContent.find().byId(id).getTitle();
		}
		public static String getBlockNumber(long id) {
			    return IndexContent.find().byId(id).getSerial();
		}
		public static String getPicUrl(long id) {
		    return IndexContent.find().byId(id).getPicUrl();
		}
		public static List<IndexContent> getBlocks() {
			    return IndexContent.find().all();
		}
	  public static boolean isEmpty() {
		  return IndexContent.find().all().isEmpty();
	  }

	
}