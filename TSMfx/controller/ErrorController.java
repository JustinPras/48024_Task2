package controller;

import au.edu.uts.ap.javafx.Controller;
import static controller.StudentController.validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import model.Student;



public class ErrorController  extends Controller<Student> {
   @FXML private Text errorsT;
    
    @FXML private void initialize() throws Exception {
        String errors = "";
        for (String s : validator.errors()) {
            errors += s;
        }
        errorsT.setText(errors);
        stage.getIcons().add(new Image("view/error.png"));
    }
    
    @FXML private void handleOK(ActionEvent event) {
        stage.close();
    }
    
    public final Student getStudent() {
        return model;
    }

}
