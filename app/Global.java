import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Button;
import models.ButtonDB;
import models.Comment;
import models.CommentDB;
import models.GardenComment;
import models.GardenCommentDB;
import models.HeaderFooter;
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
import play.Logger;
import play.Play;
import play.libs.Yaml;
import models.UserInfoDB;
import views.formdata.ButtonFormData;
import views.formdata.CommentFormData;
import views.formdata.GardenCommentFormData;
import views.formdata.HeaderFooterFormData;
import views.formdata.IndexContentBlockFormData;
import views.formdata.PermeablePaversFormData;
import views.formdata.PlantFormData;
import views.formdata.RainBarrelFormData;
import views.formdata.RainGardenFormData;
import views.formdata.ResourceFormData;
import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Ebean;
import controllers.routes;

/**
 * Implements a Global object for the Play Framework.
 * 
 * @author eduardgamiao
 * 
 */
public class Global extends GlobalSettings {
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
      UserInfoDB.addUserInfo("John", "Smith", "johnsmith@gmail.com", "1234567", BCrypt.hashpw("pw", BCrypt.gensalt()), false);
      UserInfoDB.addUserInfo("Jane", "Smith", "janesmith@gmail.com", "1234567", BCrypt.hashpw("pw", BCrypt.gensalt()), false);
      //String admin_email = System.getenv("MAIL_USERNAME");
      //String admin_pw = System.getenv("MAIL_PASSWORD");
      String admin_email = Play.application().configuration().getString("admin_email");
      String admin_pw = Play.application().configuration().getString("admin_pw");
      Logger.debug(admin_email + " " + admin_pw);
      if (admin_email != null && admin_pw != null) {
    	  System.out.println("Creating admin account");
    	  UserInfoDB.addUserInfo("Admin", "HOK", admin_email, "1234567", BCrypt.hashpw(admin_pw, BCrypt.gensalt()), true, true);
      }
    }
        
    // Add rain garden.
    if (RainGarden.find().all().isEmpty()) {
      List<String> plants = new ArrayList<String>();
      plants.add("'Ahu'awa");
      plants.add("'Ae'ae");
      plants.add("'A'ali'i");
      RainGarden garden = RainGardenDB.addRainGarden(new RainGardenFormData("John's Rain Garden", "Residential", 
        "564 Ulahala St.", "No", "We installed our rain garden because we live in an area prone to daily rainfall. "
            + " We used to have a problem with the water rolling off of our roof and ponding in our yard. Once we "
            + "installed the rain garden, the water flows right through our yard and we don't have to worry about "
            + "our yard when it rains.", 
        "4", "5", "2014", plants, "100 Square Feet", "1000 Square Feet", "The primary source of water comes from our "
            + "roof. The water is funneled through downspouts which all lead to our rain garden.", 
        "0.75 inches/hour"), UserInfoDB.getUser("johnsmith@gmail.com"));
      
      garden.setApproved(true);
      garden.setExternalImageURL(routes.Assets.at("images/garden-1.jpg").url());
      garden.save();
      
      if (garden != null && Comment.find().all().isEmpty()) {
        CommentDB.addComment(new CommentFormData("Wow, you garden looks nice!"), 
            garden,
            UserInfoDB.getUser("janesmith@gmail.com"));
      }
    }
    
    // Add rain barrel.
    if (RainBarrel.find().all().isEmpty()) {
    RainBarrel barrel = RainBarrelDB.addRainBarrel(new RainBarrelFormData("John's Rain Barrel", "Residential", 
        "564 Ulahala St.", "No", "We installed a set of rain barrels on the side of our house because we wanted to "
            + "protect the plants from excessive flooding. The water is collected from the gutters on the roof and flow"
            + " into these barrels. The collected water is then used for gardening. It helps cutdown our water bill!", 
            "4", "5", "2014", "Old Drum", "50 Gallons", "Blue", "Plastic", "25.00", "Gardening", "Once a year.", 
            "Covered", "Home Depot", "Self-Installed"), 
        UserInfoDB.getUser("johnsmith@gmail.com"));
    barrel.setApproved(true);
    barrel.setExternalImageURL(routes.Assets.at("images/barrel-1.jpg").url());
    barrel.save();
    }
    
    // Add permeable paver.
    if (PermeablePavers.find().all().isEmpty()) {
    PermeablePavers paver = PermeablePaversDB.addPermeablePavers(new PermeablePaversFormData("John's Permeable Paver", 
        "Residential", "564 Ulahala St.", "No", "Whenever it rains, our driveway becomes a mini river. The rain would "
            + "just pool in our driveway. After we installed our permeable paver, we noticed a drastic decrease in "
            + "pooling and the lawn around our driveway looks more green.", "4", "5", "2014", "Asphalt", "Concrete", 
            "<200 Square Feet", "Self-Installed"),  UserInfoDB.getUser("johnsmith@gmail.com"));
    paver.setApproved(true);
    paver.setExternalImageURL(routes.Assets.at("images/paver-1.jpg").url());
    paver.save();
    }
    
    if (Resource.find().all().isEmpty()) {
    	//Learn More Resource Database
        ResourceDB.addGardenResource(new ResourceFormData(-1, "Hui o Ko'olaupoko Rain Garden Program", "http://www.huihawaii.org/rain-gardens.html"));
        ResourceDB.addGardenResource(new ResourceFormData(-1, "Hawaii Rain Garden Manual", "http://www.huihawaii.org/uploads/1/6/6/3/16632890/raingardenmanual-web-res-smaller.pdf"));
        ResourceDB.addGardenResource(new ResourceFormData(-1, "Native Plant Care Manual", "http://www.huihawaii.org/uploads/1/6/6/3/16632890/plant_foster_parent_handbook_final_draft_for_pdf.pdf"));
        
        ResourceDB.addBarrelResource(new ResourceFormData(-1, "Honolulu Board of Water Supply Rain Barrel Program", "http://www.hbws.org/cssweb/display.cfm?sid=2091"));
        
        ResourceDB.addPaverResource(new ResourceFormData(-1, "AquaPave", "http://www.aquapave.com/index.htm"));
        ResourceDB.addPaverResource(new ResourceFormData(-1, "Futura Stone of Hawaii", "http://futurastonehawaii.com/"));
    }
    
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
    if (Plant.find().all().isEmpty()) {
      @SuppressWarnings("unchecked")
      Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
      Ebean.save(all.get("plants"));
    }
  }
  
  private static void populateIndexContentDB() {
    if (HeaderFooterDB.isEmpty()) {
	  HeaderFooterDB.add(new HeaderFooterFormData("Hawaii Rainwater Solutions: Sponsored by Hui o Koolaupoko",  
	      "Registry & Gallery", "Website sponsered by Hui o Ko'olaupoko and the Hawaii State Department of Health"
	          + " - 2014 Privacy", "This project has been jointly funded by the U.S. Environmental Protection "
	              + "Agency (EPA) under Section 319(h) of the Clean Water Act, and the Hawaii State Department of "
	              + "Health (HDOH), Clean Water Branch. Although the information in this document has been funded "
	              + "wholly or in part by a Federal Grant to the HDOH, it may not necessarily reflect the views of "
	              + "the EPA and the HDOH and no offical endorsement should be inferred."));
    }
    if (ButtonDB.isEmpty()) {  
		  ButtonDB.add(new ButtonFormData("1", "Sign Up", "/signup"));
		  ButtonDB.add(new ButtonFormData("1", "View Map", "/map"));
		  
		  ButtonDB.add(new ButtonFormData("2", "Learn More", "/learnmore#garden_resources"));
		  ButtonDB.add(new ButtonFormData("2", "View Gallery", "/gallery/rain-garden"));
		  
		  ButtonDB.add(new ButtonFormData("3", "Learn More", "/learnmore#paver_resources"));
		  ButtonDB.add(new ButtonFormData("3", "View Gallery", "/gallery/permeable-paver"));
		  
		  ButtonDB.add(new ButtonFormData("4", "Learn More", "/learnmore#barrel_resources"));
		  ButtonDB.add(new ButtonFormData("4", "View Gallery", "/gallery/rain-barrel"));
    }
	  
	if(IndexContentDB.isEmpty()){  
		  IndexContentDB.addBlock(new IndexContentBlockFormData("1","What Harm Can A Little Rainwater Do?" ,"When it rains, the resulting rainwater runoff washes pollutants into Hawaii's streams, rivers, lakes and the ocean. Rainwater runoff solutions allow the resulting runoff to instead be collected, reused or absorbed naturually into the Earth. Share your solutions with your community and inspire your neighbors to be green!", "alawai.png"));
		  IndexContentDB.addBlock(new IndexContentBlockFormData("2","Solution: Rain Garden" ,"A Rain Garden is a low-lying area populated with native plants. They reduce the amount of water diverted from roofs/driveways/parking lots into storm drains by absorbing and filtering the water.", "garden.png"));
		  IndexContentDB.addBlock(new IndexContentBlockFormData("3","Solution: Permeable Paver" ,"A permeable interlocking concrete pavement is comprised of a layer of permeable pavers separated by joints filled with small stones. The stones in the joints provide 100% surface permeability while the stone base effectively filters stormwater and reduces pollutants and debris that would otherwise be washed into streams and rivers.", "paver.png"));
		  IndexContentDB.addBlock(new IndexContentBlockFormData("4","Solution: Rain Barrel" ,"During the summer months it is estimated that nearly 40 percent of household water is used for lawn and garden maintenance. A rain barrel collects water and stores it for those times that you need it most --- during the dry summer months. Using rain barrels potentially helps homeowners lower water bills, while also improving the vitality of plants, flowers, trees, and lawns.", "barrel.png"));
	}
  }//
  
 
}
