import java.util.Calendar;
import play.Application;
import play.GlobalSettings;
import play.Logger;

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
    Logger.info("" + Calendar.getInstance().get(Calendar.YEAR));
  }
}
