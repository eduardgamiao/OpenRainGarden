package views.formdata;

import java.util.List;
import java.util.ArrayList;

import models.Button;
import play.data.validation.ValidationError;

/**
 * Backing class for Sign in form.
 * @author Kyle
 *
 */
public class ButtonFormData {
	
  public long id = -1;
  
	public String blockNumber;
	
	public String title;
	
	public String href;
	
	
	/**
	 * Constructor.
	 */
	public ButtonFormData() {
	  
	}

	 public ButtonFormData(String a, String b, String c) {
	    this.blockNumber = a;
	    this.title = b;
	    this.href = c;
	  }
	
	public ButtonFormData(Button b) {
	  this.id = b.getId();
	  this.blockNumber = b.getNumber();
	  this.title = b.getTitle();
	  this.href = b.getHref();

	}

  /**
	 * Validation for Sign Up Form.
	 * @return A list of errors (empty list if form is valid)
	 */
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.blockNumber == null || this.blockNumber.length() == 0) {
			errors.add(new ValidationError("blockNumber", "Please type in blockNumber."));
		}
		if (this.title == null || this.title.length() == 0) {
			errors.add(new ValidationError("title", "Please type in title."));
		}
		if (this.href == null || this.href.length() == 0) {
			errors.add(new ValidationError("href", "Please type in url link."));
		}
		return errors.isEmpty() ? null : errors;
	}
}
