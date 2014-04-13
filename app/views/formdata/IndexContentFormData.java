package views.formdata;

import java.util.List;
import java.util.ArrayList;

import models.HeaderFooterDB;
import models.IndexContent;
import play.data.validation.ValidationError;

/**
 * Backing class for Sign in form.
 * @author Kyle
 *
 */
public class IndexContentFormData {
	
	public String header;
	
	public String subheader;
	
	//public String footer;
	
	//public String subfooter;
	
	
	/**
	 * Constructor.
	 * @param userInfo The user.
	 */
	public IndexContentFormData() {
	  this.header    = HeaderFooterDB.getHeader();
	  this.subheader = HeaderFooterDB.getSubHeader();
	  this.footer    = HeaderFooterDB.getFooter();
	  this.subfooter = HeaderFooterDB.getSubFooter();
	  header
	}
	
	/**
	 * Validation for Sign Up Form.
	 * @return A list of errors (empty list if form is valid)
	 */
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.header == null || this.header.length() == 0) {
			errors.add(new ValidationError("header", "Please type in header."));
		}
		if (this.subheader == null || this.subheader.length() == 0) {
			errors.add(new ValidationError("subheader", "Please type in subheader."));
		}
		if (this.footer == null || this.footer.length() == 0) {
			errors.add(new ValidationError("footer", "Please type in footer."));
		}
		if (this.subfooter == null || this.subfooter.length() == 0) {
			errors.add(new ValidationError("subfooter", "Please type in subfooter."));
		}	
		
		return errors.isEmpty() ? null : errors;
	}
}
