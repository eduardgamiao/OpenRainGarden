import java.util.ArrayList;
import java.util.List;
import models.Button;
import models.ButtonDB;
import models.CommentDB;
import models.GardenComment;
import models.GardenCommentDB;
import models.HeaderFooterDB;
import models.IndexContent;
import models.IndexContentDB;
import models.PermeablePavers;
import models.PermeablePaversDB;
import models.Plant;
import models.PlantDB;
import models.RainBarrel;
import models.RainBarrelDB;
import models.RainGarden;
import models.RainGardenDB;
import models.Resource;
import models.ResourceDB;
import play.Application;
import play.GlobalSettings;
import models.UserInfoDB;
import views.formdata.CommentFormData;
import views.formdata.GardenCommentFormData;
import views.formdata.PermeablePaversFormData;
import views.formdata.PlantFormData;
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
    if (UserInfoDB.getUsers().isEmpty()) {
      UserInfoDB.addUserInfo("John", "Smith", "johnsmith@gmail.com", "1234567", "pw", false);
      UserInfoDB.addUserInfo("Jane", "Smith", "janesmith@gmail.com", "1234567", "pw", false);
      UserInfoDB.addUserInfo("Admin", "HOK", "admin@gmail.com", "1234567", "pw", true);
    }
        
    // Add rain garden.
    if (RainGarden.find().all().isEmpty()) {
      List<String> plants = new ArrayList<String>();
      plants.add("‘Ahu‘awa");
      RainGardenDB.addRainGarden(new RainGardenFormData("John's Rain Garden", "Residential", 
        "564 Ulahala St.", "No", "My rain garden works and you should get one!", 
        "4", "5", "2014", plants, "100 Square Feet", "1000 Square Feet", "Water flows from roof into garden.", 
        "0.75 inches/hour"), UserInfoDB.getUser("johnsmith@gmail.com"));
    }
    
    RainGarden garden = RainGardenDB.getRainGarden(1);
    
    if (garden != null && GardenComment.find().all().isEmpty()) {
      GardenCommentDB.addComment(new GardenCommentFormData("Wow, you garden looks nice!"), 
          garden,
          UserInfoDB.getUser("janesmith@gmail.com"));
    }
    
    // Add rain barrel.
    if (RainBarrel.find().all().isEmpty()) {
    RainBarrelDB.addRainBarrel(new RainBarrelFormData("John's Rain Barrel", "Residential", "564 Ulahala St.", 
        "No", "My rain garden works and you should get one!", "4", "5", "2014", "Old Drum", "50 Gallons", "Orange-Red",
        "Plastic", "25.00", "Gardening", "Once a year.", "Open", "Home Depot", "Self-Installed"), 
        UserInfoDB.getUser("johnsmith@gmail.com"));
    }
    
    // Add permeable paver.
    if (PermeablePavers.find().all().isEmpty()) {
    PermeablePaversDB.addPermeablePavers(new PermeablePaversFormData(0, "John's Permeable Paver", "Residential", 
        "564 Ulahala St.", "No", "We installed a permeable pavement to replace our aging concrete driveway. The water "
            + "does not pool in front of our driveway anymore.", "4", "5", "2014", "Asphalt", "Concrete", 
            "<200 Square Feet", "Self-Installed"),  UserInfoDB.getUser("johnsmith@gmail.com"));
    }
    
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

  }
  
  /**
   * Populate plant database with plants from file.
   */
  private static void populatePlantDB() {
    String plant1 = "‘Ahu‘awa, Mariscus javanicus, Basin, Sedge, Wet & Dry Climate";
    /**
    String plant2 = "‘Ākia, Wikstroemia uva-ursi, Slope/berm, low shrub, Wet & Dry Climate";
    String plant3 = "‘Ākulikuli, Sessuvium portulacastrum, Inlet, ground cover, Wet & Dry Climate";
    String plant4 = "Carex, Carex wahuensis, Basin, sedge, Wet & Dry Climate";
    String plant5 = "‘Ilie‘e, Plumbago zeylanica, Slope/berm, low shrub, Wet & Dry Climate";
    String plant6 = "‘A‘ali‘i, Dodonaea viscosa, Accent, bush, Dry Climate";
    **/
    String [] plantArr1 = plant1.split(", ");
    /**
    String [] plantArr2 = plant2.split(", ");
    String [] plantArr3 = plant3.split(", ");
    String [] plantArr4 = plant4.split(", ");
    String [] plantArr5 = plant5.split(", ");
    String [] plantArr6 = plant6.split(", ");
    **/
    if (Plant.find().all().isEmpty()) {
    PlantDB.addPlant(new PlantFormData(plantArr1[0], plantArr1[1], plantArr1[2], 
                                   plantArr1[THREE], plantArr1[FOUR]));
    }
    
    /**
    PlantDB.addPlant(new PlantFormData(0, plantArr2[0], plantArr2[1], plantArr2[2], plantArr2[THREE], plantArr2[FOUR]));
    PlantDB.addPlant(new PlantFormData(0, plantArr3[0], plantArr3[1], plantArr3[2], plantArr3[THREE], plantArr3[FOUR]));
    PlantDB.addPlant(new PlantFormData(0, plantArr4[0], plantArr4[1], plantArr4[2], plantArr4[THREE], plantArr4[FOUR]));
    PlantDB.addPlant(new PlantFormData(0, plantArr5[0], plantArr5[1], plantArr5[2], plantArr5[THREE], plantArr5[FOUR]));
    PlantDB.addPlant(new PlantFormData(0, plantArr6[0], plantArr6[1], plantArr6[2], plantArr6[THREE], plantArr6[FOUR]));
    **/
  }
  
  private static void populateIndexContentDB() {
	  HeaderFooterDB.setHeader("Hawaii Rainwater Solutions: Sponsored by Hui o Koolaupoko"); 
	  HeaderFooterDB.setSubHeader("Registry & Gallery");
	  HeaderFooterDB.setFooter("Website sponsered by Hui o Ko'olaupoko and the Hawaii State Department of Health - 2014 Privacy");
	  HeaderFooterDB.setSubFooter("This project has been jointly funded by the U.S. Environmental Protection Agency (EPA) under Section 319(h) of the Clean Water Act, and the Hawaii State Department of Health (HDOH), Clean Water Branch. Although the information in this document has been funded wholly or in part by a Federal Grant to the HDOH, it may not necessarily reflect the views of the EPA and the HDOH and no offical endorsement should be inferred.");
	  HeaderFooterDB.setBannerImage("index_banner.jpg");
	  
	  ButtonDB.addButton(new Button("1", "Sign Up", "/signup"));
	  ButtonDB.addButton(new Button("1", "View Map", "/map"));
	  
	  ButtonDB.addButton(new Button("2", "Learn More", "/learnmore#garden_resources"));
	  ButtonDB.addButton(new Button("2", "View Gallery", "/gallery/rain-garden"));
	  
	  ButtonDB.addButton(new Button("4", "Learn More", "/learnmore#barrel_resources"));
	  ButtonDB.addButton(new Button("4", "View Gallery", "/gallery/rain-barrel"));
	  
	  ButtonDB.addButton(new Button("3", "Learn More", "/learnmore#paver_resources"));
	  ButtonDB.addButton(new Button("3", "View Gallery", "/gallery/permeable-paver"));
	
	  
	  
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
