package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.UserInfoDB;

/**
 * Backing class for the password recovery form
 * @author Kyle
 *
 */
public class PasswordRecoveryFormData {
	/** Email of the user */
	public String email;
	
	/**
	 * Form Validation
	 * @return
	 */
	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.email == null || this.email.length() == 0) {
			errors.add(new ValidationError("email", "Please enter your email address."));
		}
		if (UserInfoDB.isUser(this.email) != true) {
			errors.add(new ValidationError("email", "Incorrect user information."));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
