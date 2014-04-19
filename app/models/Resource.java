package models;

import java.io.IOException;

/**
 * Represents a resource object in the Learn More Page
 * @author Kyle
 *
 */
public class Resource {
	private Long id;
	private String header;
	private String url;
	private byte [] image;
	
	/**
	 * Constructor
	 * @param id ID of the resource
	 * @param header Title of the resource
	 * @param url URL of the resource
	 */
	public Resource(Long id, String header, String url) {
		this.id = id;
		this.header = header;
		this.url = url;
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
}
