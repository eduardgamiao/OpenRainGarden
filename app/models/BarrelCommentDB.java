package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import play.Logger;
import views.formdata.BarrelCommentFormData;

/**
 * Handles all acessor methods for garden comments.
 * @author eduardgamiao
 *
 */
public class BarrelCommentDB {
  
  /**
   * Add a garden comment to a rain barrel.
   * @param formData Form data for a barrel comment.
   * @param barrel The barrel being commented on.
   * @param userInfo The person posting the comment.
   * @return The recently added/edited barrel comment.
   */
  public static BarrelComment addComment(BarrelCommentFormData formData, RainBarrel barrel, UserInfo userInfo) {
    BarrelComment comment;
    Logger.debug("" + (formData.id == -1));
    if (formData.id == -1) {
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      Date date = new Date();
      comment = new BarrelComment(formData.comment, userInfo, barrel, dateFormat.format(date));
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
   * Get a barrel comment.
   * @param id ID of the barrel comment.
   * @return The barrel comment matching the ID.
   */
  public static BarrelComment getComment(Long id) {
    return BarrelComment.find().byId(id);
  }
  
  /**
   * Return a list of comments on a barrel.
   * @param id The ID of the barrel.
   * @return A list of comments on a barrel.
   */
  public static List<BarrelComment> getRainBarrelComments(Long id) {
    return BarrelComment.find().where().eq("barrel_id", id).findList();
  }

}
