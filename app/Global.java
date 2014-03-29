import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import models.Button;
import models.ButtonDB;
import models.IndexContent;
import models.IndexContentDB;
import models.PermeablePaversDB;
import models.Plant;
import models.PlantDB;
import models.RainBarrelDB;
import models.RainGardenDB;
import models.Resource;
import models.ResourceDB;
import play.Application;
import play.GlobalSettings;
import models.UserInfoDB;
import views.formdata.PermeablePaversFormData;
import views.formdata.RainBarrelFormData;
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
    populateIndexContentDB();
    //Add phoney users
    UserInfoDB.addUserInfo("John", "Smith", "johnsmith@gmail.com", "1234567", "pw");
        
    // Add rain garden.
    List<String> plants = new ArrayList<String>();
    plants.add("‘Ahu‘awa");
    plants.add("Kāwelu");
    plants.add("Mau‘u ‘aki ‘aki");
    RainGardenDB.addRainGarden(new RainGardenFormData(0, "John's Rain Garden", "Residential", "564 Ulahala St.", 
        "No", "My rain garden works and you should get one!", "4", "5", "2014", plants, "25", "200", 
        "Water flows from roof into garden.", "0.75", "2"), UserInfoDB.getUser("johnsmith@gmail.com"));
    
    // Add rain barrel.
    RainBarrelDB.addRainBarrel(new RainBarrelFormData(0, "John's Rain Garden", "Residential", "564 Ulahala St.", 
        "No", "My rain garden works and you should get one!", "4", "5", "2014", "Old Drum", "50", "Orange-Red",
        "Plastic", "25.00", "Gardening", "Once a year.", "Open", "Home Depot", "Self-Installed", "4+"), 
        UserInfoDB.getUser("johnsmith@gmail.com"));
    
    // Add permeable paver.
    PermeablePaversDB.addPermeablePavers(new PermeablePaversFormData(0, "John's Rain Garden", "Residential", 
        "564 Ulahala St.", "No", "We installed a permeable pavement to replace our aging concrete driveway. The water "
            + "does not pool in front of our driveway anymore.", "4", "5", "2014", "Asphalt", "Concrete", "250",
        "Self-Installed"),  UserInfoDB.getUser("johnsmith@gmail.com"));
    
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
	  
	  
	  ButtonDB.addButton(new Button("1", "register", "/signup"));
	  ButtonDB.addButton(new Button("1", "View Gallery", "/gallery"));
	  ButtonDB.addButton(new Button("1", "View Map", "/map"));
	  
	  ButtonDB.addButton(new Button("2", "Learn More", "/learnmore"));
	  ButtonDB.addButton(new Button("2", "View Gallery", "/register"));
	  
	  ButtonDB.addButton(new Button("3", "Learn More", "/learnmore"));
	  ButtonDB.addButton(new Button("3", "View Gallery", "/register"));
	  
	  ButtonDB.addButton(new Button("4", "Learn More", "/learnmore"));
	  ButtonDB.addButton(new Button("4", "View Gallery", "/register"));
	
	  
	  
	  IndexContent i  = new IndexContent("1","What Harm Can A Little Rainwater Do?" ,"When it rains, the resulting rainwater runoff washes pollutants into Hawaii's streams, rivers, lakes and the ocean. Rainwater runoff solutions allow the resulting runoff to instead be collected, reused or absorbed naturually into the Earth. Share your solutions with your community and inspire your neighbors to be green!", "alawai.png",ButtonDB.getButtons());
	  IndexContent i1 = new IndexContent("2","Solution: Rain Garden" ,"A Rain Garden is a low-lying area populated with native plants. They reduce the amount of water diverted from roofs/driveways/parking lots into storm drains by absorbing and filtering the water.", "garden.png",ButtonDB.getButtons());
	  IndexContent i2 = new IndexContent("3","Solution: Permeable Paver" ,"A permeable interlocking concrete pavement is comprised of a layer of permeable pavers separated by joints filled with small stones. The stones in the joints provide 100% surface permeability while the stone base effectively filters stormwater and reduces pollutants and debris that would otherwise be washed into streams and rivers.", "paver.png",ButtonDB.getButtons());
	  IndexContent i3 = new IndexContent("4","Solution: Rain Barrel" ,"During the summer months it is estimated that nearly 40 percent of household water is used for lawn and garden maintenance. A rain barrel collects water and stores it for those times that you need it most --- during the dry summer months. Using rain barrels potentially helps homeowners lower water bills, while also improving the vitality of plants, flowers, trees, and lawns.", "barrel.png",ButtonDB.getButtons());
	 
	  IndexContentDB.addBlock(i);
	  IndexContentDB.addBlock(i1);
	  IndexContentDB.addBlock(i2);
	  IndexContentDB.addBlock(i3);
	  
  }
}
