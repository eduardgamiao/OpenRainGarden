package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.PaverComment;

/**
 * Handles form submission for posting a comment.
 * 
 * @author eduardgamiao
 * 
 */
public class PaverCommentFormData {
  /** ID for garden comment. */
  public long id = -1;

  /** The comment. */
  public String comment;

  /**
   * Constructor.
   */
  public PaverCommentFormData() {
  }

  /**
   * Constructor.
   * 
   * @param comment The comment being posted.
   */
  public PaverCommentFormData(String comment) {
    this.comment = comment;
  }

  /**
   * Constructor.
   * 
   * @param comment An existing comment object.
   */
  public PaverCommentFormData(PaverComment comment) {
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
