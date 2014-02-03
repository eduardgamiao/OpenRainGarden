package controllers;

import java.util.List;
import models.RainGarden;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.DownspoutDisconnectedType;
import views.formdata.PropertyTypes;
import views.formdata.RainGardenFormData;
import views.html.Index;
import views.html.BrowseGardens;
import views.html.Page1;
import views.html.RainGardenRegistryForm;

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
    return ok(RainGardenRegistryForm.render(formData, DownspoutDisconnectedType.getChoiceList(),
              PropertyTypes.getTypes()));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
  public static Result postRainGardenRegister() {
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return badRequest(RainGardenRegistryForm.render(formData, DownspoutDisconnectedType.getChoiceList(),
                        PropertyTypes.getTypes()));
    } 
    else {
      RainGardenFormData form = formData.get();
      return TODO;
    }
    
  }
  
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
}
