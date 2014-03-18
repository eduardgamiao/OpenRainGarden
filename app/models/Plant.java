package models;

/**
 * Represents plants used in a rain garden.
 * @author eduardgamiao
 *
 */
public class Plant {
  
  private String name;
  private String scientificName;
  private String placement;
  private String growth;
  private String climateType;

  /**
   * Constructor.
   * @param name Name of plant.
   * @param scientificName Scienctific name of plant.
   * @param placement Placement in rain garden.
   * @param growth Growth type.
   * @param climateType Plant climate type. 
   */
  public Plant(String name, String scientificName, String placement, String growth, String climateType) {
    this.name = name;
    this.scientificName = scientificName;
    this.placement = placement;
    this.growth = growth;
    this.climateType = climateType;
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
  
  
}
