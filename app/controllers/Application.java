package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.google.common.io.Files;
import models.RainGarden;
import play.api.mvc.MultipartFormData;
import play.api.mvc.MultipartFormData.FilePart;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.DateTypes;
import views.formdata.DownspoutDisconnectedType;
import views.formdata.PropertyTypes;
import views.formdata.RainGardenFormData;
import views.html.Index;
import views.html.BrowseGardens;
import views.html.Page1;
import views.html.RegisterRainGarden;
import views.html.Login;
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
   * @return The resulting rain garden page.
   */
  public static Result registerRainGarden() {
    RainGardenFormData data = new RainGardenFormData();
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).fill(data); 
    return ok(RegisterRainGarden.render(formData, DownspoutDisconnectedType.getChoiceList(), PropertyTypes.getTypes(), 
              DateTypes.getMonthTypes(), DateTypes.getMonthTypes(), DateTypes.getMonthTypes()));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
  public static Result postRainGardenRegister() {
      Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).bindFromRequest();
      
      if (formData.hasErrors()) {    
        return badRequest(RegisterRainGarden.render(formData, DownspoutDisconnectedType.getChoiceList(), 
                          PropertyTypes.getTypes(), DateTypes.getMonthTypes(), DateTypes.getMonthTypes(), 
                          DateTypes.getMonthTypes()));      
      }
      else {
        RainGardenFormData data = formData.get();
        System.out.println(data.propertyType);
        return ok(Index.render(data.title));
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
    return ok(Page1.render("Welcome to Page1."));
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
  
}
