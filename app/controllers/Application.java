package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.common.io.Files;
import models.CommentDB;
import models.HeaderFooterDB;
import models.IndexContentDB;
import models.PermeablePavers;
import models.PermeablePaversDB;
import models.PlantDB;
import models.RainBarrel;
import models.RainBarrelDB;
import models.RainGarden;
import models.RainGardenDB;
import models.UserInfo;
import models.UserInfoDB;
import models.ResourceDB;
import play.Logger;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Security;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.formdata.CommentFormData;
import views.formdata.CoverTypes;
import views.formdata.DateTypes;
import views.formdata.InfiltrationRateTypes;
import views.formdata.InstallationTypes;
import views.formdata.MaterialTypes;
import views.formdata.PaverMaterialTypes;
import views.formdata.PermeablePaversFormData;
import views.formdata.PermeablePaversSizeTypes;
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
import views.formdata.SolutionAmountType;
import views.formdata.LoginFormData;
import views.formdata.SignUpFormData;
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
				    		HeaderFooterDB.getSubFooter()
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
			  
			  //create new userinfo and add it to the "database"
			  UserInfoDB.addUserInfo(data.firstName, data.lastName, data.email, data.telephone, data.password);
			  
			  return redirect(routes.Application.profile(data.email));
		  }
	  }
	  return redirect(routes.Application.errorReport("No user logged in, post edit profile fails."));
  }
  /**
   * Returns the registration navigation menu.
   * @return The registration navigation menu page.
   */
  public static Result registerMenu() {
    return ok(RegisterMenu.render("Registration - Main"));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @param id ID of rain garden.
   * @return The resulting rain garden page.
   */
  @Security.Authenticated(Secured.class)
  public static Result registerRainGarden(Long id) {
    RainGardenFormData data;
    if (id == 0) {
      data = new RainGardenFormData();
    }
    else if ((RainGardenDB.getRainGarden(id) == null)
             || (Secured.getUserInfo(ctx()) != RainGardenDB.getRainGarden(id).getOwner())) {
      return redirect(routes.Application.registerRainGarden(0));
    }
    else {
      data = new RainGardenFormData(RainGardenDB.getRainGarden(id));
    }
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).fill(data);   
    return ok(RegisterRainGarden.render(formData, id, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
              DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
              PlantTypes.getPlantMap(data.plants), RainGardenSizeTypes.getTypes(data.rainGardenSize), 
              WaterSourceSizeTypes.getTypes(data.waterFlowSourceSize), 
              InfiltrationRateTypes.getTypes(data.infiltrationRate), Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postRainGardenRegister() throws IOException {
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).bindFromRequest();
    validateGardenUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      Long id = Long.decode(dataMap.get("id"));
      List<String> plantList = new ArrayList<String>();
      for (String key : dataMap.keySet()) {
        if (key.contains("plants")) {
          plantList.add(dataMap.get(key));
        }
      }          
      return badRequest(RegisterRainGarden.render(formData, id, YesNoChoiceType.getChoiceList(), 
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
   * Returns the created/edited rain garden page.
   * @param id ID of rain garden.
   * @return The resulting rain garden page.
   */
  @Security.Authenticated(Secured.class)
  public static Result registerRainBarrel(Long id) {
    RainBarrelFormData data;
    if (id == 0) {
      data = new RainBarrelFormData();
    }
    else if ((RainBarrelDB.getRainBarrel(id) == null) 
              || (Secured.getUserInfo(ctx()) != RainBarrelDB.getRainBarrel(id).getOwner())) {
      return redirect(routes.Application.registerRainBarrel(0));
    }
    else {
      data = new RainBarrelFormData(RainBarrelDB.getRainBarrel(id));
    }
    Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).fill(data);    
    return ok(RegisterRainBarrel.render(formData, id, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
              DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
              RainBarrelTypes.getRainBarrelTypes(data.rainBarrelType), MaterialTypes.getMaterialTypes(data.material),
              WaterUsageTypes.getWaterUsageTypes(data.waterUse), CoverTypes.getCoverTypes(data.cover), 
              InstallationTypes.getInstallationTypes(data.installationType),
              RainBarrelCapacityTypes.getTypes(data.capacity)));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postRainBarrelRegister() throws IOException {
    Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).bindFromRequest();
    long id = 0;
    validateBarrelUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      id = Long.decode(dataMap.get("id"));
      return badRequest(RegisterRainBarrel.render(formData, id, YesNoChoiceType.getChoiceList(), 
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
      return redirect(routes.Application.viewBarrel(barrel.getID()));
     }     
    }
  
  /**
   * Returns the created/edited permeable pavers page.
   * @param id ID of permeable pavers.
   * @return The resulting permeable paver page.
   */
  @Security.Authenticated(Secured.class)
  public static Result registerPermeablePavers(Long id) {
    PermeablePaversFormData data;
    if (id == 0 || (PermeablePaversDB.getPermeablePavers(id) == null)) {
      data = new PermeablePaversFormData();
    }
    else if (Secured.getUserInfo(ctx()) != PermeablePaversDB.getPermeablePavers(id).getOwner()) {
      return redirect(routes.Application.registerPermeablePavers(0));
    }
    else {
      data = new PermeablePaversFormData(PermeablePaversDB.getPermeablePavers(id));
    }
    Form<PermeablePaversFormData> formData = Form.form(PermeablePaversFormData.class).fill(data);
    return ok(RegisterPermeablePavers.render(formData, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
              DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
              PaverMaterialTypes.getMaterialTypes(data.material), 
              PaverMaterialTypes.getMaterialTypes(data.previousMaterial), 
              PermeablePaversSizeTypes.getTypes(data.size), Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the created/edited permeable pavers page.
   * @return The resulting permeable pavers page if information was valid, else the registration form.
   * @throws IOException When there is an issue when copying the file to the byte array.
   */
  @Security.Authenticated(Secured.class)
  public static Result postPermeablePaversRegister() throws IOException {
    Form<PermeablePaversFormData> formData = Form.form(PermeablePaversFormData.class).bindFromRequest();
    validatePaverUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      return badRequest(RegisterPermeablePavers.render(formData, 
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
      return redirect(routes.Application.viewPaver(paver.getID()));
     }     
    }
  
  /**
   * View garden page.
   * @param id ID of garden to view.
   * @return The garden view page of the rain garden matching the given ID. 
   */
  public static Result viewGarden(Long id) {
    RainGarden garden = RainGardenDB.getRainGarden(id);
    CommentFormData commentFormData = new CommentFormData();
    Form<CommentFormData> commentForm = Form.form(CommentFormData.class).fill(commentFormData);
    if (garden != null) {
     return ok(ViewGarden.render(garden, PlantDB.getPlants(), CommentDB.getComments(garden.getKey()), commentForm));
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
    CommentFormData commentFormData = new CommentFormData();
    Form<CommentFormData> commentForm = Form.form(CommentFormData.class).fill(commentFormData);
    if (barrel != null) {
     return ok(ViewBarrel.render(barrel, CommentDB.getComments(barrel.getKey()), commentForm));
    }
    return badRequest();
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
    CommentFormData commentFormData = new CommentFormData();
    Form<CommentFormData> commentForm = Form.form(CommentFormData.class).fill(commentFormData);
    if (paver != null) {
     return ok(ViewPaver.render(paver, CommentDB.getComments(paver.getKey()), commentForm));
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
		  UserInfoDB.addUserInfo(data.firstName, data.lastName, data.email, data.telephone, data.password);
		  
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
    Form<CommentFormData> formData = Form.form(CommentFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return redirect(uri);
    }
    CommentFormData data = formData.get();
    CommentDB.addComment(data, Secured.getUserInfo(ctx()), RainGardenDB.getRainGarden(id).getKey());
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
    Form<CommentFormData> formData = Form.form(CommentFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return redirect(uri);
    }
    CommentFormData data = formData.get();
    if (uri.contains("rain-garden")) {
      CommentDB.addComment(data, Secured.getUserInfo(ctx()), RainGardenDB.getRainGarden(id).getKey());
    }
    else if (uri.contains("rain-barrel")) {
      CommentDB.addComment(data, Secured.getUserInfo(ctx()), RainBarrelDB.getRainBarrel(id).getKey());
    }    
    else if (uri.contains("permeable-pavers")) {
      CommentDB.addComment(data, Secured.getUserInfo(ctx()), PermeablePaversDB.getPermeablePavers(id).getKey());
    }
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
    Form<CommentFormData> formData = Form.form(CommentFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return redirect(uri);
    }
    CommentFormData data = formData.get();
    if (uri.contains("rain-garden")) {
      CommentDB.addComment(data, Secured.getUserInfo(ctx()), RainGardenDB.getRainGarden(id).getKey());
    }
    else if (uri.contains("rain-barrel")) {
      CommentDB.addComment(data, Secured.getUserInfo(ctx()), RainBarrelDB.getRainBarrel(id).getKey());
    }    
    else if (uri.contains("permeable-pavers")) {
      CommentDB.addComment(data, Secured.getUserInfo(ctx()), PermeablePaversDB.getPermeablePavers(id).getKey());
    }
    return redirect(uri);
  }

  /**
   * Retrieve a garden image.
   * @param id The ID of the garden to retrieve the image from.
   * @return The image matching the ID given.
   */
  public static Result retrieveGardenImage(long id) {
    if (RainGardenDB.hasID(id)) {
      return ok(RainGardenDB.getRainGarden(id).getImage()).as("image/jpeg");
    }
    return redirect("/");
  }
  
  /**
   * Retrieve a paver image.
   * @param id The ID of the paver to retrieve the image from.
   * @return The image matching the ID given.
   */
  public static Result retrieveBarrelImage(long id) {
    if (RainBarrelDB.hasID(id)) {
      return ok(RainBarrelDB.getRainBarrel(id).getImage()).as("image/jpeg");
    }
    return redirect("/");
  }
  
  /**
   * Retrieve a paver image.
   * @param id The ID of the paver to retrieve the image from.
   * @return The image matching the ID given.
   */
  public static Result retrievePaverImage(long id) {
    if (PermeablePaversDB.hasID(id)) {
      System.out.println("------------------------------------------------PermeablePaversDB.hasID");
      return ok(PermeablePaversDB.getPermeablePavers(id).getImage()).as("image/jpeg");
    }
   
    return redirect("/");
  }
}
