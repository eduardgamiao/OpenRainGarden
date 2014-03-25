import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.h2.engine.Session;
import models.Plant;
import models.PlantDB;
import models.RainGarden;
import models.RainGardenDB;
import play.Application;
import play.GlobalSettings;
import models.UserInfoDB;
import play.*;
import play.mvc.*;
import views.formdata.RainGardenFormData;
import static play.mvc.Results.*;

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
    
    //Add phoney users
    UserInfoDB.addUserInfo("John", "Smith", "johnsmith@gmail.com", "1234567", "pw");
        
    // Add rain garden.
    List<String> plants = new ArrayList<String>();
    plants.add("‘Ahu‘awa");
    plants.add("Kāwelu");
    plants.add("Mau‘u ‘aki ‘aki");
    RainGardenDB.addRainGarden(new RainGardenFormData(0, "John's Rain Garden", "Residential", "564 Though Lane", 
        "No", "My rain garden works and you should get one!", "4", "5", "2014", plants, "25", "200", 
        "Water flows from roof into garden.", "0.75", "2"), UserInfoDB.getUser("johnsmith@gmail.com"));
  }

  /**
   * Termination method for this Play Framework web application.
   * 
   * @param app A Play Framework application.
   */
  public void onStop(Application app) {
    // Clean upload folder. Delete this code once backing database is added.
    try {
      FileUtils.cleanDirectory(new File("public/images/upload"));
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
