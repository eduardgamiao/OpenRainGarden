package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import views.formdata.GardenCommentFormData;

/**
 * Handles all acessor methods for garden comments.
 * @author eduardgamiao
 *
 */
public class GardenCommentDB {
  
  /**
   * Add a garden comment to a rain garden.
   * @param formData Form data for a garden comment.
   * @param garden The garden being commented on.
   * @param userInfo The person posting the comment.
   * @return The recently added/edited garden comment.
   */
  public static GardenComment addComment(GardenCommentFormData formData, RainGarden garden, UserInfo userInfo) {
    GardenComment comment;
    
    if (formData.id == -1) {
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
      Date date = new Date();
      comment = new GardenComment(formData.comment, userInfo, garden, dateFormat.format(date));
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
   * Get a garden comment.
   * @param id ID of the garden comment.
   * @return The garden comment matching the ID.
   */
  public static GardenComment getComment(Long id) {
    return GardenComment.find().byId(id);
  }
  
  /**
   * Return a list of comments on a garden.
   * @param id The ID of the garden.
   * @return A list of comments on a garden.
   */
  public static List<GardenComment> getRainGardenComments(Long id) {
    return GardenComment.find().where().eq("garden_id", id).findList();
  }

}
