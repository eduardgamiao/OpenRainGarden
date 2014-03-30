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
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    comments.get(key).add(new Comment(formData.comment, userInfo, dateFormat.format(date)));
  }
  
  /**
   * Initialize the comment section of a rainwater runoff solution with the given key.
   * @param key The key to store the comments.
   */
  public static void initializeCommentSection(String key) {
    comments.put(key, new ArrayList<Comment>());
  }
}
