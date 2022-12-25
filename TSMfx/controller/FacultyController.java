package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Faculty;
import model.Student;

public class FacultyController extends Controller<Faculty> {
    @FXML private TableView<Student> studentsTv;
    @FXML private TextField nameTf;
    @FXML private TextField emailTf;
    @FXML private Button deleteBtn;
    @FXML private Button selectBtn;
    @FXML private Button slipBtn;
 
    @FXML private void initialize() throws Exception{
        stage.getIcons().add(new Image("view/faculty.png"));
        studentsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        studentsTv.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldStudent, newStudent) -> disableButtons(newStudent == null));
    }
    
    @FXML private void handleFilterName(KeyEvent event) throws Exception {
        getFaculty().filterList(nameTf.getText(), "1234567890");
    }
    @FXML private void handleFilterEmail(KeyEvent event) throws Exception {
          getFaculty().filterList("1234567890", emailTf.getText());
      }
    @FXML private void handleAdd(ActionEvent event) throws Exception {
        Student student = new Student("Placeholder Student", "", "", "", "", "", 0, 0.0, "");
        student.setFaculty(getFaculty());
        ViewLoader.showStage(student, "/view/student.fxml", "Adding New Student", new Stage());
    }
    
    @FXML private void handleDelete(ActionEvent event) throws Exception {
        getFaculty().removeStudent(studentsTv.getSelectionModel().getSelectedItem());
        disableButtons(true);
        studentsTv.getSelectionModel().clearSelection();
    }
    
    @FXML private void handleSelect(ActionEvent event) throws Exception {
        ViewLoader.showStage(studentsTv.getSelectionModel().getSelectedItem(), "/view/student.fxml", "Accessing File: " + (studentsTv.getSelectionModel().getSelectedItem().getName()), new Stage());
        disableButtons(true);
        studentsTv.getSelectionModel().clearSelection();
    }    
    
    @FXML private void handleSlip(ActionEvent event) throws Exception {
        ViewLoader.showStage(studentsTv.getSelectionModel().getSelectedItem(), "/view/slip.fxml", (studentsTv.getSelectionModel().getSelectedItem().getName()) + " SLIP Report", new Stage());
        disableButtons(true);
        studentsTv.getSelectionModel().clearSelection();
    }
    
    @FXML private void handleReport(ActionEvent event) throws Exception {
        ViewLoader.showStage(getFaculty(), "/view/tms.fxml", "TMS Report", new Stage());
    }
    
    @FXML private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
    
    private final void disableButtons(boolean disabled) {
        deleteBtn.setDisable(disabled);
        selectBtn.setDisable(disabled);
        slipBtn.setDisable(disabled);
    }
    
    public final Faculty getFaculty() {
        return model;
    }
}
