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
	public long id = 0;
	public String header;
	public String url;
	
	public ResourceFormData() {
		this.id = 0;
		this.header = "";
		this.url = "";
	}
	
	public ResourceFormData(long id, String header, String url) {
		this.id = id;
		this.header = header;
		this.url = url;
	}
	
	public ResourceFormData(Resource resource) {
		this.id = resource.getID();
		this.header = resource.getHeader();
		this.url = resource.getUrl();
	}
	
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.header == null || this.header.length() == 0) {
			errors.add(new ValidationError("header", "Please enter a header for the resource."));
		}
		if (this.url == null || this.url.length() == 0) {
			errors.add(new ValidationError("url", "Please enter a URL for the resource."));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
