package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import com.google.common.io.Files;
import models.PlantDB;
import models.RainBarrel;
import models.RainBarrelDB;
import models.RainGarden;
import models.RainGardenDB;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.formdata.CoverTypes;
import views.formdata.DateTypes;
import views.formdata.InstallationTypes;
import views.formdata.MaterialTypes;
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
import views.formdata.LoginFormData;
import views.html.SignUp;
import views.formdata.SignUpFormData;
import views.html.RegisterMenu;
import views.html.RegisterRainBarrel;

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
    return ok(Index.render("Home"));
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
  public static Result registerRainGarden(Long id) {
    RainGardenFormData data = (id == 0) 
        ? new RainGardenFormData() : new RainGardenFormData(RainGardenDB.getRainGarden(id));
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).fill(data); 
    return ok(RegisterRainGarden.render(formData, YesNoChoiceType.getChoiceList(), PropertyTypes.getTypes(), 
              DateTypes.getMonthTypes(), DateTypes.getDayTypes(), DateTypes.getYearTypes(), 
              PlantTypes.getPlantMap(), SolutionAmountType.getTypes()));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
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
                        SolutionAmountType.getTypes(dataMap.get("numberOfRainGardens"))));   
    } 
    else {
      RainGardenFormData data = formData.get();
      RainGarden garden = RainGardenDB.addRainGarden(data);
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          File destination = new File("public/images/rg" + garden.getID());
          source.renameTo(destination);
      }
      return ok(ViewGarden.render(garden, PlantDB.getPlants()));
     }     
    }
  
  /**
   * Returns the created/edited rain garden page.
   * @param id ID of rain garden.
   * @return The resulting rain garden page.
   */
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
      RainBarrel barrel = RainBarrelDB.addRainBarrel(data);
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart picture = body.getFile("uploadFile");
      if (picture != null) {
          File source = picture.getFile();
          File destination = new File("public/images/rb" + barrel.getID());
          source.renameTo(destination);
      }
      return TODO;
     }     
    }
  
  /**
   * View garden page.
   * @param id ID of garden to view.
   * @return The garden view page of the rain garden mathcing the given ID. 
   */
  public static Result viewGarden(Long id) {
    RainGarden garden = RainGardenDB.getRainGarden(id);
    if (garden != null) {
     return ok(ViewGarden.render(garden, PlantDB.getPlants()));
    }
    return badRequest(Index.render("Error"));
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
  public static Result page1() {
    return ok(Page1.render("Welcome to Page1.", PlantDB.getPlants()));
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
		  return ok(SignUp.render(formData));
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
}
