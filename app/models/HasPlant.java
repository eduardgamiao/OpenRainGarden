package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * Reprsents the relationship between a rain garden and a plant.
 * @author eduardgamiao
 *
 */
@Entity
public class HasPlant extends Model {
  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  
  // Relationships.
  @OneToMany (mappedBy = "plant")
  RainGarden garden;
  
  @OneToMany (mappedBy = "garden")
  Plant plant;

}
