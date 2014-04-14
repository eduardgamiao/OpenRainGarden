package views.formdata;

import java.util.List;
import java.util.ArrayList;
import models.Resource;
import play.data.validation.ValidationError;

/**
 * Backing class for edit resource form
 * @author Kyle
 *
 */
public class ResourceFormData {
	public String header;
	public String imageName;
	public String url;
	
	public ResourceFormData() {
		this.header = "";
		this.imageName = "";
		this.url = "";
	}
	
	public ResourceFormData(String header, String imageName, String url) {
		this.header = header;
		this.imageName = imageName;
		this.url = url;
	}
	
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.header == null || this.header.length() == 0) {
			errors.add(new ValidationError("header", "Please enter a header for the resource."));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
