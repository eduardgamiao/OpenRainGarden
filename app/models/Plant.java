package models;

import java.text.Normalizer;

/**
 * Represents plants used in a rain garden.
 * @author eduardgamiao
 *
 */
public class Plant {

  private Long id;
  private String name;
  private String scientificName;
  private String placement;
  private String growth; 
  private String climateType;
  private byte [] image;

  /**
   * Constructor.
   * @param id ID of plant.
   * @param name Name of plant.
   * @param scientificName Scientific name of plant.
   * @param placement Placement in rain garden.
   * @param growth Growth type.
   * @param climateType Plant climate type. 
   */
  public Plant(Long id, String name, String scientificName, String placement, String growth, String climateType) {
    this.id = id;
    this.name = name;
    this.scientificName = scientificName;
    this.placement = placement;
    this.growth = growth;
    this.climateType = climateType;
  }

  /**
   * @return the id
   */
  public Long getID() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setID(Long id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the scientificName
   */
  public String getScientificName() {
    return scientificName;
  }

  /**
   * @param scientificName the scientificName to set
   */
  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * @return the placement
   */
  public String getPlacement() {
    return placement;
  }

  /**
   * @param placement the placement to set
   */
  public void setPlacement(String placement) {
    this.placement = placement;
  }

  /**
   * @return the growth
   */
  public String getGrowth() {
    return growth;
  }

  /**
   * @param growth the growth to set
   */
  public void setGrowth(String growth) {
    this.growth = growth;
  }

  /**
   * @return the climateType
   */
  public String getClimateType() {
    return climateType;
  }

  /**
   * @param climateType the climateType to set
   */
  public void setClimateType(String climateType) {
    this.climateType = climateType;
  }
  
  /**
   * @return the image
   */
  public byte [] getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(byte [] image) {
    this.image = image;
  }
  
  /**
   * Check if the plant has an image.
   * @return True if the plant has an image, false otherwie.
   */
  public boolean hasPicture() {
    return (this.image != null);
  }

  /**
   * Return the name of the plant without markings/accents. 
   * @return The name of the plant without markings/accents.
   */
  public String getPictureName() {
    return Normalizer.normalize(this.name, Normalizer.Form.NFD).replaceAll("[^A-Za-z0-9]", "").toLowerCase() + ".jpg";
  }
}
