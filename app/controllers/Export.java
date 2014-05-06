package controllers;

import models.PermeablePavers;
import models.PermeablePaversDB;
import models.RainBarrel;
import models.RainBarrelDB;
import models.RainGarden;
import models.RainGardenDB;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

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
      String output = "Title,Property_Type,Address,Description,Date_Installed,Rain_Garden_Size,Waterflow_Source_Size,"
          + "Waterflow_Description,Infiltration_Rate,Owner_Email\n";
      for (RainGarden garden : RainGardenDB.getRainGardens()) {
        output += garden.formatToCSV();
      }
      return ok(output).as("text/csv");
    }
    return redirect(routes.Application.index());
  }
  
  /**
   * Export the rain barrel information as a csv.
   * @return The rain barrel information as a csv.
   */
  @Security.Authenticated(Secured.class)
  public static Result exportRainBarrel() {
    if (Secured.isLoggedIn(ctx()) && Secured.getUserInfo(ctx()).isAdmin()) {
      // Prepare a chunked text stream
      Chunks<String> chunks = new StringChunks() {

        // Called when the stream is ready
        public void onReady(Chunks.Out<String> out) {          
          rainBarrelOutChannel(out);
        }

      };

      // Serves this stream with 200 OK
      response().setContentType("text/csv");
      return ok(chunks);
    }
    return redirect(routes.Application.index());
  }

  /**
   * Writes the rain barrel information to a stream.
   * @param out The stream being written to.
   */
  @Security.Authenticated(Secured.class)
  public static void rainBarrelOutChannel(Chunks.Out<String> out) {
    out.write("Title,Property_Type,Address,Description,Date_Installed,Rain_Barrel_Type,Capacity,Color,Material,"
        + "Estimated_Cost,Water_Use,Overflow_Frequency,Cover,Obtained_From,Installation_Type,Owner_Email\n");
    for (RainBarrel barrel : RainBarrelDB.getRainBarrels()) {
      out.write(barrel.formatToCSV());
    }
    out.close();
  }
  
  /**
   * Export the permeable pavers information as a csv.
   * @return The permeable pavers information as a csv.
   */
  @Security.Authenticated(Secured.class)
  public static Result exportPermeablePavers() {
    if (Secured.isLoggedIn(ctx()) && Secured.getUserInfo(ctx()).isAdmin()) {
      // Prepare a chunked text stream
      Chunks<String> chunks = new StringChunks() {

        // Called when the stream is ready
        public void onReady(Chunks.Out<String> out) {          
          permeablePaversOutChannel(out);
        }

      };

      // Serves this stream with 200 OK
      response().setContentType("text/csv");
      return ok(chunks);
    }
    return redirect(routes.Application.index());
  }

  /**
   * Writes the permeable pavers information to a stream.
   * @param out The stream being written to.
   */
  @Security.Authenticated(Secured.class)
  public static void permeablePaversOutChannel(Chunks.Out<String> out) {
    out.write("Title,Property_Type,Address,Description,Date_Installed,Material,Previous_Material,Size,"
              + "Installer,Owner_Email\n");
    for (PermeablePavers paver : PermeablePaversDB.getPermeablePavers()) {
      out.write(paver.formatToCSV());
    }
    out.close();
  }


}
