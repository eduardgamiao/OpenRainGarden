package controllers;

import models.Plant;
import models.PlantDB;
import play.mvc.Controller;
import play.mvc.Result;

public class Export extends Controller {
  
  public static Result index() {
    // Prepare a chunked text stream
    Chunks<String> chunks = new StringChunks() {
      
      // Called when the stream is ready
      public void onReady(Chunks.Out<String> out) {
        registerOutChannelSomewhere(out);
      }
      
    };
    
    // Serves this stream with 200 OK
    return ok(chunks).as("text/csv");
  }
  
  public static void registerOutChannelSomewhere(Chunks.Out<String> out) {
    out.write("TODO");
    out.close();
  }

}
