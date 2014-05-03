package views.formdata;

import java.util.List;
import java.util.ArrayList;
import models.IndexContent;
import play.data.validation.ValidationError;

/**
 * Backing class for IndexContentBlock form.
 * @author Vinson Gao
 *
 */
public class IndexContentFormData {
	
  /** Index content ID. */
  public long id = -1;
  
  /** Title of block. */
  public String title;
  
  /** Content of block. */
  public String content;
  
  /** Text for first button. */
  public String firstButtonText;
  
  /** URL for first button. */
  public String firstButtonURL;
  
  /** Text for second button. */
  public String secondButtonText;
  
  /** URL for second button. */
  public String secondButtonURL;
  
  /** External URL for an image. */
  public String externalImageURL;
	
	
	/**
	 * Constructor.
	 */
	public IndexContentFormData() {
	  
	}

	/**
	 * Constructor.
   * @param title Title of the content block.
   * @param content Text content.
   * @param firstButtonText Text of first button.
   * @param firstButtonURL URL for first button.
   * @param secondButtonText Text for second button.
   * @param secondButtonURL URL for second button.
   */
  public IndexContentFormData(String title, String content, String firstButtonText, String firstButtonURL,
      String secondButtonText, String secondButtonURL) {
    this.title = title;
    this.content = content;
    this.firstButtonText = firstButtonText;
    this.firstButtonURL = firstButtonURL;
    this.secondButtonText = secondButtonText;
    this.secondButtonURL = secondButtonURL;
  }
  
  /**
   * Constructor.
   * @param indexContent An IndexContent object.
   */
  public IndexContentFormData(IndexContent indexContent) {
    this.id = indexContent.getId();
    this.title = indexContent.getTitle();
    this.content = indexContent.getContent();
    this.firstButtonText = indexContent.getFirstButtonText();
    this.firstButtonURL = indexContent.getFirstButtonURL();
    this.secondButtonText = indexContent.getSecondButtonText();
    this.secondButtonURL = indexContent.getSecondButtonURL();
  }

  

  /**
	 * Validation for Sign Up Form.
	 * @return A list of errors (empty list if form is valid)
	 */
	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (this.title == null || this.title.isEmpty()) {
		  errors.add(new ValidationError("title", "Title is required."));
		}
		if (this.content == null || this.content.isEmpty()) {
      errors.add(new ValidationError("content", "Content is required."));
    }
		if ((this.firstButtonText == null || this.firstButtonText.isEmpty() 
		     && !(this.firstButtonURL == null || this.firstButtonURL.isEmpty()))) {
		  errors.add(new ValidationError("firstButtonText", "Missing text for button."));
		}
    if ((this.secondButtonText == null || this.secondButtonText.isEmpty() 
         && !(this.secondButtonURL == null || this.secondButtonURL.isEmpty()))) {
      errors.add(new ValidationError("secondButtonText", "Missing text for button."));
    }
    if ((this.firstButtonURL == null || this.firstButtonURL.isEmpty() 
         && !(this.firstButtonText == null || this.firstButtonText.isEmpty()))) {
      errors.add(new ValidationError("firstButtonURL", "Missing URL for button."));
    }
    if ((this.secondButtonURL == null || this.secondButtonURL.isEmpty() 
        && !(this.secondButtonText == null || this.secondButtonText.isEmpty()))) {
     errors.add(new ValidationError("secondButtonURL", "Missing URL for button."));
   }
		
		return errors.isEmpty() ? null : errors;
	}
}
