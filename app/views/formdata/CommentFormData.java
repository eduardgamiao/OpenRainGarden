package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.Comment;

/**
 * Handles form submission for posting a comment.
 * 
 * @author eduardgamiao
 * 
 */
public class CommentFormData {
  
  /** The ID of the comment. */
  public long id = -1;

  /** The comment. */
  public String comment;

  /**
   * Constructor.
   */
  public CommentFormData() {
  }

  /**
   * Constructor.
   * 
   * @param comment The comment being posted.
   */
  public CommentFormData(String comment) {
    this.comment = comment;
  }

  /**
   * Constructor.
   * 
   * @param comment An existing comment object.
   */
  public CommentFormData(Comment comment) {
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
