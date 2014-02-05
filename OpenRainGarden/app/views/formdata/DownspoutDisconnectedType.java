package views.formdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements choices for downspouts disconnected drop down.
 * @author eduardgamiao
 *
 */
public class DownspoutDisconnectedType {
  
  private static String [] types = {"Yes", "No"};

  /**
   * Return list of choices for downspout disconnected.
   * @return List of strings that represent the choices.
   */
  public static List<String> getChoiceList() {
    List<String> footStyles = new ArrayList<String>();
    for (String current : types) {
      footStyles.add(current);
    }
    return footStyles;
  }

}
