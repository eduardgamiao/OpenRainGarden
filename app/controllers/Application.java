package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FilenameUtils;
import com.google.common.io.Files;
import models.RainGardenDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.formdata.DateTypes;
import views.formdata.DownspoutDisconnectedType;
import views.formdata.PropertyTypes;
import views.formdata.RainGardenFormData;
import views.formdata.UploadResource;
import views.html.Index;
import views.html.BrowseGardens;
import views.html.Page1;
import views.html.RegisterRainGarden;
import views.html.Login;
import views.html.UploadRainGardenPicture;
import views.formdata.LoginFormData;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render("Home"));
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
    return ok(RegisterRainGarden.render(formData, DownspoutDisconnectedType.getChoiceList(), PropertyTypes.getTypes(), 
              DateTypes.getMonthTypes(), DateTypes.getDayTypes(), DateTypes.getYearTypes()));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
  public static Result postRainGardenRegister() {
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      Map<String, String> dataMap = formData.data();
      return badRequest(RegisterRainGarden.render(formData, DownspoutDisconnectedType.getChoiceList(), 
                        PropertyTypes.getTypes(dataMap.get("propertyType")), 
                        DateTypes.getMonthTypes(dataMap.get("month")), 
                        DateTypes.getDayTypes(dataMap.get("day")), 
                        DateTypes.getYearTypes(dataMap.get("year"))));   
    } 
    else {
      RainGardenFormData data = formData.get();
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart resourceFile = body.getFile("uploadFile");
      System.out.println(resourceFile.getContentType());
      return ok(Index.render(data.propertyType));
     }     
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
    UploadResource resource = new UploadResource();
    Form<UploadResource> formData = Form.form(UploadResource.class).fill(resource);
    return ok(Page1.render(formData, "Welcome to Page1."));
  }
  
  /**
   * Returns login page
   * @return Log in
   */
  public static Result login() {
	  Form<LoginFormData> formData = Form.form(LoginFormData.class);
	  return ok(Login.render(formData));
  }
  
  /**
   * Processes the log in form
   * @return
   */
  public static Result postLogin() {
	  System.out.println("Post Login");
	  Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
	  LoginFormData data = formData.get();
	  System.out.format("%s %s", data.email, data.password);
	  return ok(Login.render(formData));
  }
  
  public static Result uploadRainGardenPicture() {
    return ok(UploadRainGardenPicture.render("Upload Rain Garden Photo"));        
  }
  
  /**
   * A test page.
   * @return A test page.
   */
  public static Result uploadAction() {
    Form<UploadResource> filledForm = Form.form(UploadResource.class).bindFromRequest();

    if (filledForm.hasErrors()) {
        return TODO;
    } else {
        UploadResource resource = filledForm.get();
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart resourceFile = body.getFile("resourceFile");
        System.out.println(resourceFile.getContentType());
        return ok(Index.render("Oh snap!"));
     }    
  }
   
  /**
   * Returns the rain garden profile page..
   * @return The rain garden profile page.
   * @throws IOException 
   */
  public static Result upload() throws IOException {
    play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
    System.out.println(request().body().asMultipartFormData() == null);
    play.mvc.Http.MultipartFormData.FilePart picture = body.getFile("picture");
    if (picture != null) {
      String fileName = picture.getFilename();
      String contentType = picture.getContentType(); 
      File file = picture.getFile();
      File destinationFile = new File("./public/images/" + file.getName());
      //Files.copy(file, destinationFile);
      return ok(contentType);
    } else {
      flash("error", "Missing file");
      return redirect(routes.Application.index());    
    }
  }
  
}
