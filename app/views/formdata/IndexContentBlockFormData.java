package views.formdata;

import java.util.List;
import java.util.ArrayList;

import models.IndexContent;
import play.data.validation.ValidationError;

/**
 * Backing class for IndexContentBlock form.
 * @author Vinson Gao
 *
 */
public class IndexContentBlockFormData {
	
  public long id = -1;
  
	public String serial;
	
	public String title;
	
	public String content;
	
	public String image;
	
	
	/**
	 * Constructor.
	 */
	public IndexContentBlockFormData() {
	  
	}

	public IndexContentBlockFormData(String a, String b, String c, String d) {
	    this.serial = a;
	    this.title = b;
	    this.content = c;
	    this.image = d;
	}
	
	public IndexContentBlockFormData(IndexContent b) {
	  this.id = b.getId();
	  this.serial = b.getSerial();
	  this.title = b.getTitle();
	  this.content = b.getContent();
	  this.image = b.getPicUrl();

	}

  /**
	 * Validation for Sign Up Form.
	 * @return A list of errors (empty list if form is valid)
	 */
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.serial == null || this.serial.length() == 0) {
			errors.add(new ValidationError("blockNumber", "Please type in blockNumber."));
		}
		if (this.title == null || this.title.length() == 0) {
			errors.add(new ValidationError("title", "Please type in title."));
		}
		if (this.content == null || this.content.length() == 0) {
			errors.add(new ValidationError("content", "Please type in content."));
		}
		if (this.image == null || this.image.length() == 0) {
			errors.add(new ValidationError("image", "Please type in image url."));
		}
		return errors.isEmpty() ? null : errors;
	}
}
