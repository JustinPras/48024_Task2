package controller;

import au.edu.uts.ap.javafx.Controller;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import model.Student;



public class SlipController  extends Controller<Student> {
    @FXML private Text totalFeeTf;
    @FXML private Text netFeeTf;
    @FXML private Text payPerCreditTf;
    @FXML private Text scholarshipTf;
    @FXML private Text deductionTf;
    
    @FXML private void initialize() throws Exception {
        stage.getIcons().add(new Image("view/edit.png"));
        totalFeeTf.textProperty().bind(getStudent().totalFeeProperty().asString("$%.2f"));
        netFeeTf.textProperty().bind(getStudent().netFeeProperty().asString("$%.2f"));
        payPerCreditTf.textProperty().bind(getStudent().payPerCreditProperty().asString("$%.2f"));
        scholarshipTf.textProperty().bind(getStudent().scholarshipProperty().asString("$%.2f"));
        deductionTf.textProperty().bind(getStudent().deductionProperty().asString("$%.2f"));
    }
    
    public final Student getStudent() {
        return model;
    }
    
    @FXML private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
}
