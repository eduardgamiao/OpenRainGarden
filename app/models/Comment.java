package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Class that represents a comment on a rainwater runoff solution.
 */
@Entity
public class Comment extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String comment;
  private Date datePosted;
  private String type;
  
  @ManyToOne
  private UserInfo poster;
  
  @ManyToOne
  private RainGarden garden;
  
  @ManyToOne
  private RainBarrel barrel;
  
  @ManyToOne
  private PermeablePavers paver;
  
  /**
   * Comment for a rain garden.
   * @param comment The message.
   * @param garden The rain garden being commented on.
   * @param poster The poster of the comment.
   */
  public Comment(String comment, RainGarden garden, UserInfo poster) {
    this.comment = comment;
    this.garden = garden;
    this.poster = poster;
    this.type = "rg";
    this.datePosted = new Date();
  }
  
  /**
   * Comment for a rain barrel.
   * @param comment The message.
   * @param barrel The rain barrel being commented on.
   * @param poster The poster of the comment.
   */
  public Comment(String comment, RainBarrel barrel, UserInfo poster) {
    this.comment = comment;
    this.barrel = barrel;
    this.poster = poster;
    this.type = "rb";
    this.datePosted = new Date();
  }
  
  /**
   * Comment for a rain garden.
   * @param comment The message.
   * @param paver The Permeable Pavers being commented on.
   * @param poster The poster of the comment.
   */
  public Comment(String comment, PermeablePavers paver, UserInfo poster) {
    this.comment = comment;
    this.paver = paver;
    this.poster = poster;
    this.type = "pp";
    this.datePosted = new Date();
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
  public Date getDatePosted() {
    return datePosted;
  }
  /**
   * @param datePosted the datePosted to set
   */
  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
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
   * @return the barrel
   */
  public RainBarrel getBarrel() {
    return barrel;
  }

  /**
   * @param barrel the barrel to set
   */
  public void setBarrel(RainBarrel barrel) {
    this.barrel = barrel;
  }

  /**
   * @return the paver
   */
  public PermeablePavers getPaver() {
    return paver;
  }

  /**
   * @param paver the paver to set
   */
  public void setPaver(PermeablePavers paver) {
    this.paver = paver;
  }
  
  /**
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for comments.
   */
  public static Finder<Long, Comment> find() {
    return new Finder<Long, Comment>(Long.class, Comment.class);
  }
}
