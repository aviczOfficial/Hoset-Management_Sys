package Ik.ijse.hybernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {


    public AnchorPane paneLogin;
    public TextField txtUsrNm;
    public PasswordField txtPwr;

    public void btnLogin(ActionEvent actionEvent) throws IOException {

        setUI("DashboardForm");

    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) paneLogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
