package models;

/**
 * Class that represents a comment on a rainwater runoff solution.
 */
public class Comment {
  
  private String comment;
  private UserInfo poster;
  private String datePosted;
  /**
   * @param comment Message of comment.
   * @param poster Poster of comment.
   * @param datePosted Date comment was posted.
   */
  public Comment(String comment, UserInfo poster, String datePosted) {
    this.comment = comment;
    this.poster = poster;
    this.datePosted = datePosted;
  }
  /**
   * @return the comment
   */
  public String getComment() {
    return comment;
  }
  /**
   * @param comment the comment to set
   */
  public void setComment(String comment) {
    this.comment = comment;
  }
  /**
   * @return the poster
   */
  public UserInfo getPoster() {
    return poster;
  }
  /**
   * @param poster the poster to set
   */
  public void setPoster(UserInfo poster) {
    this.poster = poster;
  }
  /**
   * @return the datePosted
   */
  public String getDatePosted() {
    return datePosted;
  }
  /**
   * @param datePosted the datePosted to set
   */
  public void setDatePosted(String datePosted) {
    this.datePosted = datePosted;
  }
}
