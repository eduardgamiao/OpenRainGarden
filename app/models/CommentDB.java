package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import views.formdata.CommentFormData;

/**
 * Database that stores comments.
 * @author eduardgamiao
 *
 */
public class CommentDB {
  
  private static HashMap<String, List<Comment>> comments = new HashMap<String, List<Comment>>();

  /**
   * Add comment to database.
   * @param formData Comment form data.
   * @param userInfo User posting the comment.
   * @param key Key to store comment under.
   */
  public static void addComment(CommentFormData formData, UserInfo userInfo, String key) {
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    Date date = new Date();
    comments.get(key).add(new Comment(formData.comment, userInfo, dateFormat.format(date)));
  }
  
  /**
   * Get a list of comments from the database that matches the given key.
   * @param key The key of the rainwater runoff solution to get the comments of.
   * @return A list of comments.
   */
  public static List<Comment> getComments(String key) {
    return comments.get(key);
  }
  
  /**
   * Initialize the comment section of a rainwater runoff solution with the given key.
   * @param key The key to store the comments.
   */
  public static void initializeCommentSection(String key) {
    comments.put(key, new ArrayList<Comment>());
  }
}
