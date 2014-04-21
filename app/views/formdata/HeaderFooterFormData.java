package views.formdata;

import java.util.List;
import java.util.ArrayList;
import models.HeaderFooter;
import models.HeaderFooterDB;
import models.IndexContent;
import play.data.validation.ValidationError;

/**
 * Backing class for Sign in form.
 * @author Kyle
 *
 */
public class HeaderFooterFormData {
	
  public long id = -1;
  
	public String header;
	
	public String subHeader;
	
	public String footer;
	
	public String subFooter;
	
	/**
	 * Constructor.
	 */
	public HeaderFooterFormData() {
	  
	}

	 public HeaderFooterFormData(String header, String subHeader, String footer, String subFooter) {
	    this.header = header;
	    this.subHeader = subHeader;
	    this.footer = footer;
	    this.subFooter = subFooter;
	  }
	
	public HeaderFooterFormData(HeaderFooter headerFooter) {
	  this.id = headerFooter.getId();
	  this.header = headerFooter.getHeader();
	  this.subHeader = headerFooter.getSubHeader();
	  this.footer = headerFooter.getFooter();
	  this.subFooter = headerFooter.getSubFooter();
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
		if (this.subHeader == null || this.subHeader.length() == 0) {
			errors.add(new ValidationError("subheader", "Please type in subheader."));
		}
		if (this.footer == null || this.footer.length() == 0) {
			errors.add(new ValidationError("footer", "Please type in footer."));
		}
		if (this.subFooter == null || this.subFooter.length() == 0) {
			errors.add(new ValidationError("subfooter", "Please type in subfooter."));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
