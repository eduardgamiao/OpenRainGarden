package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import play.db.ebean.Model;

/**
 * Represents the header object in the index page.
 */
@Entity
public class HeaderFooter extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String header;
  @Lob
  private String subHeader;
  private String footer;
  @Lob
  private String subFooter;
  @Lob
  private byte [] headerImage;
  
  
  
  /**
   * @param header The header.
   * @param subHeader The sub-header.
   * @param footer The footer.
   * @param subFooter The sub-footer.
   */
  public HeaderFooter(String header, String subHeader, String footer, String subFooter) {
    this.header = header;
    this.subHeader = subHeader;
    this.footer = footer;
    this.subFooter = subFooter;
  }

  /**
   * @param header The header.
   * @param subHeader The sub header.
   * @param headerImage Header image.
   */
  public HeaderFooter(String header, String subHeader, byte[] headerImage) {
    this.header = header;
    this.subHeader = subHeader;
    this.headerImage = headerImage;
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
   * @return the header
   */
  public String getHeader() {
    return header;
  }

  /**
   * @param header the header to set
   */
  public void setHeader(String header) {
    this.header = header;
  }

  /**
   * @return the subHeader
   */
  public String getSubHeader() {
    return subHeader;
  }

  /**
   * @param subHeader the subHeader to set
   */
  public void setSubHeader(String subHeader) {
    this.subHeader = subHeader;
  }

  /**
   * @return the headerImage
   */
  public byte[] getHeaderImage() {
    return headerImage;
  }

  /**
   * @param headerImage the headerImage to set
   */
  public void setHeaderImage(byte[] headerImage) {
    this.headerImage = headerImage;
  }
 
  /**
   * @return the footer
   */
  public String getFooter() {
    return footer;
  }

  /**
   * @param footer the footer to set
   */
  public void setFooter(String footer) {
    this.footer = footer;
  }

  /**
   * @return the subFooter
   */
  public String getSubFooter() {
    return subFooter;
  }

  /**
   * @param subFooter the subFooter to set
   */
  public void setSubFooter(String subFooter) {
    this.subFooter = subFooter;
  }

  /**
   * The EBean ORM finder method for database queries on ID.
   * @return The finder method for header.
   */
  public static Finder<Long, HeaderFooter> find() {
    return new Finder<Long, HeaderFooter>(Long.class, HeaderFooter.class);
  }
}
