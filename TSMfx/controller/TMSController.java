package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import model.Faculty;
import model.Student;



public class TMSController  extends Controller<Faculty>{
   @FXML private TableView<Student> studentsTv;
    
    @FXML private void initialize() throws Exception {
        stage.getIcons().add(new Image("view/uts.jpeg"));
        studentsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    @FXML private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
    
    public final Faculty getFaculty() {
        return model;
    }
    
    public final String getTotalTuition() {
        DoubleProperty total = new SimpleDoubleProperty();
        for (Student student : studentsTv.getItems()) {
            total.set(total.get() + student.getTotalFee());
        }
        return total.asString("$%.2f").get();
    }
    
    public final String getTotalNetFee() {
        DoubleProperty total = new SimpleDoubleProperty();
        for (Student student : studentsTv.getItems()) {
            total.set(total.get() + student.getNetFee());
        }
        return total.asString("$%.2f").get();
   }
    
    public final String getTotalBAS() {
        DoubleProperty totalScholarship = new SimpleDoubleProperty();
        DoubleProperty totalDeduction = new SimpleDoubleProperty();
        for (Student student : studentsTv.getItems()) {
            totalScholarship.set(totalScholarship.get() + student.getScholarship());
            totalDeduction.set(totalDeduction.get() + student.getDeduction());
        } 
        DoubleProperty total = new SimpleDoubleProperty();
        total.set(totalScholarship.get() + totalDeduction.get());
        return total.asString("$%.2f").get();
    }
    
    public final String getTotalScholarship() {
        DoubleProperty total = new SimpleDoubleProperty();
        for (Student student : studentsTv.getItems()) {
            total.set(total.get() + student.getScholarship());
        } 
        return total.asString("$%.2f").get();
    }
    
    public final String getTotalDeduction() {
        DoubleProperty total = new SimpleDoubleProperty();
        for (Student student : studentsTv.getItems()) {
            total.set(total.get() + student.getDeduction());
        } 
        return total.asString("$%.2f").get();
    }
}
