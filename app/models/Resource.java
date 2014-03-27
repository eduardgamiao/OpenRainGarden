package models;

/**
 * Represents a resource object in the Learn More Page
 * @author Kyle
 *
 */
public class Resource {
	private String header;
	private String imageName;
	private String url;
	
	/**
	 * Constructor
	 * @param header Title of the resource
	 * @param imageName Name of the image file displayed
	 * @param url URL of the resource
	 */
	public Resource(String header, String imageName, String url) {
		this.header = header;
		this.imageName = imageName;
		this.url = url;
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
	 * Returns the name of the image displayed
	 * @return
	 */
	public String getImageName() {
		return this.imageName;
	}
	
	/**
	 * Sets the name of the image displayed to the given imageName
	 * There is no validation of whether the given imageName exists in the image folder
	 * @param imageName
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
}
