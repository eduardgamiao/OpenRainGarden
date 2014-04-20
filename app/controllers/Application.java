package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.protocol.HTTP;
import com.google.common.io.Files;
import com.ning.http.client.Body;
import models.BarrelCommentDB;
import models.Comment;
import models.CommentDB;
import models.GardenCommentDB;
import models.HeaderFooterDB;
import models.IndexContentDB;
import models.PaverCommentDB;
import models.PermeablePavers;
import models.PermeablePaversDB;
import models.Plant;
import models.PlantDB;
import models.RainBarrel;
import models.RainBarrelDB;
import models.RainGarden;
import models.RainGardenDB;
import models.UserInfo;
import models.UserInfoDB;
import models.Resource;
import models.ResourceDB;
import play.Logger;
import play.api.Play;
import play.api.mvc.SimpleResult;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Results;
import play.mvc.Security;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.formdata.BarrelCommentFormData;
import views.formdata.CommentFormData;
import views.formdata.CoverTypes;
import views.formdata.DateTypes;
import views.formdata.GardenCommentFormData;
import views.formdata.InfiltrationRateTypes;
import views.formdata.InstallationTypes;
import views.formdata.MaterialTypes;
import views.formdata.PaverCommentFormData;
import views.formdata.PaverMaterialTypes;
import views.formdata.PermeablePaversFormData;
import views.formdata.PermeablePaversSizeTypes;
import views.formdata.PlantFormData;
import views.formdata.PlantFormDropdownTypes;
import views.formdata.RainBarrelCapacityTypes;
import views.formdata.RainBarrelFormData;
import views.formdata.RainBarrelTypes;
import views.formdata.RainGardenSizeTypes;
import views.formdata.WaterSourceSizeTypes;
import views.formdata.WaterUsageTypes;
import views.formdata.YesNoChoiceType;
import views.formdata.PlantTypes;
import views.formdata.PropertyTypes;
import views.formdata.RainGardenFormData;
import views.formdata.LoginFormData;
import views.formdata.SignUpFormData;
import views.formdata.IndexContentFormData;
import views.html.Index;
import views.html.Page1;
import views.html.RegisterRainGarden;
import views.html.Login;
import views.html.ViewGarden;
import views.html.ViewBarrel;
import views.html.SignUp;
import views.html.RegisterMenu;
import views.html.RegisterRainBarrel;
import views.html.LearnMore;
import views.html.RegisterPermeablePavers;
import views.html.ViewPaver;
import views.html.RainGardenGallery;
import views.html.RainBarrelGallery;
import views.html.PermeablePaverGallery;
import views.html.MapPage;
import views.html.Profile;
import views.html.EditProfile;
import views.html.ErrorReport;
import views.html.AdminPanel;
import views.html.EditIndexContent;
import views.html.RegisterPlant;
import views.html.ViewPlant;
import views.html.EditResource;
import views.html.NewResource;


/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {
  private static final long MAX_FILE_SIZE = 2621440;

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result errorReport(String s){    
	  	return ok(ErrorReport.render(s));	  
  }
  
  public static Result index() {
    return ok(Index.render( IndexContentDB.getBlocks(), 
				    		HeaderFooterDB.getHeader(),
				    		HeaderFooterDB.getSubHeader(),
				    		HeaderFooterDB.getFooter(),
				    		HeaderFooterDB.getSubFooter(),
				    		HeaderFooterDB.getBannerImage()
				    		));
  }
  /**
   * Returns the user profile page. 
   * @return The resulting user profile page. 
   */
  public static Result profile(String email) {
	
	UserInfo user = UserInfoDB.getUser(email);
	System.out.println("Opening Profile Page");

	return ok(Profile.render(user));

  }
  
  /**
   * Returns the edit profile page.
   * @return edit profile page
   */
  public static Result editProfile() {
	System.out.println("Opening Edit Profile Page");
    SignUpFormData data = (!Secured.isLoggedIn(ctx())) 
        ? new SignUpFormData() : new SignUpFormData(UserInfoDB.getUser(Secured.getUserInfo(ctx()).getEmail()));
	  Form<SignUpFormData> formData = Form.form(SignUpFormData.class).fill(data);
	  if(Secured.isLoggedIn(ctx())){
		  return ok(EditProfile.render(formData,  UserInfoDB.getUser(Secured.getUser(ctx()))));
	  }
	  return redirect(routes.Application.errorReport("No user logged in,open edit profile fails."));
  }
  /**
   * Processes the edited profile form.
   * @return
   */
  public static Result postEditProfile() {
	  System.out.println("Post Edit");
	  
	  if(Secured.isLoggedIn(ctx())){
		 
		  Form<SignUpFormData> formData = Form.form(SignUpFormData.class).bindFromRequest();
		  if (formData.hasErrors() == true) {
			  System.out.println("Edit profile Errors found.");
			  return badRequest(EditProfile.render(formData,  UserInfoDB.getUser(Secured.getUser(ctx()))));
		  }
		  else {
			  SignUpFormData data = formData.get();
			  System.out.println(data.firstName + " " + data.lastName + " " + data.email + " " + data.telephone + " " + data.password);
			  
			  UserInfo user = Secured.getUserInfo(ctx());
			  user.setFirstName(data.firstName);
			  user.setLastName(data.lastName);
			  user.setEmail(data.email);
			  user.setTelephone(data.telephone);
			  user.setPassword(data.password);
			  
			  return redirect(routes.Application.profile(data.email));
		  }
	  }
	  return redirect(routes.Application.errorReport("No user logged in, post edit profile fails."));
  }
  /**
   * Open the admin control panel.
   * @return
   */
  @Security.Authenticated(Secured.class)
  public static Result adminPanel() {
	    /*IndexContentFormData data = (!Secured.isLoggedIn(ctx())) 
	        ? new IndexContentFormData() : new IndexContentFormData();
		  Form<IndexContentFormData> formData = Form.form(IndexContentFormData.class).fill(data);
		  if(Secured.isLoggedIn(ctx())){
			  if(Secured.getUserInfo(ctx()).isAdmin()){
				  return ok(EditIndexContent.render(formData,  UserInfoDB.getUser(Secured.getUser(ctx()))));
			  }
		  }
		  return redirect(routes.Application.errorReport("No admin logged in,open admin control panel fails."));*/
	  
	  
	  if (Secured.getUserInfo(ctx()).isAdmin() == true) {
		  return ok(AdminPanel.render("admin"));
	  }
	  return redirect(routes.Application.index());
  }
  
 
   /**
   * Open the admin control panel.
   * @return
   */
  @Security.Authenticated(Secured.class)
  public static Result editIndexContentFormData() {
		System.out.println("Opening admin control Page");
	    IndexContentFormData data = (!Secured.isLoggedIn(ctx())) 
	        ? new IndexContentFormData() : new IndexContentFormData();
		  Form<IndexContentFormData> formData = Form.form(IndexContentFormData.class).fill(data);
		  if(Secured.isLoggedIn(ctx())){
			  if(Secured.getUserInfo(ctx()).isAdmin()){
				  return ok(EditIndexContent.render(formData,  UserInfoDB.getUser(Secured.getUser(ctx()))));
			  }
		  }
		  return redirect(routes.Application.errorReport("No admin logged in,open admin control panel fails."));
	  }
  
  /**
   * Processes the edited IndexContentFormData .
   * @return
   */
  @Security.Authenticated(Secured.class)
  public static Result postIndexContentFormData() throws IOException {
	  System.out.println("Post Edit");
	  
	  if(Secured.isLoggedIn(ctx())){
		  if(Secured.getUserInfo(ctx()).isAdmin()){
			  Form<IndexContentFormData> formData = Form.form(IndexContentFormData.class).bindFromRequest();
			  if (formData.hasErrors() == true) {
				  System.out.println("Edit profile Errors found.");
				  return badRequest(EditIndexContent.render(formData,  UserInfoDB.getUser(Secured.getUser(ctx()))));
			  }
			  else {
				  IndexContentFormData data = formData.get();
				 // System.out.println(data.firstName + " " + data.lastName + " " + data.email + " " + data.telephone + " " + data.password);
				  
				  //create new userinfo and add it to the "database"
				  HeaderFooterDB.setHeader(data.header);
				  HeaderFooterDB.setSubHeader(data.subheader);
				  HeaderFooterDB.setFooter(data.footer);
				  HeaderFooterDB.setSubFooter(data.subfooter);
				  HeaderFooterDB.setBannerImage(data.bannerImageUrl);
				  
				  MultipartFormData body = request().body().asMultipartFormData();
			      FilePart picture = body.getFile("uploadFile");
			      if (picture != null) {
			        HeaderFooterDB.setImage(Files.toByteArray(picture.getFile()));
			      }
				  
				  
				  return redirect(routes.Application.editIndexContentFormData());
			  }
		  }
	  }
	  return redirect(routes.Application.errorReport("No admin logged in, post index content form data fails."));
  }
  /**
   * Returns the registration navigation menu.
   * @return The registration navigation menu page.
   */
  public static Result registerMenu() {
    return ok(RegisterMenu.render("Registration - Main"));
  }
  
  /**
   * Register new rain garden.
   * @return The rain garden registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result newRainGarden() {
    RainGardenFormData data = new RainGardenFormData();
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).fill(data);
    return ok(RegisterRainGarden.render(formData, true, YesNoChoiceType.getChoiceList(), 
        PropertyTypes.getTypes(), DateTypes.getMonthTypes(), 
        DateTypes.getDayTypes(), DateTypes.getYearTypes(), 
        PlantTypes.getPlantMap(), RainGardenSizeTypes.getTypes(), 
        WaterSourceSizeTypes.getTypes(), 
        InfiltrationRateTypes.getTypes(), Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @param isNew Specifies if the entry being processed is new or not.
   * @return The resulting rain garden page if information was valid, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postRainGardenRegister(boolean isNew) throws IOException {
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).bindFromRequest();
    validateGardenUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      List<String> plantList = new ArrayList<String>();
      for (String key : dataMap.keySet()) {
        if (key.contains("plants")) {
          plantList.add(dataMap.get(key));
        }
      }          
      return badRequest(RegisterRainGarden.render(formData, isNew, YesNoChoiceType.getChoiceList(), 
                        PropertyTypes.getTypes(dataMap.get("propertyType")), 
                        DateTypes.getMonthTypes(dataMap.get("month")), 
                        DateTypes.getDayTypes(dataMap.get("day")), 
                        DateTypes.getYearTypes(dataMap.get("year")),
                        PlantTypes.getPlantMap(plantList),
                        RainGardenSizeTypes.getTypes(dataMap.get("rainGardenSize")),
                        WaterSourceSizeTypes.getTypes(dataMap.get("waterFlowSourceSize")),
                        InfiltrationRateTypes.getTypes(dataMap.get("infiltrationRate")), 
                        Secured.getUserInfo(ctx())));   
    } 
    else {
      RainGardenFormData data = formData.get();
      Logger.debug("" + data.id);
      RainGarden garden = RainGardenDB.addRainGarden(data, Secured.getUserInfo(ctx()));    
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          garden.setImage(Files.toByteArray(source));
      }
      return redirect(routes.Application.viewGarden(garden.getID()));
     }     
    }
  
  /**
   * Manage information for a rain garden.
   * @param id The ID of the rain garden to manage.
   * @return The rain garden edit form.
   */
  @Security.Authenticated(Secured.class)
  public static Result manageRainGarden(long id) {
    if (RainGardenDB.hasID(id)) {      
      if (Secured.isLoggedIn(ctx()) 
          && (Secured.getUserInfo(ctx()).getId() == RainGardenDB.getRainGarden(id).getOwner().getId())) {
        RainGardenFormData data = new RainGardenFormData(RainGardenDB.getRainGarden(id));
        Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).fill(data);
        return ok(RegisterRainGarden.render(formData, false, YesNoChoiceType.getChoiceList(), 
                  PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
                  DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
                  PlantTypes.getPlantMap(data.plants), RainGardenSizeTypes.getTypes(data.rainGardenSize), 
                  WaterSourceSizeTypes.getTypes(data.waterFlowSourceSize), 
                  InfiltrationRateTypes.getTypes(data.infiltrationRate), Secured.getUserInfo(ctx())));        
      }
    }
    return redirect(routes.Application.index());
  }
    
  /**
   * Register a new rain barrel.
   * @return The rain barrel registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result newRainBarrel() {
    RainBarrelFormData data = new RainBarrelFormData();
    Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).fill(data);    
    return ok(RegisterRainBarrel.render(formData, true, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(), DateTypes.getMonthTypes(), 
              DateTypes.getDayTypes(), DateTypes.getYearTypes(), 
              RainBarrelTypes.getRainBarrelTypes(), MaterialTypes.getMaterialTypes(),
              WaterUsageTypes.getWaterUsageTypes(), CoverTypes.getCoverTypes(), 
              InstallationTypes.getInstallationTypes(),
              RainBarrelCapacityTypes.getTypes()));
  }
  
  /**
   * Returns the created/edited rain barrel page.
   * @param isNew Specifies if the entry is a new rain barrel.
   * @return The resulting rain barrel page if information was valid, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postRainBarrelRegister(boolean isNew) throws IOException {
    Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).bindFromRequest();
    validateBarrelUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      return badRequest(RegisterRainBarrel.render(formData, isNew, YesNoChoiceType.getChoiceList(), 
                        PropertyTypes.getTypes(dataMap.get("propertyType")), 
                        DateTypes.getMonthTypes(dataMap.get("month")), 
                        DateTypes.getDayTypes(dataMap.get("day")), 
                        DateTypes.getYearTypes(dataMap.get("year")),
                        RainBarrelTypes.getRainBarrelTypes(dataMap.get("rainBarrelType")),
                        MaterialTypes.getMaterialTypes(dataMap.get("material")),
                        WaterUsageTypes.getWaterUsageTypes(dataMap.get("waterUse")),
                        CoverTypes.getCoverTypes(dataMap.get("cover")),
                        InstallationTypes.getInstallationTypes(dataMap.get("installationType")),
                        RainBarrelCapacityTypes.getTypes(dataMap.get("capacity"))));  
    } 
    else {
      RainBarrelFormData data = formData.get();
      RainBarrel barrel = RainBarrelDB.addRainBarrel(data, Secured.getUserInfo(ctx()));
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          barrel.setImage(Files.toByteArray(source));
      }
      barrel.save();
      return redirect(routes.Application.viewBarrel(barrel.getID()));
     }     
    }
  
  /**
   * Manage information for a rain barrel.
   * @param id The ID of the rain barrel to manage.
   * @return The rain barrel edit form.
   */
  @Security.Authenticated(Secured.class)
  public static Result manageRainBarrel(long id) {
    if (RainBarrelDB.hasID(id)) {
      if (Secured.isLoggedIn(ctx()) 
          && (Secured.getUserInfo(ctx()).getId() == RainBarrelDB.getRainBarrel(id).getOwner().getId())) {
        RainBarrelFormData data = new RainBarrelFormData(RainBarrelDB.getRainBarrel(id));
        Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).fill(data);
        return ok(RegisterRainBarrel.render(formData, false, YesNoChoiceType.getChoiceList(), 
                  PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
                  DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
                  RainBarrelTypes.getRainBarrelTypes(data.rainBarrelType), 
                  MaterialTypes.getMaterialTypes(data.material), WaterUsageTypes.getWaterUsageTypes(data.waterUse), 
                  CoverTypes.getCoverTypes(data.cover), InstallationTypes.getInstallationTypes(data.installationType),
                  RainBarrelCapacityTypes.getTypes(data.capacity)));      
      }
    }
    return redirect(routes.Application.index());
  }
  
  /**
   * Register a new permeable paver.
   * @return The permeable paver registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result newPermeablePavers() {
    PermeablePaversFormData data = new PermeablePaversFormData();
    Form<PermeablePaversFormData> formData = Form.form(PermeablePaversFormData.class).fill(data);    
    return ok(RegisterPermeablePavers.render(formData, true, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(), DateTypes.getMonthTypes(), 
              DateTypes.getDayTypes(), DateTypes.getYearTypes(), 
              PaverMaterialTypes.getMaterialTypes(), 
              PaverMaterialTypes.getMaterialTypes(), 
              PermeablePaversSizeTypes.getTypes(), Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the created/edited permeable pavers page.
   * @param isNew Specifies if the permeable pavers is new or not.
   * @return The resulting permeable pavers page if information was valid, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postPermeablePaversRegister(boolean isNew) throws IOException {
    Form<PermeablePaversFormData> formData = Form.form(PermeablePaversFormData.class).bindFromRequest();
    validatePaverUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      return badRequest(RegisterPermeablePavers.render(formData, isNew,
                        YesNoChoiceType.getChoiceList(), 
                        PropertyTypes.getTypes(dataMap.get("propertyType")), 
                        DateTypes.getMonthTypes(dataMap.get("month")), 
                        DateTypes.getDayTypes(dataMap.get("day")), 
                        DateTypes.getYearTypes(dataMap.get("year")),
                        PaverMaterialTypes.getMaterialTypes(dataMap.get("material")),
                        PaverMaterialTypes.getMaterialTypes(dataMap.get("previousMaterial")),
                        PermeablePaversSizeTypes.getTypes(dataMap.get("size")),
                        Secured.getUserInfo(ctx())));   
    } 
    else {
      PermeablePaversFormData data = formData.get();
      PermeablePavers paver = PermeablePaversDB.addPermeablePavers(data, Secured.getUserInfo(ctx()));
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          paver.setImage(Files.toByteArray(source));
      }
      paver.save();
      return redirect(routes.Application.viewPaver(paver.getID()));
     }     
    }
  
  /**
   * Manage information for a permeable paver.
   * @param id The ID of the permeable paver to manage.
   * @return The rain barrel edit form.
   */
  @Security.Authenticated(Secured.class)
  public static Result managePermeablePavers(long id) {
    if (PermeablePaversDB.hasID(id)) {
      if (Secured.isLoggedIn(ctx()) 
          && (Secured.getUserInfo(ctx()).getId() == PermeablePaversDB.getPermeablePavers(id).getOwner().getId())) {
            PermeablePaversFormData data = new PermeablePaversFormData(PermeablePaversDB.getPermeablePavers(id));
            Form<PermeablePaversFormData> formData = Form.form(PermeablePaversFormData.class).fill(data);
            return ok(RegisterPermeablePavers.render(formData, false, YesNoChoiceType.getChoiceList(), 
                      PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
                      DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
                      PaverMaterialTypes.getMaterialTypes(data.material), 
                      PaverMaterialTypes.getMaterialTypes(data.previousMaterial), 
                      PermeablePaversSizeTypes.getTypes(data.size), Secured.getUserInfo(ctx())));     
      }
    }
    return redirect(routes.Application.index());
  }
  
  /**
   * View garden page.
   * @param id ID of garden to view.
   * @return The garden view page of the rain garden matching the given ID. 
   */
  public static Result viewGarden(Long id) {
    RainGarden garden = RainGardenDB.getRainGarden(id);
    GardenCommentFormData commentFormData = new GardenCommentFormData();
    Form<GardenCommentFormData> commentForm = Form.form(GardenCommentFormData.class).fill(commentFormData);
    if (garden != null) {
     return ok(ViewGarden.render(garden, PlantDB.getPlants(), commentForm));
    }
    return badRequest();
  }
  
  /**
   * Delete a garden from application.
   * @param id ID of garden to delete.
   * @return The index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteGarden(Long id) {
    RainGarden garden = RainGardenDB.getRainGarden(id);
    if (garden != null) {
      garden.getOwner().deleteGarden(garden);
      RainGardenDB.deleteRainGarden(garden.getID());
    }
    // Redirect to user page once implemented.
    return redirect(routes.Application.index());
  }
  
  /**
   * View barrel page.
   * @param id ID of barrel to view.
   * @return The barrel view page of the rain barrel matching the given ID. 
   */
  public static Result viewBarrel(Long id) {
    RainBarrel barrel = RainBarrelDB.getRainBarrel(id);
    BarrelCommentFormData commentFormData = new BarrelCommentFormData();
    Form<BarrelCommentFormData> commentForm = Form.form(BarrelCommentFormData.class).fill(commentFormData);
    if (barrel != null) {
     return ok(ViewBarrel.render(barrel, BarrelCommentDB.getRainBarrelComments(id), commentForm));
    }
    return redirect(routes.Application.barrelgallery());
  }
  
  /**
   * Delete a barrel from application.
   * @param id ID of barrel to delete.
   * @return The index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteBarrel(Long id) {
    RainBarrel barrel = RainBarrelDB.getRainBarrel(id);
    if (barrel != null) {
      barrel.getOwner().deleteBarrel(barrel);
      RainBarrelDB.deleteRainBarrel(id);
    }
    // Redirect to user page once implemented.
    return redirect(routes.Application.index());
  }
  
  /**
   * View paver page.
   * @param id ID of paver to view.
   * @return The paver view page of the permeable paver matching the given ID. 
   */
  public static Result viewPaver(Long id) {
    PermeablePavers paver = PermeablePaversDB.getPermeablePavers(id);
    PaverCommentFormData commentFormData = new PaverCommentFormData();
    Form<PaverCommentFormData> commentForm = Form.form(PaverCommentFormData.class).fill(commentFormData);
    if (paver != null) {
     return ok(ViewPaver.render(paver, PaverCommentDB.getRainPaverComments(id), commentForm));
    }
    return badRequest();
  }
  
  /**
   * Delete a paver from application.
   * @param id ID of paver to delete.
   * @return The index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result deletePaver(Long id) {
    PermeablePavers paver = PermeablePaversDB.getPermeablePavers(id);
    if (paver != null) {
      paver.getOwner().deletePaver(paver);
      PermeablePaversDB.deletePermeablePaver(paver.getID());
    }
    // Redirect to user page once implemented.
    return redirect(routes.Application.index());
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   */
  @Security.Authenticated(Secured.class)
  public static Result page1() {
    return ok(Page1.render("Welcome to Page1.", Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the sign in page.
   * @return sign in page
   */
  public static Result signup() {
	  Form<SignUpFormData> formData = Form.form(SignUpFormData.class);
	  return ok(SignUp.render(formData));
  }
 
 
  /**
   * Processes the sign up form.
   * @return
   */
  public static Result postSignUp() {
	  System.out.println("Post Sign Up");
	  Form<SignUpFormData> formData = Form.form(SignUpFormData.class).bindFromRequest();
	  if (formData.hasErrors() == true) {
		  System.out.println("Sign up Errors found.");
		  return badRequest(SignUp.render(formData));
	  }
	  else {
		  SignUpFormData data = formData.get();
		  System.out.println(data.firstName + " " + data.lastName + " " + data.email + " " + data.telephone + " " + data.password);
		  
		  //create new userinfo and add it to the "database"
		  UserInfoDB.addUserInfo(data.firstName, data.lastName, data.email, data.telephone, data.password, false);
		  
		  return redirect(routes.Application.login());
	  }
  }
  
  /**
   * Returns login page
   * @return Log in
   */
  public static Result login() {
	  Form<LoginFormData> formData = Form.form(LoginFormData.class);
	  return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
  }
  
  /**
   * Processes the log in form
   * @return
   */
  public static Result postLogin() {
	  System.out.println("Post Login");
	  Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
	  
	  if (formData.hasErrors() == true) {
		  System.out.println("Login errors found");
		  flash("error", "Login credentials not valid.");
		  return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
	  }
	  else {
		  session().clear();
		  session("email", formData.get().email);
		  return redirect(routes.Application.index());
	  }
  }
  
  /**
   * Logout the current user
   * @return the index page
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
	  session().clear();
	  return redirect(routes.Application.index());
  }
  
  /**
   * Returns the Learn More page
   * @return
   */
  public static Result learnmore() {
      return ok(LearnMore.render(ResourceDB.getGardenList(), ResourceDB.getBarrelList(), ResourceDB.getPaverList()));
  }
  
  /**
   * Validate a given form's upload file.
   * @param formData The form to check.
   * @param body Multipart form data that holds the uploaded file to check.
   * @return A form with validated upload data.
   */
  private static Form<RainGardenFormData> validateGardenUpload(Form<RainGardenFormData> formData, 
      MultipartFormData body) {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if (formData.errors().get("uploadFile") != null) {
      errors = formData.errors().get("uploadFile");
    }
    FilePart picture = body.getFile("uploadFile"); 
    if (picture != null) {
      if (!(picture.getContentType().contains("image"))) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is not an accepted "
            + "file type. Please select a file with the extension .jpg/.jpeg or .png"));
      }
      if (picture.getFile().length() > MAX_FILE_SIZE) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is too large. "
            + "Please select a file that is under 2.5 MB"));
      }
    }
    if (!errors.isEmpty()) {
      formData.errors().put("uploadFile", errors);
    }
    return formData;
  }
  
  /**
   * Validate a given form's upload file.
   * @param formData The form to check.
   * @param body Multipart form data that holds the uploaded file to check.
   * @return A form with validated upload data.
   */
  private static Form<RainBarrelFormData> validateBarrelUpload(Form<RainBarrelFormData> formData,
      MultipartFormData body) {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if (formData.errors().get("uploadFile") != null) {
      errors = formData.errors().get("uploadFile");
    }
    FilePart picture = body.getFile("uploadFile"); 
    if (picture != null) {
      if (!(picture.getContentType().contains("image"))) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is not an accepted "
            + "file type. Please select a file with the extension .jpg/.jpeg or .png"));
      }
      if (picture.getFile().length() > MAX_FILE_SIZE) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is too large. "
            + "Please select a file that is under 2.5 MB"));
      }
    }
    if (!errors.isEmpty()) {
      formData.errors().put("uploadFile", errors);
    }
    return formData;
  }
  
  /**
   * Validate a given form's upload file.
   * @param formData The form to check.
   * @param body Multipart form data that holds the uploaded file to check.
   * @return A form with validated upload data.
   */
  private static Form<PermeablePaversFormData> validatePaverUpload(Form<PermeablePaversFormData> formData,
      MultipartFormData body) {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if (formData.errors().get("uploadFile") != null) {
      errors = formData.errors().get("uploadFile");
    }
    FilePart picture = body.getFile("uploadFile"); 
    if (picture != null) {
      if (!(picture.getContentType().contains("image"))) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is not an accepted "
            + "file type. Please select a file with the extension .jpg/.jpeg or .png"));
      }
      if (picture.getFile().length() > MAX_FILE_SIZE) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is too large. "
            + "Please select a file that is under 2.5 MB"));
      }
    }
    if (!errors.isEmpty()) {
      formData.errors().put("uploadFile", errors);
    }
    return formData;
  }
  

  public static Result gardengallery() {
	  return ok(RainGardenGallery.render(RainGardenDB.getRainGardens()));
  }
  
  public static Result barrelgallery() {
	  return ok(RainBarrelGallery.render(RainBarrelDB.getRainBarrels()));
  }
  
  public static Result pavergallery() {
	  return ok(PermeablePaverGallery.render(PermeablePaversDB.getPermeablePavers()));
  }
  
  public static Result map() {
	  return ok(MapPage.render("Map"));
  }
  
  /**
   * Post a comment.
   * @param id ID of the solution being commented on.
   * @param uri Target of redirect.
   * @return The page that the user was commenting on.
   */
  @Security.Authenticated(Secured.class)
  public static Result postGardenComment(Long id, String uri) {
    Form<GardenCommentFormData> formData = Form.form(GardenCommentFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return redirect(uri);
    }
    GardenCommentFormData data = formData.get();
    GardenCommentDB.addComment(data, RainGardenDB.getRainGarden(data.id), Secured.getUserInfo(ctx()));
    return redirect(uri);
  }
  
  /**
   * Post a comment.
   * @param id ID of the solution being commented on.
   * @param uri Target of redirect.
   * @return The page that the user was commenting on.
   */
  @Security.Authenticated(Secured.class)
  public static Result postBarrelComment(Long id, String uri) {
    Form<BarrelCommentFormData> formData = Form.form(BarrelCommentFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return redirect(uri);
    }
    BarrelCommentFormData data = formData.get();
    BarrelCommentDB.addComment(data, RainBarrelDB.getRainBarrel(id), Secured.getUserInfo(ctx()));
    return redirect(uri);
  }
  
  /**
   * Post a comment.
   * @param id ID of the solution being commented on.
   * @param uri Target of redirect.
   * @return The page that the user was commenting on.
   */
  @Security.Authenticated(Secured.class)
  public static Result postPaverComment(Long id, String uri) {
    Form<PaverCommentFormData> formData = Form.form(PaverCommentFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return redirect(uri);
    }
    PaverCommentFormData data = formData.get();
    PaverCommentDB.addComment(data, PermeablePaversDB.getPermeablePavers(id), Secured.getUserInfo(ctx()));
    return redirect(uri);
  }
  
  public static Result retrieveHomeBannerImage() {
      return ok(HeaderFooterDB.getImage()).as("image/jpeg");
  }
  
  /**
   * Retrieve a garden image.
   * @param id The ID of the garden to retrieve the image from.
   * @return The image matching the ID given.
   */
  public static Result retrieveGardenImage(long id) {
    RainGarden garden = RainGardenDB.getRainGarden(id);
    if (garden != null && garden.hasPicture()) {      
      return ok(RainGardenDB.getRainGarden(id).getImage()).as("image/jpeg");
    }
    return redirect("");
  }
  
  /**
   * Retrieve a paver image.
   * @param id The ID of the paver to retrieve the image from.
   * @return The image matching the ID given.
   */
  public static Result retrieveBarrelImage(long id) {
    RainBarrel barrel = RainBarrelDB.getRainBarrel(id);
    if ((barrel != null) && barrel.hasPicture()) {
      return ok(RainBarrelDB.getRainBarrel(id).getImage()).as("image/jpeg");
    }
    return redirect("");
  }
  
  /**
   * Retrieve a paver image.
   * @param id The ID of the paver to retrieve the image from.
   * @return The image matching the ID given.
   */
  public static Result retrievePaverImage(long id) {
    PermeablePavers paver = PermeablePaversDB.getPermeablePavers(id);
    if ((paver != null) && paver.hasPicture()) {
      System.out.println("PermeablePaversDB.hasID");
      return ok(PermeablePaversDB.getPermeablePavers(id).getImage()).as("image/jpeg");
    }   
    return redirect("");
  }
  
  /**
   * Retrieve a plant image.
   * @param plantName The name of the plant to retrieve the image from.
   * @return The image matching the plant name given.
   */
  public static Result retrievePlantImage(String plantName) {
    Plant plant = PlantDB.getPlant(plantName);
    if (plant != null && plant.hasPicture()) {      
      return ok(plant.getImage()).as("image/jpeg");
    }
    return redirect(routes.Assets.at("images/placeholder.gif"));
  }
  
  /**
   * Register new plant.
   * @return The plant registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result newPlant() {
    PlantFormData data = new PlantFormData();
    Form<PlantFormData> formData = Form.form(PlantFormData.class).fill(data);
    return ok(RegisterPlant.render(formData, true, PlantFormDropdownTypes.getPlacementTypes(), 
                                   PlantFormDropdownTypes.getGrowthTypes(), PlantFormDropdownTypes.getClimateTypes(),
                                   "/assets/images/placeholder.gif"));
  }
  
  /**
   * Handles form submission for plant registration/edit.
   * @param isNew Specifies if the plant is new or not.
   * @return The plant listing, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postPlantRegistration(boolean isNew) throws IOException {
    Form<PlantFormData> formData = Form.form(PlantFormData.class).bindFromRequest();
    validatePlantUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      String url = routes.Application.retrievePlantImage(dataMap.get("name")).toString();
      return badRequest(RegisterPlant.render(formData, isNew, 
                                             PlantFormDropdownTypes.getPlacementTypes(dataMap.get("placement")), 
                                             PlantFormDropdownTypes.getGrowthTypes(dataMap.get("growth")), 
                                             PlantFormDropdownTypes.getClimateTypes(dataMap.get("climateType")),
                                             url));   
    } 
    else {      
      PlantFormData data = formData.get();
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      Plant plant = PlantDB.addPlant(data);
      if (picture != null) {
        Logger.debug("PLANT URL: " + picture.getFile().toURI().toURL());
        plant.setImage(Files.toByteArray(picture.getFile()));
      }
      plant.save();
      return redirect(routes.Application.viewPlants());
     }     
    }
  
  /**
   * Manage a plant's information.
   * 
   * @param plantName The name of the plant to manage.
   * @return The plant edit form.
   */
  @Security.Authenticated(Secured.class)
  public static Result managePlant(String plantName) {
    if (PlantDB.hasName(plantName)) {
      if (Secured.isLoggedIn(ctx()) && Secured.getUserInfo(ctx()).isAdmin()) {
        PlantFormData data = new PlantFormData(PlantDB.getPlant(plantName));
        data.hasPicture = true;
        Form<PlantFormData> formData = Form.form(PlantFormData.class).fill(data);
        return ok(RegisterPlant.render(formData, false, PlantFormDropdownTypes.getPlacementTypes(data.placement), 
                                       PlantFormDropdownTypes.getGrowthTypes(data.growth), 
                                       PlantFormDropdownTypes.getClimateTypes(data.climateType),
                                       routes.Application.retrievePlantImage(data.name).absoluteURL(request())));
      }
    }
    else if (!PlantDB.hasName(plantName)) {
      return badRequest(ErrorReport.render("\"" + request().host() + request().uri() + "\" is not a valid URL."));
    }
    return badRequest(ViewPlant.render("", Secured.getUserInfo(ctx())));
  }
  
  /**
   * Validate a given form's upload file.
   * @param formData The form to check.
   * @param body Multipart form data that holds the uploaded file to check.
   * @return A form with validated upload data.
   */
  private static Form<PlantFormData> validatePlantUpload(Form<PlantFormData> formData, MultipartFormData body) {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if (formData.errors().get("uploadFile") != null) {
      errors = formData.errors().get("uploadFile");
    }
    FilePart picture = body.getFile("uploadFile"); 
    if (picture != null) {
      if (!(picture.getContentType().contains("image"))) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is not an accepted "
            + "file type. Please select a file with the extension .jpg/.jpeg or .png"));
      }
      if (picture.getFile().length() > MAX_FILE_SIZE) {
        errors.add(new ValidationError("uploadFile", "The file \"" + picture.getFilename() + "\" is too large. "
            + "Please select a file that is under 2.5 MB"));
      }
    }
    if (!errors.isEmpty()) {
      formData.errors().put("uploadFile", errors);
    }
    return formData;
  }
  
  /**
   * Shows all the plants registered in the plant database.
   * @return A page showing all the plants in the database.
   */
  public static Result viewPlants() {
    return ok(ViewPlant.render("", Secured.getUserInfo(ctx())));
  }
  
  /**
   * Delete a plant from the system.
   * @param name Name of the plant to delete.
   * @return The view plant page.
   */
  public static Result deletePlant(long id) {
    PlantDB.deletePlant(id);
    return ok(ViewPlant.render("", Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the edit form for the resource matching the given header
   * @param header
   * @return
   */
  @Security.Authenticated(Secured.class)
  public static Result editResource(String header) {
	  if (Secured.getUserInfo(ctx()).isAdmin() == true) {
		  Resource resource;
		  if ((resource = ResourceDB.getGardenResource(header)) == null) {
			  if ((resource = ResourceDB.getBarrelResource(header)) == null) {
				  resource = ResourceDB.getPaverResource(header);
			  }
		  }
		  return ok(EditResource.render(resource));
	  }
	  return redirect(routes.Application.index());
  }
  
  /**
   * Returns the new resource page
   * @return
   */
  @Security.Authenticated(Secured.class)
  public static Result newResource(String header) {
	  if (Secured.getUserInfo(ctx()).isAdmin() == true) {
		  List<Resource> list;
		  if (header.equals("garden")) {
			  list = ResourceDB.getGardenList();
		  }
		  else if (header.equals("barrel")) {
			  list = ResourceDB.getBarrelList();
		  }
		  else {
			  list = ResourceDB.getPaverList();
		  }
		  return ok(NewResource.render(list));
	  }
	  return redirect(routes.Application.index());
  }
}
