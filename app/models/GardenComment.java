package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.Logger;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Class that represents a comment on a rain garden.
 */
@Entity
public class GardenComment extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String comment;
  private String datePosted;
  
  @ManyToOne
  private UserInfo poster;
  @ManyToOne
  private RainGarden garden;
  
  /**
   * @param comment Message of comment.
   * @param datePosted Date comment was posted.
   */
  public GardenComment(String comment, String datePosted) {
    this.comment = comment;
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
  public RainGarden getGarden() {
    return garden;
  }
  /**
   * @param garden the garden to set
   */
  public void setGarden(RainGarden garden) {
    this.garden = garden;
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
  public static Finder<Long, GardenComment> find() {
    return new Finder<Long, GardenComment>(Long.class, GardenComment.class);
  }
}
