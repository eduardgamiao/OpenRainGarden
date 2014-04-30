package views.formdata;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;
import models.UserInfoDB;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Backing Class for Log in page form
 * @author Kyle
 *
 */
public class LoginFormData {
	/** Email of the user */
	public String email;
	
	/** Password of the user */
	public String password;
	
	/**
	 * Form Validation
	 * @return
	 */
	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.email == null || this.email.length() == 0) {
			errors.add(new ValidationError("email", "Please enter your email address."));
		}		
		if (this.password == null || this.password.length() == 0) {
			errors.add(new ValidationError("password", "Please enter your password."));
		}
		
		/*if (this.email != null && UserInfoDB.isUser(this.email) == true) {
			if (this.password != null && UserInfoDB.isValid(this.email, this.password) == false) {
				errors.add(new ValidationError("password", "Incorrect password."));
			}
				
		}
		else {
			errors.add(new ValidationError("email", "Entered email is not registered."));
		}*/
		
		if (UserInfoDB.isUser(this.email) != true) {
			errors.add(new ValidationError("email", "Incorrect user information."));
		}
		else {
			if (BCrypt.checkpw(this.password, UserInfoDB.getUser(this.email).getPassword()) == false) {
				errors.add(new ValidationError("password", "Incorrect user information."));
				errors.add(new ValidationError("email", "Incorrect user information"));
			}
		}
		
		return errors.isEmpty() ? null : errors;
	}
}
