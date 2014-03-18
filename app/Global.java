import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import models.Plant;
import models.PlantDB;
import play.Application;
import play.GlobalSettings;
import play.Logger;

/**
 * Implements a Global object for the Play Framework.
 * 
 * @author eduardgamiao
 * 
 */
public class Global extends GlobalSettings {
  private static final int EXPECTED_PLANT_FILE_LENGTH = 5;
  private static final int THREE = 3;
  private static final int FOUR = 4;

  /**
   * Initialization method for this Play Framework web application.
   * 
   * @param app A Play Framework application.
   */
  public void onStart(Application app) {
    // Populate plant database.
    populatePlantDB();
  }
  
  /**
   * Populate plant database with plants from file.
   */
  private static void populatePlantDB() {
    try {
      BufferedReader br = new BufferedReader(new FileReader("public/csv/plant.txt"));
      String line;
      String [] current;
      while ((line = br.readLine()) != null) {
        current = line.split(", ");
        if (current.length == EXPECTED_PLANT_FILE_LENGTH) {
          PlantDB.addPlant(new Plant(current[0].trim(), current[1].trim(), current[2].trim(), 
              current[THREE].trim(), current[FOUR].trim()));
        }
      }
      br.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
