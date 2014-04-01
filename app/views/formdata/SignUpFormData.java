package views.formdata;

import java.util.List;
import java.util.ArrayList;
import play.data.validation.ValidationError;

/**
 * Backing class for Sign in form.
 * @author Kyle
 *
 */
public class SignUpFormData {
	/** First Name of user.   */
	public String firstName;
	
	/** Last Name of user.	 */
	public String lastName;
	
	/** Email of user.	 */
	public String email;
	
	/** Telephone of user.	 */
	public String telephone;
	
	/** Password of user.	 */
	public String password;
	
	/**
	 * Validation for Sign Up Form.
	 * @return A list of errors (empty list if form is valid)
	 */
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.firstName == null || this.firstName.length() == 0) {
			errors.add(new ValidationError("firstName", "Please enter your first name."));
		}
		if (this.lastName == null || this.lastName.length() == 0) {
			errors.add(new ValidationError("lastName", "Please enter your last name."));
		}
		if (this.email == null || this.email.length() == 0) {
			errors.add(new ValidationError("email", "Please enter your email address."));
		}
		//if (this.telephone == null || this.telephone.length() == 0) {
			//errors.add(new ValidationError("telephone", "Please enter your telephone number."));
		//}
		if (this.password == null || this.password.length() == 0) {
			errors.add(new ValidationError("password", "Please enter your desired password."));
		}	
		
		return errors.isEmpty() ? null : errors;
	}
}
