import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import models.IndexContent;
import models.IndexContentDB;
import models.Plant;
import models.PlantDB;
import models.RainGardenDB;
import models.Resource;
import models.ResourceDB;
import play.Application;
import play.GlobalSettings;
import models.UserInfoDB;
import views.formdata.RainGardenFormData;

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
    
    //Learn More Resource Database
    ResourceDB.addGardenResource(new Resource("Hui o Ko'olaupoko Rain Garden Program", "hokprogram.jpg", "http://www.huihawaii.org/rain-gardens.html"));
    ResourceDB.addGardenResource(new Resource("Hawaii Rain Garden Manual", "raingardenmanual.jpg", "http://www.huihawaii.org/uploads/1/6/6/3/16632890/raingardenmanual-web-res-smaller.pdf"));
    ResourceDB.addGardenResource(new Resource("Native Plant Care Manual", "nativeplantmanual.jpg", "http://www.huihawaii.org/uploads/1/6/6/3/16632890/plant_foster_parent_handbook_final_draft_for_pdf.pdf"));
    
    ResourceDB.addBarrelResource(new Resource("Honolulu Board of Water Supply Rain Barrel Program", "watersupplyprogram.jpg", "http://www.hbws.org/cssweb/display.cfm?sid=2091"));
    
    ResourceDB.addPaverResource(new Resource("AquaPave", "aquapave.jpg", "http://www.aquapave.com/index.htm"));
    ResourceDB.addPaverResource(new Resource("Futura Stone of Hawaii", "futurastone.jpg", "http://futurastonehawaii.com/"));
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
  private static void populateIndexContentDB() {
	  IndexContent i  = new IndexContent("1","What Harm Can A Little Rainwater Do?" ,"When it rains, the resulting rainwater runoff washes pollutants into Hawaii's streams, rivers, lakes and the ocean. Rainwater runoff solutions allow the resulting runoff to instead be collected, reused or absorbed naturually into the Earth. Share your solutions with your community and inspire your neighbors to be green!", "url","button");
	  IndexContent i1 = new IndexContent("2","What Harm Can A Little Rainwater Do?" ,"When it rains, the resulting rainwater runoff washes pollutants into Hawaii's streams, rivers, lakes and the ocean. Rainwater runoff solutions allow the resulting runoff to instead be collected, reused or absorbed naturually into the Earth. Share your solutions with your community and inspire your neighbors to be green!", "url","button");
	  IndexContent i2 = new IndexContent("3","What Harm Can A Little Rainwater Do?" ,"When it rains, the resulting rainwater runoff washes pollutants into Hawaii's streams, rivers, lakes and the ocean. Rainwater runoff solutions allow the resulting runoff to instead be collected, reused or absorbed naturually into the Earth. Share your solutions with your community and inspire your neighbors to be green!", "url","button");
	  IndexContent i3 = new IndexContent("4","What Harm Can A Little Rainwater Do?" ,"When it rains, the resulting rainwater runoff washes pollutants into Hawaii's streams, rivers, lakes and the ocean. Rainwater runoff solutions allow the resulting runoff to instead be collected, reused or absorbed naturually into the Earth. Share your solutions with your community and inspire your neighbors to be green!", "url","button");
	  
	  IndexContentDB.addPlant(i);
	  IndexContentDB.addPlant(i1);
	  IndexContentDB.addPlant(i2);
	  IndexContentDB.addPlant(i3);
	  
  }
}
