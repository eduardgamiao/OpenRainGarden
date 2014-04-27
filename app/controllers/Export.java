package controllers;

import play.mvc.Controller;
import play.mvc.Result;
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
  public static Result exportRainGarden() {
    // Prepare a chunked text stream
    Chunks<String> chunks = new StringChunks() {
      
      // Called when the stream is ready
      public void onReady(Chunks.Out<String> out) {
        rainGardenOutChannel(out);
      }
      
    };
    
    // Serves this stream with 200 OK
    return ok(chunks).as("text/csv");
  }

  /**
   * Writes the rain garden information to a stream.
   * @param out The stream being written to.
   */
  public static void rainGardenOutChannel(Chunks.Out<String> out) {
    out.write("TODO");
    out.close();
  }

}
