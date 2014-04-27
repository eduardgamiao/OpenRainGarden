package models;

import java.io.IOException;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Represents a resource object in the Learn More Page
 * @author Kyle
 *
 */
@Entity
public class Resource extends Model {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String header;
	private String url;
	private String type;
	@Lob
	private byte [] image;
	
	/**
	 * Constructor
	 * @param header Header of the resource
	 * @param url URL of the resource
	 * @param type Type of the resource ('garden', 'barrel', or 'paver')
	 */
	public Resource(String header, String url, String type) {
		this.header = header;
		this.url = url;
		this.type = type;
	}
	
	/**
	 * Returns the id of the resource
	 * @return
	 */
	public Long getID() {
		return this.id;
	}
	
	/**
	 * Sets the id to the given id
	 * @param id
	 */
	public void setID(Long id) {
		this.id = id;
	}
	
	/**
	 * Returns the header of the resource
	 * @return
	 */
	public String getHeader() {
		return this.header;
	}
	
	/**
	 * Sets the header to the given header
	 * @param header
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * Returns the URL of the resource
	 * @return
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * Sets the URL of the resource to the given url
	 * There is no validation of whether the given url is a valid URL
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Sets the type of the resource to either "garden", "barrel", or "paver"
	 * @param type
	 */
	public void setType(String type) {
		if (type.equals("garden") == true) {
			this.type = type;
		}
		else if (type.equals("barrel") == true) {
			this.type = type;
		}
		else if (type.equals("paver") == true) {
			this.type = type;
		}
	}
	
	/**
	 * Returns the type of the resource ("garden", "barrel", or "paver")
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Returns the image
	 * @return
	 */
	public byte [] getImage() {
		return image;
	}
	
	/**
	 * Sets the image to the given image
	 * @param image
	 */
	public void setImage(byte [] image) {
		this.image = image;
	}
	
	/**
	 * Returns true if there is an image, false otherwise
	 * @return
	 */
	public boolean hasPicture() {
		if (this.image == null) {
			return false;
		}
		else {
			return (this.image.length > 0);
		}
	}
	
	/**
	 * Returns the image name, if it exists; otherwise returns the placeholder image
	 * @return
	 * @throws IOException
	 */
	public String getPictureName() throws IOException {
		if (hasPicture() == true) {
			return "images/upload/resource" + this.id;
		}
		return "images/placeholder.gif";
	}
	
	/**
	 * The EBean ORM finder method for database queries on the ID field
	 * @return the finder method for resource
	 */
	public static Finder<Long, Resource> find() {
		return new Finder<Long, Resource>(Long.class, Resource.class);
	}
}
