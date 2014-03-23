package views.formdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements choices for yes/no choices on forms.
 * @author eduardgamiao
 *
 */
public class YesNoChoiceType {
  
  private static String [] types = {"Yes", "No"};

  /**
   * Return list of choices for any yes/no choice.
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
