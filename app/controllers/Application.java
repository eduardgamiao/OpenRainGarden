package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.IndexContentDB;
import models.PermeablePavers;
import models.PermeablePaversDB;
import models.PlantDB;
import models.RainBarrel;
import models.RainBarrelDB;
import models.RainGarden;
import models.RainGardenDB;
import models.UserInfoDB;
import models.ResourceDB;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Security;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.formdata.CoverTypes;
import views.formdata.DateTypes;
import views.formdata.InstallationTypes;
import views.formdata.MaterialTypes;
import views.formdata.PaverMaterialTypes;
import views.formdata.PermeablePaversFormData;
import views.formdata.RainBarrelFormData;
import views.formdata.RainBarrelTypes;
import views.formdata.WaterUsageTypes;
import views.formdata.YesNoChoiceType;
import views.formdata.PlantTypes;
import views.formdata.PropertyTypes;
import views.formdata.RainGardenFormData;
import views.formdata.SolutionAmountType;
import views.html.Index;
import views.html.BrowseGardens;
import views.html.Page1;
import views.html.RegisterRainGarden;
import views.html.Login;
import views.html.ViewGarden;
import views.html.ViewBarrel;
import views.formdata.LoginFormData;
import views.html.SignUp;
import views.formdata.SignUpFormData;
import views.html.RegisterMenu;
import views.html.RegisterRainBarrel;
import views.html.LearnMore;
import views.html.RegisterPermeablePavers;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {
  private static final long MAX_FILE_SIZE = 2621440;

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render(IndexContentDB.getBlocks()));
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
    RainGardenFormData data = (id == 0) 
        ? new RainGardenFormData() : new RainGardenFormData(RainGardenDB.getRainGarden(id));
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).fill(data);
    return ok(RegisterRainGarden.render(formData, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
              DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
              PlantTypes.getPlantMap(data.plants), SolutionAmountType.getTypes(data.numberOfRainGardens), 
              Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result postRainGardenRegister() {
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
      return badRequest(RegisterRainGarden.render(formData, YesNoChoiceType.getChoiceList(), 
                        PropertyTypes.getTypes(dataMap.get("propertyType")), 
                        DateTypes.getMonthTypes(dataMap.get("month")), 
                        DateTypes.getDayTypes(dataMap.get("day")), 
                        DateTypes.getYearTypes(dataMap.get("year")),
                        PlantTypes.getPlantMap(plantList),
                        SolutionAmountType.getTypes(dataMap.get("numberOfRainGardens")),
                        Secured.getUserInfo(ctx())));   
    } 
    else {
      RainGardenFormData data = formData.get();
      RainGarden garden = RainGardenDB.addRainGarden(data, Secured.getUserInfo(ctx()));
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          File destination = new File("public/images/upload/rg" + garden.getID());
          source.renameTo(destination);
      }
      return redirect("/view/rain-garden/" + garden.getID());
     }     
    }
  
  /**
   * Returns the created/edited rain garden page.
   * @param id ID of rain garden.
   * @return The resulting rain garden page.
   */
  @Security.Authenticated(Secured.class)
  public static Result registerRainBarrel(Long id) {
    RainBarrelFormData data = (id == 0) 
        ? new RainBarrelFormData() : new RainBarrelFormData(RainBarrelDB.getRainBarrel(id));
    Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).fill(data); 
    return ok(RegisterRainBarrel.render(formData, YesNoChoiceType.getChoiceList(), PropertyTypes.getTypes(), 
              DateTypes.getMonthTypes(), DateTypes.getDayTypes(), DateTypes.getYearTypes(), 
              RainBarrelTypes.getRainBarrelTypes(), MaterialTypes.getMaterialTypes(),
              WaterUsageTypes.getWaterUsageTypes(), CoverTypes.getCoverTypes(), 
              InstallationTypes.getInstallationTypes(), SolutionAmountType.getTypes()));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result postRainBarrelRegister() {
    Form<RainBarrelFormData> formData = Form.form(RainBarrelFormData.class).bindFromRequest();
    validateBarrelUpload(formData, request().body().asMultipartFormData());
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      return badRequest(RegisterRainBarrel.render(formData, YesNoChoiceType.getChoiceList(), 
                        PropertyTypes.getTypes(dataMap.get("propertyType")), 
                        DateTypes.getMonthTypes(dataMap.get("month")), 
                        DateTypes.getDayTypes(dataMap.get("day")), 
                        DateTypes.getYearTypes(dataMap.get("year")),
                        RainBarrelTypes.getRainBarrelTypes(dataMap.get("rainBarrelType")),
                        MaterialTypes.getMaterialTypes(dataMap.get("material")),
                        WaterUsageTypes.getWaterUsageTypes(dataMap.get("waterUse")),
                        CoverTypes.getCoverTypes(dataMap.get("cover")),
                        InstallationTypes.getInstallationTypes(dataMap.get("installationType")),
                        SolutionAmountType.getTypes(dataMap.get("numberOfRainBarrels"))));   
    } 
    else {
      RainBarrelFormData data = formData.get();
      RainBarrel barrel = RainBarrelDB.addRainBarrel(data, Secured.getUserInfo(ctx()));
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          File destination = new File("public/images/upload/rb" + barrel.getID());
          source.renameTo(destination);
      }
      return redirect("/view/rain-barrel/" + barrel.getID());
     }     
    }
  
  /**
   * Returns the created/edited permeable pavers page.
   * @param id ID of permeable pavers.
   * @return The resulting permeable paver page.
   */
  @Security.Authenticated(Secured.class)
  public static Result registerPermeablePavers(Long id) {
    PermeablePaversFormData data = (id == 0) 
        ? new PermeablePaversFormData() : new PermeablePaversFormData(PermeablePaversDB.getPermeablePavers(id));
    Form<PermeablePaversFormData> formData = Form.form(PermeablePaversFormData.class).fill(data);
    return ok(RegisterPermeablePavers.render(formData, YesNoChoiceType.getChoiceList(), 
              PropertyTypes.getTypes(data.propertyType), DateTypes.getMonthTypes(data.month), 
              DateTypes.getDayTypes(data.day), DateTypes.getYearTypes(data.year), 
              PaverMaterialTypes.getMaterialTypes(data.material), Secured.getUserInfo(ctx())));
  }
  
  /**
   * Returns the created/edited permeable pavers page.
   * @return The resulting permeable pavers page if information was valid, else the registration form.
   */
  @Security.Authenticated(Secured.class)
  public static Result postPermeablePaversRegister() {
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
                        Secured.getUserInfo(ctx())));   
    } 
    else {
      PermeablePaversFormData data = formData.get();
      PermeablePavers paver = PermeablePaversDB.addPermeablePavers(data, Secured.getUserInfo(ctx()));
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          File destination = new File("public/images/upload/pp" + paver.getID());
          source.renameTo(destination);
      }
      return TODO;
     }     
    }
  
  /**
   * View garden page.
   * @param id ID of garden to view.
   * @return The garden view page of the rain garden matching the given ID. 
   */
  public static Result viewGarden(Long id) {
    RainGarden garden = RainGardenDB.getRainGarden(id);
    if (garden != null) {
     return ok(ViewGarden.render(garden, PlantDB.getPlants()));
    }
    //return badRequest(Index.render("Error"));
    return ok(BrowseGardens.render("Error"));
  }
  
  /**
   * View barrel page.
   * @param id ID of barrel to view.
   * @return The barrel view page of the rain barrel matching the given ID. 
   */
  public static Result viewBarrel(Long id) {
    RainBarrel barrel = RainBarrelDB.getRainBarrel(id);
    if (barrel != null) {
     return ok(ViewBarrel.render(barrel));
    }
    //return badRequest(Index.render("Error"));
    return ok(BrowseGardens.render("Error"));
  }
  
  /**
   * Brings up the rain garden profile page.
   * @return The rain garden profile page.
   */
  public static Result browseGarden() {
    return ok(BrowseGardens.render("Browse Rain Gardens"));
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
}
