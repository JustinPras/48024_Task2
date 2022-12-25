package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Student;


public class StudentController extends Controller<Student> {
    @FXML private TextField nameTf;
    @FXML private TextField emailTf;
    @FXML private TextField phoneTf;
    @FXML private TextField addressTf;
    @FXML private TextField IDTf;
    @FXML private TextField typeTf;
    @FXML private TextField creditsTf;
    @FXML private TextField scholarshipTf;
    @FXML private TextField deductionTf;
    public static Validator validator;
    @FXML private Button updateBtn;
    @FXML private Button addBtn;
    
    @FXML private void initialize() throws Exception {
        validator = new Validator();
        stage.getIcons().add(new Image("view/edit.png"));
        if (!getStudent().getName().equals("Placeholder Student")) {
            addBtn.setDisable(true);
            nameTf.textProperty().bind(getStudent().nameProperty()); nameTf.textProperty().unbind();
            emailTf.textProperty().bind(getStudent().emailProperty()); emailTf.textProperty().unbind();
            phoneTf.textProperty().bind(getStudent().phoneProperty()); phoneTf.textProperty().unbind();
            addressTf.textProperty().bind(getStudent().addressProperty()); addressTf.textProperty().unbind();
            IDTf.textProperty().bind(getStudent().IDProperty()); IDTf.textProperty().unbind();
            typeTf.textProperty().bind(getStudent().typeProperty()); typeTf.textProperty().unbind();
            creditsTf.textProperty().bind(getStudent().creditsProperty().asString()); creditsTf.textProperty().unbind();
            scholarshipTf.textProperty().bind(getStudent().scholarshipProperty().asString()); scholarshipTf.textProperty().unbind();
            deductionTf.textProperty().bind(getStudent().deductionProperty().asString()); deductionTf.textProperty().unbind();
        }
        else {
            creditsTf.setText("0");
            scholarshipTf.setText("0.0");
            deductionTf.setText("Code");
            updateBtn.setDisable(true);
        }
    }

    @FXML private void handleAdd(ActionEvent event) throws Exception {
        if (validator.isValid(
                getName(), getEmail(), getPhone(), getAddress(), getType(), getID(), getCredits(), getScholarship())) {
            getStudent().updateDetails(
                    getName(), getEmail(), getPhone(), getAddress(), getID(), getType(), getCredits(), getScholarship(),
                    getDeduction());
            getStudent().getFaculty().addStudent(getStudent());
            stage.close();
        }
        else {
            validator.clear();
           validator.generateErrors(
                   getName(), getEmail(), getPhone(), getAddress(), getType(), getID(), getCredits(), getScholarship());
           ViewLoader.showStage(getStudent(), "/view/error.fxml", "Input Exceptions", new Stage());
        }
    }
    
    @FXML private void handleUpdate(ActionEvent event) throws Exception {
        System.out.println(getStudent().getDeduction());
        System.out.println(getDeduction());
        System.out.println(getDeductionCode());
       if (validator.isValid(
                getName(), getEmail(), getPhone(), getAddress(), getType(), getID(), getCredits(), getScholarship())) {
           getStudent().updateDetails(
                getName(), getEmail(), getPhone(), getAddress(), getID(), getType(), getCredits(), getScholarship(),
                getDeductionCode());
           stage.close();
       }
       else {
           validator.clear();
           validator.generateErrors(
                getName(), getEmail(), getPhone(), getAddress(), getType(), getID(), getCredits(), getScholarship());
           ViewLoader.showStage(getStudent(), "/view/error.fxml", "Input Exceptions", new Stage());
       }
    }
    
    @FXML private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
    
    public final Student getStudent() {
        return model;
    }
    
    private String getDeductionCode() {
        if (getDeduction().equals("2022AUT")) {
            return "2022AUT";
        }
        else if (getDeduction().equals("0.0")) {
            return "Bad Code";
        }
        else {
            try {
                if (Double.parseDouble(deductionTf.getText()) == (getStudent().getDeductionRate()*getStudent().getTotalFee())) {
                    return "2022AUT";
                }
                else {
                    return "Bad Code";
                }
            }
            catch (Exception e) {
                return "Bad Code";   
            }
        }
    }
    
    private String getName() {
        return nameTf.getText();
    }
    
    private String getEmail() {
        return emailTf.getText();
    }
    private String getPhone() {
        return phoneTf.getText();
    }
    private String getAddress() {
        return addressTf.getText();
    }
    private String getID() {
        return IDTf.getText();
    }
    private String getType() {
        return typeTf.getText();
    }
    private int getCredits() {
        return Integer.parseInt(creditsTf.getText()); 
    }
    private double getScholarship() {
        return Double.parseDouble(scholarshipTf.getText());
    }
    private String getDeduction() {
        return deductionTf.getText();
    }
}
