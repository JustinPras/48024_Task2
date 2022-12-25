package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button; 
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Session;


public class SessionController extends Controller<Session> {
   @FXML private Button loginBtn;
   @FXML private Button exitBtn;
   
   
   @FXML private void handleLogin(ActionEvent event) throws Exception {
       ViewLoader.showStage(getSession(), "/view/login.fxml", "Sign In", new Stage());
   }
   
   @FXML private void handleExit(ActionEvent event) {
       Platform.exit();
   }
   
   private final Session getSession() {
       return model;
   }
}
