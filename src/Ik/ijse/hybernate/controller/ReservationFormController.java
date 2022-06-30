package Ik.ijse.hybernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFormController {
    public AnchorPane PaneResevation;

    public void btnBack(ActionEvent actionEvent) throws IOException {
        setUI("Dashboardform");
    }


    private void setUI(String location) throws IOException {
        Stage stage=(Stage) PaneResevation.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
