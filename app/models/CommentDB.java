package models;

import play.Logger;
import views.formdata.CommentFormData;

/**
 * Database that stores comments.
 * @author eduardgamiao
 *
 */
public class CommentDB {

  /**
   * Add a comment to the database.
   * @param data The form data holding the comment.
   * @param garden The garden being commented on.
   * @param userInfo The poster of the comment.
   * @return The newly created/edited comment.
   */
  public static Comment addComment(CommentFormData data, RainGarden garden, UserInfo userInfo) {
    Comment comment;
    if (data.id == -1) {
      comment = new Comment(data.comment, garden, userInfo);
      comment.save();
    }
    else {
      comment = CommentDB.getComment(data.id);
      if (comment != null) {
        comment.setComment(data.comment);
        comment.save();
      }
    }   
    return comment;
  }
  
  /**
   * Add a comment to the database.
   * @param data The form data holding the comment.
   * @param barrel The barrel being commented on.
   * @param userInfo The poster of the comment.
   * @return The newly created/edited comment.
   */
  public static Comment addComment(CommentFormData data, RainBarrel barrel, UserInfo userInfo) {
    Comment comment;
    if (data.id == -1) {
      comment = new Comment(data.comment, barrel, userInfo);
      comment.save();
    }
    else {
      comment = CommentDB.getComment(data.id);
      if (comment != null) {
        comment.setComment(data.comment);
        comment.save();
      }
    }   
    return comment;
  }
  
  /**
   * Add a comment to the database.
   * @param data The form data holding the comment.
   * @param paver The paver being commented on.
   * @param userInfo The poster of the comment.
   * @return The newly created/edited comment.
   */
  public static Comment addComment(CommentFormData data, PermeablePavers paver, UserInfo userInfo) {
    Comment comment;
    if (data.id == -1) {
      comment = new Comment(data.comment, paver, userInfo);
      comment.save();
    }
    else {
      comment = CommentDB.getComment(data.id);
      if (comment != null) {
        comment.setComment(data.comment);
        comment.save();
      }
    }   
    return comment;
  }
  
  /**
   * Retrieve a comment from the database.
   * @param id The ID of the comment.
   * @return The comment matching the ID.
   */
  public static Comment getComment(Long id) {
    return Comment.find().byId(id);
  }
}
