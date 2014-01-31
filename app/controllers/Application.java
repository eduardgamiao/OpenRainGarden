package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.RainGardenFormData;
import views.html.Index;
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
    return ok(RainGardenRegistryForm.render(formData));
  }
  
  /**
   * Returns the created/edited rain garden page.
   * @return The resulting rain garden page if information was valid, else the registration form.
   */
  public static Result postRainGardenRegister() {
    Form<RainGardenFormData> formData = Form.form(RainGardenFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      return badRequest(RainGardenRegistryForm.render(formData));
    } 
    else {
      RainGardenFormData form = formData.get();
      System.out.println(form.firstName + " " + form.lastName);
      return TODO;
    }
    
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   */
  public static Result page1() {
    return ok(Page1.render("Welcome to Page1."));
    
  }
}
