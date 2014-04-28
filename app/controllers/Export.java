package controllers;

import models.RainGarden;
import models.RainGardenDB;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.PlantFormDropdownTypes;

/**
 * Handles exporting data from application.
 * @author eduardgamiao
 *
 */
public class Export extends Controller {
  
  /**
   * Export the rain garden information as a csv.
   * @return The rain garden information as a csv.
   */
  @Security.Authenticated(Secured.class)
  public static Result exportRainGarden() {
    if (Secured.isLoggedIn(ctx()) && Secured.getUserInfo(ctx()).isAdmin()) {
      // Prepare a chunked text stream
      Chunks<String> chunks = new StringChunks() {

        // Called when the stream is ready
        public void onReady(Chunks.Out<String> out) {
          rainGardenOutChannel(out);
        }

      };

      // Serves this stream with 200 OK
      response().setContentType("text/csv");
      return ok(chunks);
    }
    return redirect(routes.Application.index());
  }

  /**
   * Writes the rain garden information to a stream.
   * @param out The stream being written to.
   */
  @Security.Authenticated(Secured.class)
  public static void rainGardenOutChannel(Chunks.Out<String> out) {
    out.write("Title,Property_Type,Address,Description,Date_Installed,Rain_Garden_Size,Waterflow_Source_Size,"
        + "Waterflow_Description,Infiltration_Rate,Owner_Name\n");
    for (RainGarden garden : RainGardenDB.getRainGardens()) {
      out.write(garden.formatToCSV());
    }
    out.close();
  }

}
