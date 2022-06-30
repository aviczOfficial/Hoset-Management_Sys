package Ik.ijse.hybernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public Pane paneDashboarad;
    public AnchorPane aPane;

    public void btnBack(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    public void btnLoginDetails(ActionEvent actionEvent) throws IOException {
        paneDashboarad.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/LoginDetailsForm.fxml"));
        paneDashboarad.getChildren().add(parent);
    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) aPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    private void setUI2(String location) throws IOException {
        paneDashboarad.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        paneDashboarad.getChildren().add(parent);

    }

    public void btnManageStudent(ActionEvent actionEvent) throws IOException {
        setUI2("ManageStudentsForm");

    }

    public void btnManageRooms(ActionEvent actionEvent) throws IOException {
        setUI2("ManageRoomsForm");

    }

    public void btnResevation(ActionEvent actionEvent) throws IOException {

       setUI("ReservationForm");
    }

    public void btnRemainKeyMoney(ActionEvent actionEvent) throws IOException {
        setUI2("RemainKeyMoneyForm");

    }
}
