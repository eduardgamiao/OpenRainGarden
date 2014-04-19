package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import views.formdata.PaverCommentFormData;
import views.formdata.PaverCommentFormData;

/**
 * Handles all acessor methods for paver comments.
 * @author eduardgamiao
 *
 */
public class PaverCommentDB {
  
  /**
   * Add a paver comment to a permeable paver.
   * @param formData Form data for a paver comment.
   * @param paver The paver being commented on.
   * @param userInfo The person posting the comment.
   * @return The recently added/edited paver comment.
   */
  public static PaverComment addComment(PaverCommentFormData formData, PermeablePavers paver, 
                                        UserInfo userInfo) {
    PaverComment comment;
    
    if (formData.id == -1) {
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      Date date = new Date();
      comment = new PaverComment(formData.comment, userInfo, paver, dateFormat.format(date));
      comment.save();
    }
    else {
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      Date date = new Date();
      comment = getComment(formData.id);
      comment.setComment(formData.comment);
      comment.setDatePosted(dateFormat.format(date));      
      comment.save();
    }
    return comment;
  }
  
  /**
   * Get a paver comment.
   * @param id ID of the paver comment.
   * @return The paver comment matching the ID.
   */
  public static PaverComment getComment(Long id) {
    return PaverComment.find().byId(id);
  }
  
  /**
   * Return a list of comments on a paver.
   * @param id The ID of the paver.
   * @return A list of comments on a paver.
   */
  public static List<PaverComment> getRainPaverComments(Long id) {
    return PaverComment.find().where().eq("paver_id", id).findList();
  }

}
