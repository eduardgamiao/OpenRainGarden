package views.formdata;

import java.util.List;
import java.util.ArrayList;

import models.IndexContent;
import models.Resource;
import play.data.validation.ValidationError;

/**
 * Backing class for edit Index block form
 * @author Vinson Gao
 *
 */
public class BlockFormData {
	public String serial;
	public String header;
	public String content;

	
	public BlockFormData() {
		this.serial = "";
		this.header = "";
		this.content = "";

	}
	
	public BlockFormData(String id, String header, String content) {
		this.serial = id;
		this.header = header;
		this.content = content;

	}
	
	public BlockFormData(IndexContent resource) {
		this.serial = resource.getSerial();
		this.header = resource.getTitle();
		this.content = resource.getContent();

	}
	
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		if (this.serial == null || this.serial.length() == 0) {
			errors.add(new ValidationError("serial", "Please enter a block number."));
		}
		if (this.header == null || this.header.length() == 0) {
			errors.add(new ValidationError("header", "Please enter a header."));
		}
		if (this.content == null || this.content.length() == 0) {
			errors.add(new ValidationError("url", "Please enter the content."));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
