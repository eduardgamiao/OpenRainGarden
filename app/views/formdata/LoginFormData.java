package views.formdata;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;
import models.UserInfoDB;

/**
 * Backing Class for Log in page form
 * @author Kyle
 *
 */
public class LoginFormData {
	public String email;
	public String password;
	
	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<>();
		
		if (this.email == null || this.email.length() == 0) {
			errors.add(new ValidationError("email", "Please enter your email address."));
		}		
		if (this.password == null || this.password.length() == 0) {
			errors.add(new ValidationError("password", "Please enter your password."));
		}
		
		if (this.email != null && UserInfoDB.isUser(this.email) == true) {
			if (this.password != null && UserInfoDB.isValid(this.email, this.password) == false) {
				errors.add(new ValidationError("password", "Incorrect password."));
			}
				
		}
		else {
			errors.add(new ValidationError("email", "Entered email is not registered."));
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
