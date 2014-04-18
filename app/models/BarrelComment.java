package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Class that represents a comment on a rain barrel.
 */
@Entity
public class BarrelComment extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String comment;
  private String datePosted;
  
  @ManyToOne
  private UserInfo poster;
  @ManyToOne
  private RainBarrel barrel;
  
  /**
   * @param comment Message of comment.
   * @param poster Poster of comment.
   * @param barrel The rain barrel being commented on.
   * @param datePosted Date comment was posted.
   */
  public BarrelComment(String comment, UserInfo poster, RainBarrel barrel, String datePosted) {
    this.comment = comment;
    this.poster = poster;
    poster.save();
    this.barrel = barrel;
    barrel.save();
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
   * @return the id
   */
  public Long getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
  /**
   * @return the garden
   */
  public RainBarrel getBarrel() {
    return barrel;
  }
  /**
   * @param barrel the barrel to set
   */
  public void setGarden(RainBarrel barrel) {
    this.barrel = barrel;
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
  
  /**
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for garden comments.
   */
  public static Finder<Long, BarrelComment> find() {
    return new Finder<Long, BarrelComment>(Long.class, BarrelComment.class);
  }
}
