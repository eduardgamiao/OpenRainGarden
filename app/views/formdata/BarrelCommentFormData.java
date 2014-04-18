package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.BarrelComment;

/**
 * Handles form submission for posting a comment.
 * 
 * @author eduardgamiao
 * 
 */
public class BarrelCommentFormData {
  
  /** ID of barrel comment. */
  public long id = -1;

  /** The comment. */
  public String comment;

  /**
   * Constructor.
   */
  public BarrelCommentFormData() {
  }

  /**
   * Constructor.
   * 
   * @param comment The comment being posted.
   */
  public BarrelCommentFormData(String comment) {
    this.comment = comment;
  }

  /**
   * Constructor.
   * 
   * @param comment An existing comment object.
   */
  public BarrelCommentFormData(BarrelComment comment) {
    this.comment = comment.getComment();
  }

  /**
   * Validates form.
   * @return A list of errors if they exist, otherwise null.
   */
  public List<ValidationError> validate() {
    ArrayList<ValidationError> errors = new ArrayList<ValidationError>();

    if (this.comment.isEmpty() || this.comment == null) {
      errors.add(new ValidationError("comment", "A comment is required."));
    }

    return errors.isEmpty() ? null : errors;
  }
}
