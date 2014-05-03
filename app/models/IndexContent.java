package models;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import play.db.ebean.Model;

/**
 * Represents content on the index page.
 */
@Entity
public class IndexContent extends Model {
	private static final long serialVersionUID = 1L;
	  
	@Id
	private Long id;
	private String title;
	@Lob
	private String content;
	private String firstButtonText;
	private String firstButtonURL;
	private String secondButtonText;
	private String secondButtonURL;
	@Lob
	private byte [] image;
	private String externalImageURL;

	/**
	 * Constructor.
   * @param title Title of the content block.
   * @param content Text content.
   * @param firstButtonText Text of first button.
   * @param firstButtonURL URL for first button.
   * @param secondButtonText Text for second button.
   * @param secondButtonURL URL for second button.
   */
  public IndexContent(String title, String content, String firstButtonText, String firstButtonURL,
      String secondButtonText, String secondButtonURL) {
    this.title = title;
    this.content = content;
    this.firstButtonText = firstButtonText;
    this.firstButtonURL = firstButtonURL;
    this.secondButtonText = secondButtonText;
    this.secondButtonURL = secondButtonURL;
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
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }



  /**
   * @return the firstButtonText
   */
  public String getFirstButtonText() {
    return firstButtonText;
  }

  /**
   * @param firstButtonText the firstButtonText to set
   */
  public void setFirstButtonText(String firstButtonText) {
    this.firstButtonText = firstButtonText;
  }

  /**
   * @return the firstButtonURL
   */
  public String getFirstButtonURL() {
    return firstButtonURL;
  }

  /**
   * @param firstButtonURL the firstButtonURL to set
   */
  public void setFirstButtonURL(String firstButtonURL) {
    this.firstButtonURL = firstButtonURL;
  }

  /**
   * @return the secondButtonText
   */
  public String getSecondButtonText() {
    return secondButtonText;
  }

  /**
   * @param secondButtonText the secondButtonText to set
   */
  public void setSecondButtonText(String secondButtonText) {
    this.secondButtonText = secondButtonText;
  }

  /**
   * @return the secondButtonURL
   */
  public String getSecondButtonURL() {
    return secondButtonURL;
  }

  /**
   * @param secondButtonURL the secondButtonURL to set
   */
  public void setSecondButtonURL(String secondButtonURL) {
    this.secondButtonURL = secondButtonURL;
  }

  /**
   * @return the image
   */
  public byte[] getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(byte[] image) {
    this.image = image;
  }
  
  /**
   * @return the externalImageURL
   */
  public String getExternalImageURL() {
    return externalImageURL;
  }

  /**
   * @param externalImageURL the externalImageURL to set
   */
  public void setExternalImageURL(String externalImageURL) {
    this.externalImageURL = externalImageURL;
  }

  /**
   * Check if a first button exists.
   * @return True if the button exists, false otherwise.
   */
  public boolean hasFirstButton() {
    return (this.firstButtonText != null && !this.firstButtonText.isEmpty()) 
            && (this.firstButtonURL != null && !this.firstButtonURL.isEmpty());
  }
  
  /**
   * Check if a second button exists.
   * @return True if the button exists, false otherwise.
   */
  public boolean hasSecondButton() {
    return (this.secondButtonText != null && !this.secondButtonText.isEmpty()) 
            && (this.secondButtonURL != null && !this.secondButtonURL.isEmpty());
  }

  /**
   * Check if an IndexContent has a picture.
   * @return True if the IndexContent has a picture, otherwise false.
   */
  public boolean hasPicture() {
    return (this.image != null);
  }

  /**
	 * The EBean ORM finder method for database queries on ID.
	 * @return The finder method for index contents.
	 */
	public static Finder<Long, IndexContent> find() {
	    return new Finder<Long, IndexContent>(Long.class, IndexContent.class);
	}
}
