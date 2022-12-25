package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import model.*;
import javafx.fxml.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class LoginController extends Controller<Session> {
    @FXML private TextField emailTf;
    @FXML private PasswordField passwordPf;
    @FXML private Text resultTxt;
    
    @FXML private void initialize() throws Exception {
        stage.getIcons().add(new Image("view/book.png"));
    }
    
    @FXML private void handleOk(ActionEvent event) throws Exception {
        Faculty faculty = getSession().getFaculty(getEmail(), getPassword());
        if (faculty != null) {
            stage.close();
            ViewLoader.showStage(faculty, "/view/faculty.fxml", "Session Admin: " + faculty.getName(), new Stage());
        }
        else {
             resultTxt.setText("Incorrect login details");
        }
            emailTf.clear();
            passwordPf.clear();
    }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception {
        stage.close();
    }
 
    private final Session getSession() {
        return model;
    }
    
    private String getEmail() {
        return emailTf.getText();
    }
    
    private String getPassword() {
        return passwordPf.getText();
    }
}
